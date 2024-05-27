package co.edu.uco.pch.crosscutting.exceptions.customs;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class InitializerPCHException extends PCHException{

	private static final long serialVersionUID = 1L;

	public InitializerPCHException(final String mensajeUsuario , Lugar lugar) {
		super(mensajeUsuario, Lugar.INITIALIZER);
	}
	
	public InitializerPCHException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, Lugar.INITIALIZER);
	}
	
	
	public InitializerPCHException(final String mensajeTecnico,final String mensajeUsuario,
			final Throwable exceptionRaiz) {
		super(mensajeTecnico,mensajeUsuario, Lugar.INITIALIZER, exceptionRaiz);
	}
}
