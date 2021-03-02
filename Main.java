import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Exceptions.CustomerNotFoundException;
import Exceptions.EmployeeExistException;
import Exceptions.EmployeeNotFoundException;
import Exceptions.ReservationNotFoundException;
import Exceptions.RoomNotAvailableException;
import Exceptions.RoomNotFoundException;

import java.io.*;

public class Main {
	static Room room[] = new Room[10];
	static ArrayList<Employee> employee = new ArrayList<>();
	static ArrayList<Customer> customer = new ArrayList<>();
	static ArrayList<Reservation> reservation = new ArrayList<>();
	static Scanner input = new Scanner(System.in);
	static Employee currentLogin;

	static void initialize() {
		employee.add(new Manager("admin", "1234")); // id 1
		employee.add(new Receptionist("user", "1234")); // id 2
		room[0] = new Royal();
		room[1] = new Presidential();
		room[2] = new Presidential();
		for (int i = 3; i < 6; i++)
			room[i] = new Suite();
		for (int i = 6; i < 10; i++)
			room[i] = new BB();
	}

	static void loadFile() {
		Object[] o = null;
		try {
			FileInputStream fileIn = new FileInputStream("save.data");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			o = (Object[]) in.readObject();
			in.close();
			fileIn.close();
			room = (Room[]) o[0];
			employee = (ArrayList<Employee>) o[1];
			customer = (ArrayList<Customer>) o[2];
			reservation = (ArrayList<Reservation>) o[3];
			if (employee.size() != 0)
				Employee.setCount(employee.get(employee.size() - 1).getId());
			if (reservation.size() != 0)
				Reservation.setCount(reservation.get(reservation.size() - 1).getId());
			if (customer.size() != 0)
				Customer.setCount(customer.get(customer.size() - 1).getId());
			return;
		} catch (IOException i) {
			// i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("class not found!");
			// c.printStackTrace();
		}
		initialize();
	}

