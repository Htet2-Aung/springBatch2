package com.hostmdy.recipe.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private Integer preTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	
	@Lob
	private String description;
	
	@Lob
	private String directions;
	
	@Lob
	private byte[] image;
	
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Notes notes;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>() ;
	
	@ManyToMany
	private Set<Category> categories = new HashSet<>();
	
	public Recipe() {
		// TODO Auto-generated constructor stub
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public String getTitle() {
		return title;
	}


	
	public void setTitle(String title) {
		if (!title.isBlank())
		this.title = title;
		
	}


	public Set<Category> getCategories() {
		return categories;
	}


	public Integer getPreTime() {
		return preTime;
	}


	public void setPreTime(Integer preTime) {
		this.preTime = preTime;
	}


	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}


	public Integer getServings() {
		return servings;
	}


	public void setServings(Integer servings) {
		this.servings = servings;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDirections() {
		return directions;
	}


	public void setDirections(String directions) {
		this.directions = directions;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] byteObjects) {
		this.image = byteObjects;
	}


	public Difficulty getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}


	public Notes getNotes() {
		return notes;
	}


	public void setNotes(Notes notes) {
		this.notes = notes;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<Category> getCategores() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
}
