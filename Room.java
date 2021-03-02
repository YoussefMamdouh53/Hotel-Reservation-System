public class Room implements java.io.Serializable {
	private static int roomCount;
	private int number;
	private Reservation reservation;

	public Room() {
		this.number = ++roomCount;
	}

	public Room(Reservation reservation) {
		this.number = ++roomCount;
		this.reservation = reservation;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public int getNumber() {
		return number;
	}

	public boolean isReserved() {
		if (reservation == null)
			return false;
		else
			return true;
	}

	public void reserve(Reservation reservation) {
		this.reservation = reservation;
	}

	public void free() {
		reservation = null;
	}

}
