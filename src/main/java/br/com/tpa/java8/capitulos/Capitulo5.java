package br.com.tpa.java8.capitulos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.tpa.java8.model.Usuario;

public class Capitulo5 {

	public static void main(String[] args) {
		
		//Comparator é uma interface funcional, ou seja, possui apenas um  método abstrato (compare). Portanto, pode ser instanciada em uma expressão lambda.

		Usuario user1 = new Usuario("Thais", 150);
		Usuario user2 = new Usuario("Elvis", 120);
		Usuario user3 = new Usuario("Catita", 190);

		// Arrays.asList é uma maneira simples de criar uma List imutável - pode ser assim?
		//List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(user1);
		usuarios.add(user2);
		usuarios.add(user3);
		
		// opção 1
		Comparator<Usuario> comparator = new Comparator<Usuario>() {
			@Override
			public int compare(Usuario u1, Usuario u2) {
				return u1.getNome().compareTo(u2.getNome());
			}
		};

		Collections.sort(usuarios, comparator);

		//opção 2
		Comparator<Usuario> comparator2 = (u1, u2) -> u1.getNome().compareTo(u2.getNome());
		Collections.sort(usuarios, comparator2);
		
		//opção 3
		Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		
		//opção 4 - método default sort na interface List
		usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		
		//opção 5 - comparing (fábrica de Comparators)
		Comparator<Usuario> comparator3 = Comparator.comparing(u -> u.getNome());
		usuarios.sort(comparator3);
		
		//tudo em uma linha:
		usuarios.sort(Comparator.comparing(u -> u.getNome()));
		
		//com static import (pesquisar)
		//usuarios.sort(comparing(u -> u.getNome()));
		
		//indexando pela ordem natural
		List<String> palavras = Arrays.asList("Casa do Código", "Alura", "Caelum");
		palavras.sort(Comparator.naturalOrder());
	}

}
