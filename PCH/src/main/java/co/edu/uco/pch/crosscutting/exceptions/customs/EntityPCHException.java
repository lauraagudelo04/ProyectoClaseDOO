package co.edu.uco.pch.crosscutting.exceptions.customs;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class EntityPCHException extends PCHException{

	private static final long serialVersionUID = -3662331984905572117L;

	public EntityPCHException(final String mensajeUsuario , Lugar lugar) {
		super(mensajeUsuario, Lugar.ENTITY);
	}
	
	public EntityPCHException(final String mensajeTecnico,final String mensajeUsuario, 
			final Throwable exceptionRaiz) {
		super(mensajeTecnico,mensajeUsuario, Lugar.ENTITY, exceptionRaiz);
	}
	
	
	
}
