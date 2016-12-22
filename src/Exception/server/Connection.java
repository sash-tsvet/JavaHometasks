package Exception.server;

import Exception.server.exceptions.NotEnoughFundsException;
import Exception.server.exceptions.WrongAmountException;

public class Connection {
	private Account account;

	Connection(Account account) {
		this.account = account;
	}

	public boolean validatePin(String pin) {
		return account.checkPin(pin);
	}

	public int getBalance() {
		return account.getBalance();
	}

	public int receiveMoney(int amount) throws NotEnoughFundsException, WrongAmountException {
		if (amount % 100 != 0) {
			throw new WrongAmountException();
		}
		return account.receiveMoney(amount);
	}

	public void putMoney(int amount) throws WrongAmountException {
		if (amount % 100 != 0) {
			throw new WrongAmountException();
		}
		account.putMoney(amount);
	}
}
