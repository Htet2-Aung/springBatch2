package com.hostmdy.recipe.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hostmdy.recipe.domain.UnitsOfMeasurement;

@DataJpaTest
class UomRepositoryTest {

	@Autowired
	UomRepository uomRepository;
	
	@Test
	void findByOunce() {
		String testUom = "ounce";
		
		Optional<UnitsOfMeasurement> uomOpt =uomRepository.findByUom(testUom);
		
		if(uomOpt.isPresent())
			assertEquals(testUom, uomOpt.get().getUom());
		
		else
			fail("Uom with name = "+testUom+"is not found");
	}

}
