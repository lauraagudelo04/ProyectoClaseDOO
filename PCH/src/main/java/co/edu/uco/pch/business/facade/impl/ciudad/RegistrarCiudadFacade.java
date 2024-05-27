package co.edu.uco.pch.business.facade.impl.ciudad;

import java.util.List;

import co.edu.uco.pch.business.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.pch.business.facade.FacadeWithReturn;
import co.edu.uco.pch.business.facade.FacadeWithoutReturn;
import co.edu.uco.pch.business.usecase.impl.ciudad.RegistrarCiudad;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.customs.BusinessPCHException;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;

public final class RegistrarCiudadFacade implements FacadeWithoutReturn<CiudadDTO> {
	
	private DAOFactory daoFactory;
	
	public RegistrarCiudadFacade() {
		daoFactory= DAOFactory.getFactory();
	}

	@Override
	public void execute(final CiudadDTO dto) {
		
		daoFactory.iniciarTransaccion();
		
		try {
			var useCase=new RegistrarCiudad(daoFactory);
			var ciudadDomain= CiudadAssemblerDTO.getInstance().toDomain(dto);
			useCase.execute(ciudadDomain);
			//Ejecutar caso de uso
			
			daoFactory.confirmarTransaccion();
		}catch(final PCHException excepcion){
			daoFactory.cancelarTransaccion();
			throw excepcion;
		}catch(final Exception excepcion) {
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario=("Se ha presentado un problema tratando de registrar la información");
			var mensajeTecnico=("Se ha presentado un problema INESPERADO tratando de registrar la información");
			
			throw new BusinessPCHException(mensajeTecnico,mensajeUsuario, excepcion);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

}
