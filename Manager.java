import java.util.Scanner;
public class Manager extends Employee{
	public Manager(String name,String password) {
		super(name,password);
	}
	public int menu() {
		Scanner input = new Scanner(System.in);
		System.out.println("1) View all rooms");
		System.out.println("2) Add new receptionist");
		System.out.println("3) View all receptionists");
		System.out.println("4) Delete receptionist by id");
		System.out.println("5) Make new reservation");
		System.out.println("6) View all reservations");
		System.out.println("7) Delete a reservation");
		System.out.println("8) Add new customer");
		System.out.println("9) View all customer");
		System.out.println("10) Delete customer by id");
		System.out.println("11) Sign Out");
		System.out.println("12) Exit and Save");
		int choice = input.nextInt();
		if (choice >=1 && choice <=12)
			return choice;
		else
			return 0;
	}
}
