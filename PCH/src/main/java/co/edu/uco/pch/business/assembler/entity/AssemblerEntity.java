package co.edu.uco.pch.business.assembler.entity;

import co.edu.uco.pch.business.assembler.Assembler;

public interface AssemblerEntity<D,K> extends Assembler<D,K > {
	K toEntity(D domain);
}
