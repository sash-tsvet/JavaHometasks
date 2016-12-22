package Exception.terminal.exceptions;

import Exception.TerminalException;

public class ClientTerminalException extends TerminalException {
	ClientTerminalException(String userMessage) {
		super(userMessage);
	}

	@Override
	public void displayError() {
		System.out.print("[CLIENT] : ");
		super.displayError();
	}
}
