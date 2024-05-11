package co.edu.uco.pch.data.dao.entity;

import java.util.UUID;

import co.edu.uco.pch.entity.CiudadEntity;

public interface CiudadDAO extends CreateDAO<CiudadEntity>, RetrieveDAO<CiudadEntity>,
        UpdateDAO<CiudadEntity>, DeleteDAO<UUID>{


}