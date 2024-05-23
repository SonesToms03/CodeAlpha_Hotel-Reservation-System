import java.util.*;

public class HotelReservationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static ReservationManager reservationManager;

    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, RoomCategory.SINGLE));
        rooms.add(new Room(102, RoomCategory.DOUBLE));
        rooms.add(new Room(103, RoomCategory.SUITE));
        rooms.add(new Room(104, RoomCategory.SINGLE));
        rooms.add(new Room(105, RoomCategory.DOUBLE));

        reservationManager = new ReservationManager(rooms);

        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nHotel Reservation System");
        System.out.println("1. Search for available rooms");
        System.out.println("2. Make a reservation");
        System.out.println("3. View booking details");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private static void searchRooms() {
        System.out.print("Enter room category (SINGLE, DOUBLE, SUITE): ");
        String categoryInput = scanner.nextLine().toUpperCase();
        try {
            RoomCategory category = RoomCategory.valueOf(categoryInput);
            List<Room> availableRooms = reservationManager.searchAvailableRooms(category);
            if (availableRooms.isEmpty()) {
                System.out.println("No available rooms in this category.");
            } else {
                System.out.println("Available rooms:");
                availableRooms.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid room category. Please try again.");
        }
    }

    private static void makeReservation() {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Room room = null;
        for (Room r : reservationManager.searchAvailableRooms(RoomCategory.SINGLE)) {
            if (r.getRoomNumber() == roomNumber) {
                room = r;
                break;
            }
        }
        if (room == null) {
            for (Room r : reservationManager.searchAvailableRooms(RoomCategory.DOUBLE)) {
                if (r.getRoomNumber() == roomNumber) {
                    room = r;
                    break;
                }
            }
        }
        if (room == null) {
            for (Room r : reservationManager.searchAvailableRooms(RoomCategory.SUITE)) {
                if (r.getRoomNumber() == roomNumber) {
                    room = r;
                    break;
                }
            }
        }

        if (room != null && room.isAvailable()) {
            System.out.print("Enter your name: ");
            String guestName = scanner.nextLine();
            System.out.print("Enter payment amount: ");
            double amountPaid = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            Reservation reservation = reservationManager.makeReservation(room, guestName, amountPaid);
            if (reservation != null) {
                System.out.println("Reservation successful!");
                System.out.println(reservation);
            } else {
                System.out.println("Failed to make reservation. Room might not be available.");
            }
        } else {
            System.out.println("Invalid room number or room is not available.");
        }
    }

    private static void viewReservations() {
        List<Reservation> reservations = reservationManager.getReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("Current reservations:");
            reservations.forEach(System.out::println);
        }
    }
}
