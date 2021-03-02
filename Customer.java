import java.util.ArrayList;

public class Customer extends Person {
	private static int customerCount;
	private ArrayList<Reservation> reservations;

	public Customer(String name) {
		super(++customerCount,name);
		reservations = new ArrayList<Reservation>();
	}
	public static void setCount(int c) {
		customerCount = c;
	}
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	public void removeReservation(Reservation reservation) {
		reservations.remove(reservation);
	}
	public void print() {
		System.out.print("id: " + getId() + ", Name: " + getName() + ", reservations: [");
		for (int i = 0; i < reservations.size(); i++) {
			System.out.print(reservations.get(i).getRoomNumber());
			if (i < reservations.size() - 1)
				System.out.print(", ");
		}
		System.out.println("]");
	}
}
