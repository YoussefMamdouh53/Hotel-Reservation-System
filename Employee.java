public abstract class Employee extends Person {
	private static int employeeCount;
	private String password;

	public Employee(String name, String password) {
		super(++employeeCount,name);
		this.password = password;
	}

	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}

	public static void setCount(int c) {
		employeeCount = c;
	}

	public String print() {
		return "id: " + getId() + ", name: " + getName() + ", password: " + password;
	}

	public abstract int menu();
}
