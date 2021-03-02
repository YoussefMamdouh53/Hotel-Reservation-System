package Exceptions;
public class CustomerNotFoundException extends Exception {
	public CustomerNotFoundException() {
		super("**Customer not found!");
	}
}
