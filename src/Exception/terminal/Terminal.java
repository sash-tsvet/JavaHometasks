package Exception.terminal;

import Exception.Card;
import Exception.TerminalException;
import Exception.server.Connection;
import Exception.server.TerminalServer;
import Exception.server.exceptions.NoSuchAccountException;
import Exception.terminal.exceptions.AlreadyInsertedException;
import Exception.terminal.exceptions.InvalidCardNumberException;
import Exception.terminal.exceptions.InvalidPinException;
import Exception.terminal.exceptions.NoCardException;

public class Terminal {

	private TerminalServer terminalServer = TerminalServer.getServer();
	private PinValidator pinValidator = null;
	private TerminalInterface anInterface = new TerminalInterface();
	private Card insertedCard = null;
	private Connection connection = null;


	public void insertCard(Card card) {
		try {
			readCard(card);
		} catch (TerminalException te) {
			insertedCard = null;
			pinValidator = null;
			connection = null;
			anInterface.showMessage(te);
		}
	}

	private void readCard(Card card) throws AlreadyInsertedException, InvalidCardNumberException, NoSuchAccountException {

		if (insertedCard != null) {
			throw new AlreadyInsertedException();
		}
		if (!card.getCardNumber().replace(" ", "").matches("\\d{16}")) {
			throw new InvalidCardNumberException();
		}

		insertedCard = card;
		connection = terminalServer.connect(card.getCardNumber());
		pinValidator = new PinValidator(connection);
	}

	public void enterPin(String pin) {
		try {
			if (insertedCard != null) {
				pinValidator.validatePin(pin);
			} else {
				throw new NoCardException();
			}
		} catch (TerminalException te) {
			anInterface.showMessage(te);
		}
	}

	public int checkBalance() {
		try {
			if (pinValidator == null) {
				throw new NoCardException();
			}
			if (!pinValidator.isValid()) {
				throw new InvalidPinException();
			}
		} catch (TerminalException te) {
			anInterface.showMessage(te);
			return 0;
		}
		return connection.getBalance();
	}

	public int receiveMoney(int amount) {
		try {
			if (pinValidator == null) {
				throw new NoCardException();
			}
			if (!pinValidator.isValid()) {
				throw new InvalidPinException();
			}
			return connection.receiveMoney(amount);
		} catch (TerminalException te) {
			anInterface.showMessage(te);
			return 0;
		}
	}

	public void putMoney(int amount) {
		try {
			if (pinValidator == null) {
				throw new NoCardException();
			}
			if (!pinValidator.isValid()) {
				throw new InvalidPinException();
			}
			connection.putMoney(amount);
		} catch (TerminalException te) {
			anInterface.showMessage(te);
		}
	}

	public Card removeCard() {
		try {
			if (insertedCard == null) {
				throw new NoCardException();
			}
		} catch (TerminalException te) {
			anInterface.showMessage(te);
		}

		Card tmpCard = insertedCard;
		insertedCard = null;
		connection = null;
		pinValidator = null;
		return tmpCard;
	}

	public static void main(String[] args) throws InterruptedException {
		Terminal terminal1 = new Terminal();


		terminal1.insertCard(new Card("0000 1111 2222 1333"));

		terminal1.insertCard(new Card("0000-1111-2222-3333"));
		terminal1.insertCard(new Card("0000-1111-2222-3333"));
		terminal1.insertCard(new Card("0000 1111 2222 3334"));
		terminal1.insertCard(new Card("0000 1111 2222 3335"));
		terminal1.insertCard(new Card("0000 1111 2222 3334"));
		terminal1.enterPin("001");
		terminal1.enterPin("002");
		terminal1.enterPin("003");
		Thread.sleep(1000);
		terminal1.enterPin("1234");
		Thread.sleep(1000);
		terminal1.enterPin("1234");
		Thread.sleep(1000);
		terminal1.enterPin("1234");
		Thread.sleep(1000);
		terminal1.enterPin("1234");
		Thread.sleep(1000);
		terminal1.enterPin("1234");
		Thread.sleep(1000);
		terminal1.enterPin("1234");

		System.out.println("Money received: " + terminal1.receiveMoney(123));
		System.out.println("Money received: " + terminal1.receiveMoney(10000));
		System.out.println("Money received: " + terminal1.receiveMoney(1000));

		System.out.println("Balance: " + terminal1.checkBalance());

		terminal1.putMoney(10000);
		System.out.println(terminal1.checkBalance());


		terminal1.removeCard();

		System.out.println("After remove:");

		terminal1.enterPin("123");
		terminal1.checkBalance();
		terminal1.putMoney(100);
		terminal1.receiveMoney(100);
	}
}
