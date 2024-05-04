package co.edu.uco.pch.crosscutting.exceptions.customs;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class CrossCuttingPCHException extends PCHException{

	private static final long serialVersionUID = 1L;
	private static final Lugar lugar=Lugar.CROSSCUTTING;

	public CrossCuttingPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar);
	}
	
	public CrossCuttingPCHException(final String mensajeTecnico,final String mensajeUsuario) {
		super(mensajeTecnico,mensajeUsuario,lugar);
	}
	
	public CrossCuttingPCHException(final String mensajeTecnico,final String mensajeUsuario,
			final Throwable exceptionRaiz) {
		super(mensajeTecnico,mensajeUsuario, lugar, exceptionRaiz);
	}
	
}
