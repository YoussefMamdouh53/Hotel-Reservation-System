
public class BB extends Room {
	public BB() {
		super();
	}

	public String toString() {
		return "Room number: " + getNumber() + ", Type: Bed and Breakfast, " + "State: "
				+ (isReserved() ? "Reserved" : "Not Reserved");
	}
}
