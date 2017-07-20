package spreadsheetUpdates.observer;

import java.util.ArrayList;
import java.util.List;

import spreadsheetUpdates.util.Logger;

public class Cell implements Listener, Subject {
	List<Listener> observers;
	private int value, v1, v2;
	private Cell op1, op2;

	/**
	 * 
	 * @param cell1
	 *            first operand cell
	 * @param cell2
	 *            second operand cell
	 * @param val1
	 *            first operand value
	 * @param val2
	 *            second operand value
	 */
	public Cell(Cell cell1, Cell cell2, int val1, int val2) {
		Logger.writeMessage("In Cell constructor\n", Logger.DebugLevel.CONSTRUCTOR);
		observers = new ArrayList<Listener>();
		initialize(cell1, cell2, val1, val2);
		calculateVal();
	}

	/**
	 * 
	 * @param cell1
	 *            first operand cell
	 * @param cell2
	 *            second operand cell
	 * @param val1
	 *            first operand value
	 * @param val2
	 *            second operand value
	 */
	public void modify(Cell cell1, Cell cell2, int val1, int val2) {
		if (op1 != null) {
			op1.removeObserver(this);
		}
		if (op2 != null) {
			op2.removeObserver(this);
		}
		initialize(cell1, cell2, val1, val2);
		calculateVal();
		notifyObserver();
	}

	/**
	 * 
	 * @param cell1
	 *            first operand cell
	 * @param cell2
	 *            second operand cell
	 * @param val1
	 *            first operand value
	 * @param val2
	 *            second operand value
	 */
	private void initialize(Cell cell1, Cell cell2, int val1, int val2) {
		op1 = cell1;
		op2 = cell2;
		v1 = val1;
		v2 = val2;
	}

	/**
	 * @param Listener
	 *            cell
	 */
	public void registerObserver(Listener cell) {
		Logger.writeMessage("register observer invoked\n", Logger.DebugLevel.IN_REGISTER);
		observers.add(cell);
	}

	/**
	 * @param Listener
	 *            cell
	 */
	public void removeObserver(Listener cell) {
		observers.remove(cell);
	}

	public void notifyObserver() {
		Logger.writeMessage("Notify observer invoked\n", Logger.DebugLevel.IN_NOTIFY);
		for (Listener observer : observers) {
			observer.update();
		}
	}

	public void update() {
		calculateVal();
		notifyObserver();
	}

	private void calculateVal() {
		value = 0;
		if (op1 != null) {
			value += op1.getValue();
		}
		if (op2 != null) {
			value += op2.getValue();
		}
		value += v1 + v2;

	}

	/**
	 * 
	 * @return int value of cell
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value + "";
	}
}
