package co.edu.uco.pch.crosscutting.helpers;

public final class ObjectHelper {
	
	private static final ObjectHelper INSTANCE=new ObjectHelper();	
	private ObjectHelper() {
		super();
	}
	
	public static final ObjectHelper getObjectHelper() {
		return INSTANCE;
	}
	
	public <O> boolean isNull( O objeto) {
		return objeto==null;
	}
	
	public <O> O getDefaultValue( O objeto, O ValorDefecto) {
		return isNull(objeto)? ValorDefecto : objeto;
	}
	
	
}
