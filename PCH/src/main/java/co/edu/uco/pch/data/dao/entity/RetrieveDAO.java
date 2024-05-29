package co.edu.uco.pch.data.dao.entity;

import java.util.List;

public interface RetrieveDAO <E>{
	List <E> consultar (E data);
}
