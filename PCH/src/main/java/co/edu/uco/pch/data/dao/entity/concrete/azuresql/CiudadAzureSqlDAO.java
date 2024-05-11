package co.edu.uco.pch.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.pch.crosscutting.exceptions.customs.DataPCHException;
import co.edu.uco.pch.data.dao.entity.CiudadDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.entity.CiudadEntity;

public class CiudadAzureSqlDAO extends SqlConnection implements CiudadDAO {
	public CiudadAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}
	
	
	@Override
	public final void crear(CiudadEntity data) {
		final StringBuilder sentenciaSql=new StringBuilder();
		sentenciaSql.append("INSERT INTO ciudad (id, nombre , departamento) ");
		sentenciaSql.append("SELECT ?,?,?)");
		try(final PreparedStatement sentenciaSqlPreparada= getConexion().prepareStatement(sentenciaSql.toString())) {
			
			
			sentenciaSqlPreparada.setObject(1,data.getId());
			sentenciaSqlPreparada.setString(2, data.getNombre());
			sentenciaSqlPreparada.setObject(3, data.getId());
			
			sentenciaSqlPreparada.executeUpdate();
			
		}catch (final SQLException excepcion) {
			var mensajeUsuario="Se ha presentado un problema tratando de quedar la ciudad \"${1}\".Por favor intente de nuevo y "
					+ "si el problema persiste contacte al administrador...";
			var mensajeTecnico="Se ha presentado una excepción de tipo SQLException tratando de realizar el insert de la ciudad en la tabla \"Pais\" para mas detalles , revise deforma completa la excepción raiz presentada \"${1}\\\" ";
					throw new DataPCHException(mensajeTecnico,mensajeUsuario,excepcion);
		
		}catch (final Exception excepcion){
			var mensajeUsuario="Se ha presentado un problema tratando de quedar la ciudad \"${1}\".Por favor intente de nuevo y "
					+ "si el problema persiste contacte al administrador...";
			var mensajeTecnico="Se ha presentado un problema INESPERADO con una excepcion de Exception tratando de realizar el insert de ciudad en la tabla \"Pais\" para mas detalles , revise deforma completa la excepción raiz presentada \"${1}\\\" ";
					throw new DataPCHException(mensajeTecnico,mensajeUsuario,excepcion);
		
	}
	}
	@Override
	public List<CiudadEntity> consultar(CiudadEntity data) {

		return null;
	}

	@Override
	public void modificar(CiudadEntity data) {

		
	}

	@Override
	public void eliminar(UUID id) {
		
	}

}
