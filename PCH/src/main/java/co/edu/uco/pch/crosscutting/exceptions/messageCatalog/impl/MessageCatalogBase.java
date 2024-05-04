package co.edu.uco.pch.crosscutting.exceptions.messageCatalog.impl;

import java.util.HashMap;
import java.util.Map;
import co.edu.uco.pch.crosscutting.exceptions.customs.CrossCuttingPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalog;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.Mensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;

public class MessageCatalogBase implements MessageCatalog {

	private final Map<String, Mensaje> mensajes= new HashMap<>();
	
	@Override
	public final void inicializar() {
		mensajes.clear();
		mensajes.put(CodigoMensaje.M00001.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00001, "El código del mensaje que se quiere obtener del catalogo de mensajes llego nulo"));
		mensajes.put(CodigoMensaje.M00002.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00002, "Se ha presentado un problema tratando de llevar a cabo la operaci+on deseada"));
		mensajes.put(CodigoMensaje.M00003.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00003, "El idetificador del mensaje \"${1}\" que se intentó obtener, no está en el catálo de mensajes base"));
		mensajes.put(CodigoMensaje.M00004.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00004, "El mensaje con identificador \"${1}\" que se intento obtener, no esta configurado para residir en el catalogo de mensajes base"));
		
	}

	@Override
	public final String obtenerContendidoMensaje(final CodigoMensaje codigo, String... parametros) {
		return obtenerMensaje(codigo, parametros).getContendio();
	}

	@Override
	public final Mensaje obtenerMensaje(final CodigoMensaje codigo, String... parametros) {
		if (ObjectHelper.getObjectHelper().isNull(codigo)) {
			var mensajeUsuario=obtenerContendidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico=obtenerContendidoMensaje(CodigoMensaje.M00001);
			throw new CrossCuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		if (!codigo.isBase()) {
			var mensajeUsuario=obtenerContendidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico=obtenerContendidoMensaje(CodigoMensaje.M00004, codigo.getIdentificador());
			throw new CrossCuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		if (!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario=obtenerContendidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico=obtenerContendidoMensaje(CodigoMensaje.M00003, codigo.getIdentificador());
			throw new CrossCuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		
		/*TODO: Tarea: asegure que si tiene parametros, el contenido 
		 del mensaje se retorne con los parametros reemplazados {1},{2},{3}*/
		
		return mensajes.get(codigo.getIdentificador());
	}

}
