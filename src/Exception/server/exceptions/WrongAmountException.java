package Exception.server.exceptions;

public class WrongAmountException extends ServerTerminalException {
	public WrongAmountException() {
		super("Wrong amount. Should devide by 100.");
	}
}
