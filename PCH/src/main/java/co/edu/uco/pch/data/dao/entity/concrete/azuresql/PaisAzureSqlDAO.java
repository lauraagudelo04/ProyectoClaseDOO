package co.edu.uco.pch.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.pch.crosscutting.exceptions.customs.DataPCHException;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.data.dao.entity.PaisDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.entity.PaisEntity;

public class PaisAzureSqlDAO extends SqlConnection implements PaisDAO {
	
	public PaisAzureSqlDAO (final Connection conexion) {
		super(conexion);
	}

	@Override
	public List<PaisEntity> consultar(PaisEntity data) {
		   final StringBuilder sentenciaSql = new StringBuilder();
		    sentenciaSql.append("SELECT id, nombre FROM Pais WHERE 1=1");

		    if (data != null) {
		        if (data.getId() != null) {
		            sentenciaSql.append(" AND id = ?");
		        }
		        if (!TextHelper.isNullOrEmpty(data.getNombre())) {
		            sentenciaSql.append(" AND nombre = ?");
		        }
		    }

		    final List<PaisEntity> paises = new ArrayList<>();

		    try (final PreparedStatement sentenciaSqlPreparada = getConexion()
		            .prepareStatement(sentenciaSql.toString())) {

		        int index = 1;

		        if (data != null) {
		            if (data.getId() != null) {
		                sentenciaSqlPreparada.setObject(index++, data.getId());
		            }
		            if (!TextHelper.isNullOrEmpty(data.getNombre())) {
		                sentenciaSqlPreparada.setString(index++, data.getNombre());
		            }
		        }

		        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
		            while (resultado.next()) {
		                PaisEntity pais = new PaisEntity();
		                pais.setId((UUID) resultado.getObject("id"));
		                pais.setNombre(resultado.getString("nombre"));
		                paises.add(pais);
		            }
		        }

		    } catch (final SQLException excepcion) {
		        var mensajeUsuario = "Se ha presentado un problema tratando de consultar los países. Por favor, contacte al administrador del sistema.";
		        var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar la consulta de los países en la tabla \"Pais\" de la base de datos Azure SQL.";

		        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);

		    } catch (final Exception excepcion) {
		        var mensajeUsuario = "Se ha presentado un problema tratando de consultar los países. Por favor, contacte al administrador del sistema.";
		        var mensajeTecnico = "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la consulta de los países en la tabla \"Pais\" de la base de datos Azure SQL.";

		        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		    }

		    return paises;
		}
}