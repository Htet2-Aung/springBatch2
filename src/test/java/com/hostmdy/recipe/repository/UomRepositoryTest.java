package com.hostmdy.recipe.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hostmdy.recipe.domain.UnitOfMeasurement;

@DataJpaTest
class UomRepositoryTest {

	@Autowired
	UomRepository uomRepository;

	@Test
	void findByOunceUom() {
		String testUom = "ounce";
		
		Optional<UnitOfMeasurement> uomOptional = uomRepository.findByUom(testUom);
		
		if(uomOptional.isPresent())
			assertEquals(testUom, uomOptional.get().getUom());
		else
			fail("Uom with name="+testUom+" is not found");
	}

}
