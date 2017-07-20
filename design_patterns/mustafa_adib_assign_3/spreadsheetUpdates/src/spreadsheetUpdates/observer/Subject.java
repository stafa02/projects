package spreadsheetUpdates.observer;

public interface Subject {
	public void registerObserver(Listener l);

	public void removeObserver(Listener l);

	public void notifyObserver();
}
