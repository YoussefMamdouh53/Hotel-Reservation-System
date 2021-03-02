public class Royal extends Room {
	public Royal() {
		super();
	}

	public String toString() {
		return "Room number: " + getNumber() + ", Type: Royal, " + "State: "
				+ (isReserved() ? "Reserved" : "Not Reserved");
	}
}
