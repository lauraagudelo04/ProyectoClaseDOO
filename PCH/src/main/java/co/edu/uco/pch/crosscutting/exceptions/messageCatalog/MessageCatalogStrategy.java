package co.edu.uco.pch.crosscutting.exceptions.messageCatalog;

import co.edu.uco.pch.crosscutting.exceptions.customs.CrossCuttingPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.Mensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.impl.MessageCatalogBase;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;

public final class MessageCatalogStrategy {
	private static final MessageCatalog base= new MessageCatalogBase();
	private static final MessageCatalog externalService= new MessageCatalogBase();
	
	private MessageCatalogStrategy() {
		super();
	}
	
	public static void inicializar() {
		base.inicializar();
		externalService.inicializar();
	}
	
	private static final MessageCatalog getStrategy(final boolean isBase) {
		return isBase ? base: externalService;
	}
	
	public static final Mensaje getMensaje(final CodigoMensaje codigo,
			final String...parametros){
		if (ObjectHelper.getObjectHelper().isNull(codigo)) {
			throw new CrossCuttingPCHException(null, null);
		}
		return getStrategy(codigo.isBase())
				.obtenerMensaje(codigo, parametros);
	}
}