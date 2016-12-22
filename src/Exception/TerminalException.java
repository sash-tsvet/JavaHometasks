package Exception;

public abstract class TerminalException extends Exception {

	private String userMessage;

	public TerminalException(String userMessage) {
		this.userMessage = userMessage;
	}

	public void displayError() {
		System.out.println(userMessage);
	}
}
