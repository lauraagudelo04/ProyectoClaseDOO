package co.edu.uco.pch.crosscutting.exceptions;

import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;

public class PCHException extends RuntimeException{
	
	private static final long serialVersionUID=1L;
	protected String mensajeUsuario;
	protected Lugar lugar;
	
	public PCHException(String mensajeTecnico,String mensajeUsuario, 
		Lugar lugar, Throwable exceptionRaiz) {
		super(mensajeTecnico, exceptionRaiz);
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}
	
	public PCHException(final String mensajeUsuario, Lugar lugar) {
			super(mensajeUsuario);
			setMensajeUsuario(mensajeUsuario);
			setLugar(lugar);
		}
	
	public PCHException(String mensajeTecnico, String mensajeUsuario, Lugar lugar ) {
		super(mensajeUsuario);
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}

	private final void setMensajeUsuario(final String mensajeUsuario) {
		this.mensajeUsuario =TextHelper.applyTrim(mensajeUsuario);
	}

	private final void setLugar(final Lugar lugar) {
		this.lugar = ObjectHelper.getObjectHelper().getDefaultValue(lugar, Lugar.DEFAULT);
		
	}

	public final String getMensajeUsuario() {
		return mensajeUsuario;
	}

	public final Lugar getLugar() {
		return lugar;
	}
	
	
	
	
	

}
