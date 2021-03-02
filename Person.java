public class Person implements java.io.Serializable {
	private int id;
	private static int idCount;
	private String name;


	public Person(String name) {
		this.id = ++idCount;
		this.name = name;
	}
	Person(int id, String name){
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}