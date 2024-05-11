package co.edu.uco.pch.entity;

import java.util.UUID;

public final class CiudadEntity {
	private UUID id;
	private String nombre;
	private DepartamentoEntity departamento;
	
	public CiudadEntity() {
		super();
	}
	
	public CiudadEntity(final UUID id, final String nombre, final DepartamentoEntity departamento) {
		setId(id);
		setNombre(nombre);
		setDepartamento(departamento);
		
	}
	public static final CiudadEntity build() {
		return new CiudadEntity();
	}
	
	public final UUID getId() {
		return id;
	}
	
	public final CiudadEntity setId(final UUID id) {
		this.id = id;
		return this;
	}
	public final String getNombre() {
		return nombre;
	}
	public final CiudadEntity setNombre(final String nombre) {
		this.nombre = nombre;
		return this;
	}
	public final DepartamentoEntity getDepartamento() {
		return departamento;
	}
	public final CiudadEntity setDepartamento(final DepartamentoEntity departamento) {
		this.departamento = departamento;
		return this;
	}


}