package co.edu.uco.pch.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.pch.data.dao.entity.PaisDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.entity.PaisEntity;

public class PaisAzureSqlDAO extends SqlConnection implements PaisDAO {
	
	public PaisAzureSqlDAO (final Connection conexion) {
		super(conexion);
	}

	@Override
	public List<PaisEntity> consultar(PaisEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

}