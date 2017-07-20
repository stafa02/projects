package genericSerDeser.util;

public class Second {
	private double doubleValue;
	private double doubleValue2;
	private long longValue;
	private long longValue2;
	private short shortValue;
	private short shortValue2;
	private String stringValue = "";

	public Second() {
		Logger.writeMessage("In constructor for second \n", Logger.DebugLevel.CONSTRUCTOR);
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public short getShortValue() {
		return shortValue;
	}

	public void setShortValue(short shortValue) {
		this.shortValue = shortValue;
	}

	public short getShortValue2() {
		return shortValue2;
	}

	public void setShortValue2(short shortValue2) {
		this.shortValue2 = shortValue2;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public long getLongValue2() {
		return longValue2;
	}

	public void setLongValue2(long longValue2) {
		this.longValue2 = longValue2;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public double getDoubleValue2() {
		return doubleValue2;
	}

	public void setDoubleValue2(double doubleValue2) {
		this.doubleValue2 = doubleValue2;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Second) {
			Second s = (Second) o;
			if (stringValue.equals(s.getStringValue()) && shortValue == s.getShortValue()
					&& shortValue2 == s.getShortValue2() && longValue == s.getLongValue()
					&& longValue2 == s.getLongValue2() && doubleValue == s.getDoubleValue()
					&& doubleValue2 == s.getDoubleValue2()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return shortValue + shortValue2;
	}
}