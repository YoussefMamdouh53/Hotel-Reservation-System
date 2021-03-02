public class Suite extends Room{
	public Suite() {
		super();
	}

	public String toString() {
		return "Room number: " + getNumber() + ", Type: Suite, " + "State: "
				+ (isReserved() ? "Reserved" : "Not Reserved");
	}
}
