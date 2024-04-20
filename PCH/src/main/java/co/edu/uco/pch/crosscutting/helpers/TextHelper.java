package co.edu.uco.pch.crosscutting.helpers;

public final class TextHelper {
	
	public static final String EMPTY="";
		
	private TextHelper() {
		super();
	}
	
	public static final boolean isNull(final String string) {
		return string==null;
	}
	
	public static final boolean isNullOrEmpty(final String string) {
		return isNull(string)|| EMPTY.equals(applyTrim(string));
	}
	
	public static final String getDefaultValue(final String string,final String defaultValue) {
		return isNullOrEmpty(string) ? defaultValue : string;
	}
	
	public static final String getDefaultValue(final String string) {
		return getDefaultValue(string,EMPTY);
	}
	
	public static final String applyTrim(final String string) {
		return getDefaultValue(string).trim();
	}
	
}
