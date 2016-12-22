package Exception.terminal.exceptions;

public class NoCardException extends ClientTerminalException {
	public NoCardException() {
		super("Card already removed or not yet inserted");
	}
}
