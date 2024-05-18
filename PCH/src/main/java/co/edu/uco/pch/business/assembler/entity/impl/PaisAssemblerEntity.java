package co.edu.uco.pch.business.assembler.entity.impl;

import co.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.business.assembler.dto.impl.PaisAssemblerDTO;
import co.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import co.edu.uco.pch.business.domain.PaisDomain;
import co.edu.uco.pch.dto.PaisDTO;
import co.edu.uco.pch.entity.PaisEntity;

public class PaisAssemblerEntity implements AssemblerEntity<PaisDomain, PaisEntity>{
	
	private static final AssemblerEntity<PaisDomain,PaisEntity> instance= new PaisAssemblerEntity();


	private  PaisAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<PaisDomain,PaisEntity> getInstance(){
		return instance;
	}
	
	@Override
	public PaisDomain toDomain(PaisEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaisEntity toEntity(PaisDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

}
