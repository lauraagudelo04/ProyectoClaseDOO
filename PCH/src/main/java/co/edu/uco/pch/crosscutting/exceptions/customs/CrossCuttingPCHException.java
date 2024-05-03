package co.edu.uco.pch.crosscutting.exceptions.customs;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class CrossCuttingPCHException extends PCHException{

	private static final long serialVersionUID = -3662331984905572117L;

	public CrossCuttingPCHException(final String mensajeUsuario , Lugar lugar) {
		super(mensajeUsuario, Lugar.DATA);
	}
	
	public CrossCuttingPCHException(String mensajeTecnico,String mensajeUsuario, 
			Lugar lugar, Throwable exceptionRaiz) {
		super(mensajeTecnico,mensajeUsuario, lugar, exceptionRaiz);
	}
	
	
	
}
