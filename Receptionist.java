import java.util.Scanner;

public class Receptionist extends Employee {
	public Receptionist(String name, String password) {
		super(name, password);
	}

	public int menu() {
		Scanner input = new Scanner(System.in);
		System.out.println("1) View all rooms"); // 1
		System.out.println("2) Make new reservation"); // 5
		System.out.println("3) View all reservations"); // 6
		System.out.println("4) Add new customer"); // 8
		System.out.println("5) View all customer"); // 9
		System.out.println("6) Delete customer by id"); // 10
		System.out.println("7) Sign Out"); // 11
		System.out.println("8) Exit and Save"); // 12
		int choice = input.nextInt();
		switch (choice) {
		case 1:
			return 1;
		case 2:
			return 5;
		case 3:
			return 6;
		case 4:
			return 8;
		case 5:
			return 9;
		case 6:
			return 10;
		case 7:
			return 11;
		case 8:
			return 12;
		default:
			return 0;
		}

	}
}