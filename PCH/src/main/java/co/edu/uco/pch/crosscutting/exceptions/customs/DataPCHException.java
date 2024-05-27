package co.edu.uco.pch.crosscutting.exceptions.customs;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class DataPCHException extends PCHException{

	private static final long serialVersionUID = 1L;
	private static final Lugar lugar=Lugar.DATA;

	public DataPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar );
	}
	
	public DataPCHException(final String mensajeUsuario,final String mensajeTecnico) {
		super(mensajeTecnico, mensajeUsuario, lugar);
	}
	
	public DataPCHException(final String mensajeTecnico,final String mensajeUsuario,
			final Throwable exceptionRaiz) {
		super(mensajeTecnico,mensajeUsuario, lugar, exceptionRaiz);
	}
	
}
