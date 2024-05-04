package co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data;

import static co.edu.uco.pch.crosscutting.helpers.TextHelper.concatenate;
import static co.edu.uco.pch.crosscutting.helpers.TextHelper.UNDERLINE;

public enum CodigoMensaje {
	
	M00001(TipoMensaje.TECNICO,CategoriaMensaje.ERRROR,"00001", true),
	M00002(TipoMensaje.USUARIO,CategoriaMensaje.ERRROR,"00002", true),
	M00003(TipoMensaje.TECNICO,CategoriaMensaje.ERRROR,"00003", true),
	M00004(TipoMensaje.TECNICO,CategoriaMensaje.ERRROR,"00003", true),
	;
	
	private TipoMensaje tipo;
	private CategoriaMensaje categoria;
	private String codigo;
	private boolean base;
	
	private CodigoMensaje(final TipoMensaje tipo, 
			final CategoriaMensaje categoria, final String codigo, 
			final boolean base) {
		setTipo(tipo);
		setCategoria(categoria);
		setCodigo(codigo);
		setBase(base);
	}

	public final TipoMensaje getTipo() {
		return tipo;
	}

	public final CategoriaMensaje getCategoria() {
		return categoria;
	}

	public final String getCodigo() {
		return codigo;
	}

	private final void setTipo(final TipoMensaje tipo) {
		this.tipo = tipo;
	}

	private final void setCategoria(final CategoriaMensaje categoria) {
		this.categoria = categoria;
	}

	private final void setCodigo(final String codigo) {
		this.codigo = codigo;
	}
	
	
	private final void setBase(final boolean base) {
		this.base = base;
	}

	public final boolean isBase() {
		return base;
	}

	public String getIdentificador() {
		return concatenate(getTipo().name(), UNDERLINE,
				getCategoria().name(), UNDERLINE,
				getCodigo());
		}
	
	public static void main(String[] args) {
		System.out.println(M00001.getIdentificador());
	}
}
