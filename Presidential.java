public class Presidential extends Room {
	public Presidential() {
		super();
	}

	public String toString() {
		return "Room number: " + getNumber() + ", Type: Presidential, " + "State: "
				+ (isReserved() ? "Reserved" : "Not Reserved");
	}
}
