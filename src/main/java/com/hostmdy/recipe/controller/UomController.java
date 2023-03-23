package com.hostmdy.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.UnitsOfMeasurement;
import com.hostmdy.recipe.service.UomService;

@Controller
@RequestMapping("/uom")
public class UomController {
	
	private final UomService uomService;

	public UomController(UomService uomService) {
		super();
		this.uomService = uomService;
	}
	
	@GetMapping("/show")
	public String showUom(Model model) {
		List<UnitsOfMeasurement>  uoms = uomService.getAllUom();
			model.addAttribute("uoms",uoms);
		
		return "uom/uom-list";
	}
	
	@GetMapping("/new")
	public String uomForm(Model model) {
		UnitsOfMeasurement uom = new UnitsOfMeasurement();
		model.addAttribute("uom", uom);
		return "uom/uom-form";
	}
	
	@PostMapping("/new")
	public String uomSubmit(@ModelAttribute("uom")UnitsOfMeasurement uom) {
		UnitsOfMeasurement craeatedUom = uomService.createdUom(uom);	
		return "redirect:/uom/uom-list";
	}

	@GetMapping("/update/{id}")
	public String  updateUom(Model model,@PathVariable Long id) {
		Optional<UnitsOfMeasurement> uomOpt = uomService.getUomById(id);
		
		if(uomOpt.isPresent()) {
		UnitsOfMeasurement uom = uomOpt.get();
		model.addAttribute("uom",uom);
		}
		return "uom/uom-form";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUom(@PathVariable Long id) {
		uomService.deleteUomById(id);
		return "redirect:/uom/uom-list";
	}
	
}
