package Exception.terminal.exceptions;

public class InvalidPinException extends ClientTerminalException {
	public InvalidPinException() {
		super("Wrong pin code, try again!");
	}
}
