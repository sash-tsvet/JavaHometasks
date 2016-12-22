package Exception.terminal;

import Exception.TerminalException;

class TerminalInterface {

	void showMessage(TerminalException e) {
		e.displayError();
	}
}