	static void saveFile() {
		Object[] o = new Object[4];
		o[0] = room;
		o[1] = employee;
		o[2] = customer;
		o[3] = reservation;
		try {
			FileOutputStream fileOut = new FileOutputStream("save.data");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	static void login() {
		while (currentLogin == null) {
			System.out.print("Enter your id: ");
			int id = input.nextInt();
			System.out.print("Enter your password: ");
			String password = input.next();
			for (int i = 0; i < employee.size(); i++) {
				if (employee.get(i).getId() == id && employee.get(i).validatePassword(password)) {
					currentLogin = employee.get(i);
					System.out.println(currentLogin.print());
					return;
				}
			}
			System.out.println("**Wrong id or password!");
		}
	}

	static void viewAllRooms() {
		for (Room r : room) {
			System.out.println(r.toString());
		}
	}

	static void viewAllCustomer() {
		if (customer.size() == 0)
			System.out.println("No customer.");
		for (Customer c : customer) {
			c.print();
		}
	}

	static void deleteCustomer() throws CustomerNotFoundException {
		System.out.print("Enter Customer's id to delete: ");
		int id = input.nextInt();
		for (Customer c : customer) {
			if (c.getId() == id) {
				// removing reservations that associate with customer id.
				Iterator<Reservation> it = reservation.iterator();
				Reservation r;
				while (it.hasNext()) {
					r = it.next();
					if (r.customerEquals(c)) {
						// free room.
						room[r.getRoomNumber() - 1].free();
						// remove reservation.
						it.remove();
					}
				}
				System.out.println("Customer '" + c.getName() + "' is deleted!");
				customer.remove(c);
				return;
			}
		}
		throw new CustomerNotFoundException();
	}

	static void viewAllReceptionist() {
		for (Employee e : employee) {
			if (e instanceof Receptionist)
				System.out.println(e.print());
		}
	}

	static void viewAllReservation() {
		if (reservation.size() == 0)
			System.out.println("No reservation found!");
		for (Reservation r : reservation) {
			System.out.println(r.print());
		}
	}

	static void addNewReceptionist() throws EmployeeExistException {
		System.out.print("Enter Receptionist Name: ");
		String name = input.next();
		System.out.print("Enter Receptionist Password: ");
		String password = input.next();
		for (Employee e : employee) {
			if (e.getName().equals(name))
				throw new EmployeeExistException();
		}
		employee.add(new Receptionist(name, password));
		System.out.println(employee.get(employee.size() - 1).print());
	}

	static void addNewCustomer() {
		System.out.print("Enter customer's name: ");
		String name = input.next();
		customer.add(new Customer(name));
		System.out
				.println("Customer Created successfully with id '" + customer.get(customer.size() - 1).getId() + "' !");
	}

	static void deleteReservation() throws ReservationNotFoundException {
		System.out.print("Enter reservation id to delete: ");
		int id = input.nextInt();
		for (Reservation r : reservation) {
			if (r.getId() == id) {
				room[r.getRoomNumber() - 1].free();
				for (Customer c : customer) {
					if (r.customerEquals(c))
						c.removeReservation(r);
				}
				reservation.remove(r);
				return;
			}
		}
		throw new ReservationNotFoundException();
	}

	static void deleteReceptionist() throws EmployeeNotFoundException {
		System.out.print("Enter Receptionist's id to delete: ");
		int id = input.nextInt();
		for (Employee e : employee) {
			if (e.getId() == id) {
				if (e instanceof Receptionist) {
					employee.remove(e);
					for (Reservation r : reservation) {
						if (r.employeeEquals(e)) {
							r.setEmployee(null);
							break;
						}
					}
					System.out.println("Receptionist with id '" + e.getId() + "' is deleted Successfully!");
					return;
				}
			}
		}
		throw new EmployeeNotFoundException();
	}

	static void makeReservation() throws CustomerNotFoundException, RoomNotAvailableException, RoomNotFoundException {
		System.out.print("Enter Customer id: ");
		int id = input.nextInt();
		System.out.print("Enter room number: ");
		int roomNumber = input.nextInt();
		System.out.print("Enter duration: ");
		int duration = input.nextInt();
		Customer _customer = null;
		for (Customer c : customer) {
			if (c.getId() == id) {
				_customer = c;
				break;
			}
		}
		if (_customer == null)
			throw new CustomerNotFoundException();
		if (roomNumber <= 10 && roomNumber > 0) {
			if (room[roomNumber - 1].isReserved()) {
				throw new RoomNotAvailableException();
			} else {
				reservation.add(new Reservation(_customer, room[roomNumber - 1], currentLogin, duration));
				room[roomNumber - 1].reserve(reservation.get(reservation.size() - 1));
				_customer.addReservation(reservation.get(reservation.size() - 1));
			}
		} else
			throw new RoomNotFoundException();
	}

	static boolean loadMenu() {
		System.out.println();
		int choice;
		if (currentLogin instanceof Manager) {
			choice = ((Manager) currentLogin).menu();
		} else {
			choice = ((Receptionist) currentLogin).menu();
		}
		switch (choice) {
		case 1:
			viewAllRooms();
			break;
		case 2:
			try {
				addNewReceptionist();
			} catch (EmployeeExistException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			viewAllReceptionist();
			break;
		case 4:
			try {
				deleteReceptionist();
			} catch (EmployeeNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 5:
			try {
				makeReservation();
			} catch (CustomerNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (RoomNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (RoomNotAvailableException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 6:
			viewAllReservation();
			break;
		case 7:
			try {
				deleteReservation();
			} catch (ReservationNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 8:
			addNewCustomer();
			break;
		case 9:
			viewAllCustomer();
			break;
		case 10:
			try {
				deleteCustomer();
			} catch (CustomerNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 11:
			currentLogin = null;
			login();
			break;
		case 12:
			System.out.println("Thank you for using Hotel Reservation System.");
			return false;
		default:
			System.out.println("**Invalid Choice!");
		}
		return true;
	}

	public static void main(String[] args) {
		loadFile();
		login();
		while (loadMenu());
		saveFile();
	}
}
