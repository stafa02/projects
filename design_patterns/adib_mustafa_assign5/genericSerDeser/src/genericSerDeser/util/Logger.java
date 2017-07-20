
package genericSerDeser.util;

public class Logger {
	/*
	 * DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
	 * DEBUG_VALUE=3 [Print to stdout everytime a thread's run() method is
	 * called] DEBUG_VALUE=2 [Print to stdout everytime an entry is added to the
	 * Results data structure] DEBUG_VALUE=1 [Print to stdout the contents of
	 * the data structure in the store] DEBUG_VALUE=0 [No output should be
	 * printed from the application, except the line
	 * "The average preference value is X.Y" ]
	 */

	public static enum DebugLevel {
		RELEASE, RESULT, DESERIALIZED_OBJECT, SET_UPDATED, CONSTRUCTOR
	};

	private static DebugLevel debugLevel;

	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		case 4:
			debugLevel = DebugLevel.CONSTRUCTOR;
			break;
		case 3:
			debugLevel = DebugLevel.SET_UPDATED;
			break;
		case 2:
			debugLevel = DebugLevel.DESERIALIZED_OBJECT;
			break;
		case 1:
			debugLevel = DebugLevel.RESULT;
			break;
		case 0:
			debugLevel = DebugLevel.RELEASE;
			break;
		}
	}

	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	// @return None
	public static void writeMessage(String message, DebugLevel levelIn) {
		if (levelIn == debugLevel)
			System.out.print(message);
	}

	/**
	 * @return String
	 */
	public String toString() {
		return "Debug Level is " + debugLevel;
	}
}