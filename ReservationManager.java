import java.util.*;
import java.util.stream.Collectors;

public class ReservationManager {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int nextReservationId;

    public ReservationManager(List<Room> rooms) {
        this.rooms = rooms;
        this.reservations = new ArrayList<>();
        this.nextReservationId = 1;
    }

    public List<Room> searchAvailableRooms(RoomCategory category) {
        return rooms.stream()
                .filter(room -> room.getCategory() == category && room.isAvailable())
                .collect(Collectors.toList());
    }

    public Reservation makeReservation(Room room, String guestName, double amountPaid) {
        if (room.isAvailable()) {
            room.setAvailable(false);
            Reservation reservation = new Reservation(nextReservationId++, room, guestName, amountPaid);
            reservations.add(reservation);
            return reservation;
        }
        return null;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
