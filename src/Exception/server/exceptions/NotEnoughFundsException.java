package Exception.server.exceptions;

public class NotEnoughFundsException extends ServerTerminalException {
	public NotEnoughFundsException() {
		super("Not enough funds for such operation");
	}
}
