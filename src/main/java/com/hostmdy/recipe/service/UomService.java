package com.hostmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.recipe.domain.UnitsOfMeasurement;

public interface UomService {

	Optional<UnitsOfMeasurement> getUomById(Long id);

	List<UnitsOfMeasurement> getAllUom();

	UnitsOfMeasurement createdUom(UnitsOfMeasurement uom);

	 void deleteUomById(Long id);
		// TODO Auto-generated method stub
		
	
	
}
