package genericSerDeser.util;

public class First {
	private boolean booleanValue;
	private byte byteValue;
	private char charValue;
	private double doubleValue;
	private float floatValue;
	private int intValue;
	private long longValue;
	private short shortValue;
	private String stringValue = "";

	public First() {
		Logger.writeMessage("In constructor for first \n", Logger.DebugLevel.CONSTRUCTOR);
	}

	public byte getByteValue() {
		return byteValue;
	}

	public void setByteValue(byte byteValue) {
		this.byteValue = byteValue;
	}

	public short getShortValue() {
		return shortValue;
	}

	public void setShortValue(short shortValue) {
		this.shortValue = shortValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public char getCharValue() {
		return charValue;
	}

	public void setCharValue(char charValue) {
		this.charValue = charValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof First) {
			First f = (First) o;
			if (stringValue.equals(f.getStringValue()) && shortValue == f.getShortValue()
					&& byteValue == f.getByteValue() && longValue == f.getLongValue() && intValue == f.getIntValue()
					&& doubleValue == f.getDoubleValue() && floatValue == f.getFloatValue()
					&& charValue == f.getCharValue() && booleanValue == f.getBooleanValue()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return shortValue + byteValue + intValue;
	}
}
