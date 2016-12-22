package Exception.terminal;

import Exception.server.Connection;
import Exception.terminal.exceptions.AccountBlockedException;
import Exception.terminal.exceptions.InvalidPinException;
import Exception.terminal.exceptions.PinAlreadyEnteredException;

import java.util.Date;

class PinValidator {

	private Connection connection;
	private int attemptsCount = 0;
	private boolean isValid = false;
	private Date blockedTime = null;

	PinValidator(Connection connection) {
		this.connection = connection;
	}

	boolean validatePin(String pin) throws AccountBlockedException, InvalidPinException, PinAlreadyEnteredException {
		if (isValid) {
			throw new PinAlreadyEnteredException();
		}
		if (blockedTime == null || blockedTime.before(new Date())) {
			if (connection.validatePin(pin)) {
				isValid = true;
				return true;
			} else {
				attemptsCount += 1;
				if (attemptsCount >= 3) {
					blockedTime = new Date(
							new Date().getTime() + 5000
					);
					attemptsCount = 0;
				}
				throw new InvalidPinException();
			}
		} else {
			throw new AccountBlockedException(blockedTime);
		}
	}

	boolean isValid() {
		return isValid;
	}

}
