import java.util.Date;

public class Reservation implements java.io.Serializable {
	private static int reservationCount;
	private int id;
	private Date dateCreated;
	private Room room;
	private int duration;
	private Employee employee;
	private Customer customer;

	public Reservation(Customer customer, Room room, Employee employee, int duration) {
		this.room = room;
		this.employee = employee;
		this.customer = customer;
		this.duration = duration;
		dateCreated = new Date();
		this.id = ++reservationCount;
	}

	public static void setCount(int c) {
		reservationCount = c;
	}

	public boolean customerEquals(Customer customer) {
		if (this.customer == customer)
			return true;
		else
			return false;
	}

	public boolean employeeEquals(Employee employee) {
		if (this.employee == employee)
			return true;
		else
			return false;
	}

	public int getRoomNumber() {
		return room.getNumber();
	}

	public int getId() {
		return id;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String print() {
		return "Id: " + id + ", Date Created: " + dateCreated.toString() + ", Duraion: " + duration
				+ " day(s), Room number: " + room.getNumber() + ", Customer: " + customer.getName() + ", Employee: "
				+ employee.getName();
	}
}
