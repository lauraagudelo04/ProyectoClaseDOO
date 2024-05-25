package co.edu.uco.pch.business.assembler.dto.impl;

import static co.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.List;

import co.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.business.domain.PaisDomain;
import co.edu.uco.pch.dto.PaisDTO;

public final class PaisAssemblerDTO implements AssemblerDTO<PaisDomain, PaisDTO> {

	private static final AssemblerDTO<PaisDomain, PaisDTO> instance = new PaisAssemblerDTO();

	private PaisAssemblerDTO() {
		super();
	}

	public static final AssemblerDTO<PaisDomain, PaisDTO> getInstance() {
		return instance;
	}

	@Override
	public final PaisDomain toDomain(final PaisDTO data) {
		var paisDtoTmp = getObjectHelper().getDefaultValue(data, PaisDTO.build());
		return PaisDomain.build(paisDtoTmp.getId(), paisDtoTmp.getNombre());
	}

	@Override
	public final PaisDTO toDTO(final PaisDomain domain) {
		var paisDomainTmp = getObjectHelper().getDefaultValue(domain, PaisDomain.build());
		return PaisDTO.build().setId(paisDomainTmp.getId()).setNombre(paisDomainTmp.getNombre());
	}

	@Override
	public List<PaisDomain> toDomainCollection(List<PaisDTO> entityCollection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisDTO> toDTOCollection(List<PaisDomain> domainCollection) {
		// TODO Auto-generated method stub
		return null;
	}

}
