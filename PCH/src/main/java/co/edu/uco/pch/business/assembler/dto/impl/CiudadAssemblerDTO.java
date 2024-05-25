package co.edu.uco.pch.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.dto.CiudadDTO;

public class CiudadAssemblerDTO implements AssemblerDTO<CiudadDomain, CiudadDTO>{

	private static final AssemblerDTO<CiudadDomain, CiudadDTO> instance=new CiudadAssemblerDTO();
	
	private CiudadAssemblerDTO() {
		super();
	}
	
	public static final AssemblerDTO<CiudadDomain, CiudadDTO> getInstance(){
		return instance;
	}
	@Override
	public CiudadDomain toDomain(CiudadDTO data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiudadDTO toDTO(CiudadDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CiudadDomain> toDomainCollection(final List<CiudadDTO> dtoCollection) {
		var dtoCollectionTmp=ObjectHelper.getObjectHelper().getDefaultValue(dtoCollection, new ArrayList<CiudadDTO>());
		
		var resultadosDomain=new ArrayList<CiudadDomain>();
		
		for(CiudadDTO ciudadDto: dtoCollection) {
			var ciudadDomainTmp=toDomain(ciudadDto);
			resultadosDomain.add(ciudadDomainTmp);
		}
		return resultadosDomain;
	}

	@Override
	public final List<CiudadDTO> toDTOCollection(final List<CiudadDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<CiudadDomain>());
		
		return domainCollection.stream().map(this::toDTO).toList();
	}
	
	//TAREA

}
