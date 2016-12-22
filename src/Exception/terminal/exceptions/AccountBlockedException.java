package Exception.terminal.exceptions;

import java.util.Date;

public class AccountBlockedException extends ClientTerminalException {
	public AccountBlockedException(Date date) {
		super("Account blocked after several attempts. Please wait " + (date.getTime() - new Date().getTime()) / 1000 + " seconds.");
	}
}
