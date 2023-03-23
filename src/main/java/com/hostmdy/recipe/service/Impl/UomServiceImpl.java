package com.hostmdy.recipe.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.domain.UnitsOfMeasurement;
import com.hostmdy.recipe.repository.UomRepository;
import com.hostmdy.recipe.service.UomService;

@Service
public class UomServiceImpl implements UomService{

	private final UomRepository uomRepository;
	
	public UomServiceImpl(UomRepository uomRepository) {
		super();
		this.uomRepository = uomRepository;
	}
	@Override
	public Optional<UnitsOfMeasurement> getUomById(Long id) {
		// TODO Auto-generated method stub
		return uomRepository.findById(id);
	}



	@Override
	public List<UnitsOfMeasurement> getAllUom() {
		// TODO Auto-generated method stub
		return (List<UnitsOfMeasurement>) uomRepository.findAll();
	}



	@Override
	public UnitsOfMeasurement createdUom(UnitsOfMeasurement uom) {
		// TODO Auto-generated method stub
		return uomRepository.save(uom);
	}
	@Override
	public void deleteUomById(Long id) {
		// TODO Auto-generated method stub
		uomRepository.deleteById(id);
	}

}
