package co.edu.uco.pch.crosscutting.exceptions.messageCatalog;

import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.Mensaje;

public interface MessageCatalog {
	
	void inicializar();
	String obtenerContendidoMensaje(final CodigoMensaje codigo , String...parametros);
	
	Mensaje obtenerMensaje(final CodigoMensaje codigo, String...parametros);
	
	

}

