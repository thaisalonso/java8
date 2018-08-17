package br.com.tpa.java8.model;

import java.math.BigDecimal;
import java.nio.file.Path;

public class Product {
	
	private String name;
	
	private Path file;
	
	private BigDecimal price;

	public Product(String nome, Path file, BigDecimal price) {
		super();
		this.name = nome;
		this.file = file;
		this.price = price;
	}

	public String getNome() {
		return name;
	}

	public Path getFile() {
		return file;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return this.name;
	}


	
}
