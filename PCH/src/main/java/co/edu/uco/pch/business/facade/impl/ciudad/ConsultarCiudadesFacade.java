package co.edu.uco.pch.business.facade.impl.ciudad;

import java.util.List;

import co.edu.uco.pch.business.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.pch.business.facade.FacadeWithReturn;
import co.edu.uco.pch.business.usecase.impl.ciudad.ConsultarCiudades;
import co.edu.uco.pch.business.usecase.impl.ciudad.RegistrarCiudad;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.customs.BusinessPCHException;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;

public final class ConsultarCiudadesFacade implements FacadeWithReturn<CiudadDTO, List<CiudadDTO>> {
	
	private DAOFactory daoFactory;
	
	public ConsultarCiudadesFacade() {
		daoFactory= DAOFactory.getFactory();
	}
	@Override
	public List<CiudadDTO> execute(final CiudadDTO dto) {
		//daoFactory.iniciarTransaccion();
		try {
			var useCase=new ConsultarCiudades(daoFactory);
			var ciudadDomain= CiudadAssemblerDTO.getInstance().toDomain(dto);
			var resultadosDomain= useCase.execute(ciudadDomain);
			return CiudadAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
			
		}catch(final PCHException excepcion){
			//daoFactory.cancelarTransaccion();
			throw excepcion;
		}catch(final Exception excepcion) {
			
			//daoFactory.cancelarTransaccion();
			var mensajeUsuario=("Se ha presentado un problema tratando de consultar la información de las ciudades");
			var mensajeTecnico=("Se ha presentado un problema INESPERADO tratando de consultar la información de las ciudades");
			
			throw new BusinessPCHException(mensajeTecnico,mensajeUsuario, excepcion);
		
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
