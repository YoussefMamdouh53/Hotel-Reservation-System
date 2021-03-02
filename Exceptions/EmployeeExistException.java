package Exceptions;
public class EmployeeExistException extends Exception {
	public EmployeeExistException() {
		super("**Employee Already Exist!");
	}
}
