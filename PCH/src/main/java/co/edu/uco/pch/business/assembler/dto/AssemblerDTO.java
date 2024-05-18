package co.edu.uco.pch.business.assembler.dto;

import co.edu.uco.pch.business.assembler.Assembler;

public interface AssemblerDTO <D,K> extends Assembler<D, K>{
	K toDTO(D domain);
	
}
