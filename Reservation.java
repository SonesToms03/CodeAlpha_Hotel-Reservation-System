public class Reservation {
    private int reservationId;
    private Room room;
    private String guestName;
    private double amountPaid;

    public Reservation(int reservationId, Room room, String guestName, double amountPaid) {
        this.reservationId = reservationId;
        this.room = room;
        this.guestName = guestName;
        this.amountPaid = amountPaid;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", room=" + room +
                ", guestName='" + guestName + '\'' +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
