package com.hostmdy.recipe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.domain.UnitOfMeasurement;

public interface UomRepository extends CrudRepository<UnitOfMeasurement, Long>{

	Optional<UnitOfMeasurement> findByUom(String uom);//query method
}
