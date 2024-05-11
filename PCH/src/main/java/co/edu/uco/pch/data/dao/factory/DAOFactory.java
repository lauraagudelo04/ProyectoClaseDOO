package co.edu.uco.pch.data.dao.factory;

import co.edu.uco.pch.data.dao.entity.CiudadDAO;
import co.edu.uco.pch.data.dao.entity.DepartamentoDAO;
import co.edu.uco.pch.data.dao.entity.PaisDAO;
import co.edu.uco.pch.data.dao.factory.concrete.AzureSQLDAOFactory;

public interface DAOFactory {

	default AzureSQLDAOFactory getFactory() {
		return new AzureSQLDAOFactory();
	}

	void abrirConexion();

	void cerrarConexion();

	void iniciarTransaccion();

	void confirmarTransaccion();

	void cancelarTransaccion();

	PaisDAO getPaisDAO();

	DepartamentoDAO getDepartamentoDAO();

	CiudadDAO getCiudadDAO();
}
