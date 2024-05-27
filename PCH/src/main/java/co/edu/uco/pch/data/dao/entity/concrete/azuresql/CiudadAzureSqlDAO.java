package co.edu.uco.pch.data.dao.entity.concrete.azuresql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.pch.crosscutting.exceptions.customs.DataPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import co.edu.uco.pch.data.dao.entity.CiudadDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;
import co.edu.uco.pch.entity.PaisEntity;

public class CiudadAzureSqlDAO extends SqlConnection implements CiudadDAO {
	public CiudadAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}
	
	@Override
	public final void crear(final CiudadEntity data) {
		final StringBuilder sentenciaSQL = new StringBuilder();
		
		sentenciaSQL.append("INSERT INTO Ciudad(id,nombre,departamento)");
		sentenciaSQL.append("SELECT ?,?,?");
	
		
		try (final PreparedStatement sentenciaSQLPreparada = getConexion()
				.prepareStatement(sentenciaSQL.toString())){
			
			sentenciaSQLPreparada.setObject(1,data.getId() );
			sentenciaSQLPreparada.setString(2, data.getNombre());
			sentenciaSQLPreparada.setObject(3, data.getDepartamento().getId());
			
			sentenciaSQLPreparada.executeUpdate();
			
		}catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de crear la Ciudad \"${1}\", por favor intente de nuevo y si el problema persiste contacte al administrador...";
			var mensajeTecnico = "Se ha presentado una excepcion de tipo SQLexception tatando de realizar el insert de la ciudad \"${1}\" en la tabla pais"
					+ "de la base de datos azureSql.para mas detalles revise de forma completa la excepcionRaiz presentada ";
			throw new DataPCHException(mensajeTecnico, mensajeUsuario,excepcion);
			
		}catch (final Exception excepcion) {
			var mensajeUsuario = "se ha presentado un problema tratando de crear la ciudad \"${1}\" y si el problemas contacte a el administrador ...";
			var mensajeTecnico = "Se ha presentado una excepcion se tipo SQLexception tatando de realizar el insert de la ciudad \"${1}\" en la tabla pais"
					+ "de la base de datos azureSql.para mas detalles revise de forma completa la excepcionRaiz presentada ";
			throw new DataPCHException(mensajeTecnico, mensajeUsuario,excepcion);
		}
		
	}

	@Override
	public List<CiudadEntity> consultar(final CiudadEntity data) {
	    final StringBuilder sentenciaSql = new StringBuilder();
	    sentenciaSql.append("SELECT c.id, c.nombre, d.id as idDepartamento, d.nombre as nombreDepartamento, p.id as idPais, p.nombre as nombrePais");
	    sentenciaSql.append(" FROM Ciudad c");
	    sentenciaSql.append(" INNER JOIN Departamento d ON c.departamento = d.id");
	    sentenciaSql.append(" INNER JOIN Pais p ON d.pais = p.id");
	    sentenciaSql.append(" WHERE 1=1");

	    final List<Object> parametros = new ArrayList<>();

	    if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
	        sentenciaSql.append(" AND c.id = ?");
	        parametros.add(data.getId());
	    }
	    if (!TextHelper.isNullOrEmpty(data.getNombre())) {
	        sentenciaSql.append(" AND c.nombre = ?");
	        parametros.add(data.getNombre());
	    }
	    if (!ObjectHelper.getObjectHelper().isNull(data.getDepartamento()) && 
	        !ObjectHelper.getObjectHelper().isNull(data.getDepartamento().getId()) && 
	        !data.getDepartamento().getId().equals(UUIDHelper.getDefault())) {
	        sentenciaSql.append(" AND c.departamento = ?");
	        parametros.add(data.getDepartamento().getId());
	    }

	    final List<CiudadEntity> ciudades = new ArrayList<>();

	    try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	        for (int i = 0; i < parametros.size(); i++) {
	            sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
	        }

	        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
	            while (resultado.next()) {
	                CiudadEntity ciudad = CiudadEntity.build();
	                ciudad.setId(UUID.fromString(resultado.getString("id")));
	                ciudad.setNombre(resultado.getString("nombre"));
	                
	                DepartamentoEntity departamento = DepartamentoEntity.build();
	                departamento.setId(UUID.fromString(resultado.getString("idDepartamento")));
	                departamento.setNombre(resultado.getString("nombreDepartamento"));

	                PaisEntity pais = PaisEntity.build();
	                pais.setId(UUID.fromString(resultado.getString("idPais")));
	                pais.setNombre(resultado.getString("nombrePais"));
	                
	                departamento.setPais(pais);
	                ciudad.setDepartamento(departamento);
	                
	                ciudades.add(ciudad);
	            }
	        }

	    } catch (final SQLException excepcion) {
	        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades. Por favor, contacte al administrador del sistema.";
	        var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar la consulta de las ciudades en la tabla \"Ciudad\" de la base de datos Azure SQL.";
	        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);

	    } catch (final Exception excepcion) {
	        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades. Por favor, contacte al administrador del sistema.";
	        var mensajeTecnico = "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la consulta de las ciudades en la tabla \"Ciudad\" de la base de datos Azure SQL.";
	        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
	    }

	    return ciudades;
	}
	@Override
	public void modificar(CiudadEntity data) {
		final StringBuilder sentenciaSQL = new StringBuilder();
		
		sentenciaSQL.append("UPDATE Ciudad SET nombre= ?,departamento= ? WHERE id= ?");

		try (final PreparedStatement sentenciaSQLPreparada = getConexion().prepareStatement(sentenciaSQL.toString())){
			sentenciaSQLPreparada.setObject(3,data.getId() );
			sentenciaSQLPreparada.setString(1, data.getNombre());
			sentenciaSQLPreparada.setObject(2, data.getDepartamento().getId());
			
			sentenciaSQLPreparada.executeUpdate();
			
		}catch (final SQLException excepcion) {
			var mensajeUsuario = "se ha presentado un prblemao tratando de modificar la ciudad \\\"${1}\\\" y si el problemas contacte a el administrador ...";
			var mensajeTecnico = "Se ha presentado una excepcion se tipo SQLexception tatando de realizar el update de la ciudad \"${1}\" en la tabla pais"
					+ "de la base de datos azureSql.para mas detalles revise de forma completa la excepcionRaiz presentada ";
			throw new DataPCHException(mensajeTecnico, mensajeUsuario,excepcion);
			
		}catch (final Exception excepcion) {
			var mensajeUsuario = "se ha presentado un problema tratando de modificar la ciudad \"${1}\" y si el problemas contacte a el administrador ...";
			var mensajeTecnico = "Se ha presentado una excepcion se tipo SQLexception tatando de realizar el update de la ciudad \"${1}\" en la tabla pais"
					+ "de la base de datos azureSql.para mas detalles revise de forma completa la excepcionRaiz presentada ";
			throw new DataPCHException(mensajeTecnico, mensajeUsuario,excepcion);
		
	}
	}
	
	 @Override
	    public void eliminar(UUID id) {
	        final StringBuilder sentenciaSQL = new StringBuilder();

	        sentenciaSQL.append("DELETE FROM Ciudad WHERE id = ?");

	        try (final PreparedStatement sentenciaSQLPreparada = getConexion().prepareStatement(sentenciaSQL.toString())){
	            sentenciaSQLPreparada.setObject(1, id);

	            sentenciaSQLPreparada.executeUpdate();
	        }catch(final SQLException excepcion) {
	            var mensajeUsuario = "se ha presentado un prblemao tratando de eliminar la ciudad \"${1}\" y si el problemas contacte a el administrador ...";
	            var mensajeTecnico = "Se ha presentado una excepcion se tipo SQLexception tatando de realizar el delete de la ciudad \\\"${1}\\\" en la tabla pais\"\r\n" + "+ \"de la base de datos azureSql.para mas detalles revise de forma completa la excepcionRaiz presentada";
	            throw new DataPCHException(mensajeTecnico, mensajeUsuario);

	        }catch(final Exception excepcion) {
	            var mensajeUsuario = "\"se ha presentado un prblema tratando de eliminar la ciudad \\\"${1}\\\" y si el problemas contacte a el administrador ...\"";
	            var mensajeTecnico = "Se ha presentado una excepcion se tipo SQLexception tatando de realizar el delete de la ciudad \"${1}\" en la tabla pais" + "de la base de datos azureSql.para mas detalles revise de forma completa la excepcionRaiz presentada ";
	            throw new DataPCHException(mensajeTecnico, mensajeUsuario);
	        }
	    }

}
