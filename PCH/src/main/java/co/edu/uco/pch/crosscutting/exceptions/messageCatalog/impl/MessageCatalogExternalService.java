package co.edu.uco.pch.crosscutting.exceptions.messageCatalog.impl;

import java.util.HashMap;
import java.util.Map;

import co.edu.uco.pch.crosscutting.exceptions.customs.CrossCuttingPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalog;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.Mensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;

public class MessageCatalogExternalService implements MessageCatalog {
	private final Map<String, Mensaje> mensajes=new HashMap<>();

	@Override
	public final void inicializar() {	
		mensajes.clear();
		mensajes.put(CodigoMensaje.M00007.getIdentificador(),new Mensaje(CodigoMensaje.M00007, 
				"La transacción se ha completado satisfactoriamente"));
	}

	@Override
	public final String obtenerContendidoMensaje(final CodigoMensaje codigo, String... parametros) {
		return obtenerMensaje(codigo,parametros).getContendio();
	}

	@Override
	public final Mensaje obtenerMensaje(final CodigoMensaje codigo, String... parametros) {
		if(ObjectHelper.getObjectHelper().isNull(codigo)){
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico =  MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
			throw new CrossCuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		if(codigo.isBase()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00005,codigo.getIdentificador());
			throw new CrossCuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		if(!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00006,codigo.getIdentificador());
			throw new CrossCuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		return mensajes.get(codigo.getIdentificador());
	}

}
