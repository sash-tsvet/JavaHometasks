package Exception.server.exceptions;

import Exception.TerminalException;

public class ServerTerminalException extends TerminalException {
	protected ServerTerminalException(String userMessage) {
		super(userMessage);
	}

	@Override
	public void displayError() {
		System.out.print("[SERVER] : ");
		super.displayError();
	}
}
