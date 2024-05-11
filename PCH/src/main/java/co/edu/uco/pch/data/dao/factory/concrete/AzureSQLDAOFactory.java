package co.edu.uco.pch.data.dao.factory.concrete;


import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.pch.crosscutting.helpers.SQLHelper;
import co.edu.uco.pch.data.dao.entity.CiudadDAO;
import co.edu.uco.pch.data.dao.entity.DepartamentoDAO;
import co.edu.uco.pch.data.dao.entity.PaisDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.data.dao.entity.concrete.azuresql.CiudadAzureSqlDAO;
import co.edu.uco.pch.data.dao.entity.concrete.azuresql.DepartamentoAzureSqlDAO;
import co.edu.uco.pch.data.dao.entity.concrete.azuresql.PaisAzureSqlDAO;
import co.edu.uco.pch.data.dao.factory.DAOFactory;

public final class AzureSQLDAOFactory extends SqlConnection implements DAOFactory {

	public AzureSQLDAOFactory() {
		super();
		abrirConexion();
	}

	@Override
	public void abrirConexion() {
		try {
			String connectionString = "jdbc://<server>:<port>.....";
			setConexion(DriverManager.getConnection(connectionString));
		} catch (final SQLException excepcion) {
			// TODO: handle exception
		} catch (final Exception excepcion) {
			// TODO: handle exception
		}

	}

	@Override
	public void cerrarConexion() {
		SQLHelper.close(getConexion());
	}

	@Override
	public void iniciarTransaccion() {
		SQLHelper.initTransaction(getConexion());
	}

	@Override
	public void confirmarTransaccion() {
		SQLHelper.commit(getConexion());
	}

	@Override
	public void cancelarTransaccion() {
		SQLHelper.rollback(getConexion());
	}

	@Override
	public PaisDAO getPaisDAO() {
		return new PaisAzureSqlDAO(getConexion());
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() {
		return new DepartamentoAzureSqlDAO(getConexion());
	}

	@Override
	public CiudadDAO getCiudadDAO() {
		return new CiudadAzureSqlDAO(getConexion());
	}

}