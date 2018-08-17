package br.com.tpa.java8.capitulos;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.tpa.java8.model.Usuario;

public class Capitulo4 {

	public static void main(String[] args) {
		// opção 1
		Predicate<Usuario> predicado = new Predicate<Usuario>() {
			@Override
			public boolean test(Usuario u) {
				return u.getPontos() > 160;
			}
		};
		Usuario user1 = new Usuario("Thais", 150);
		Usuario user2 = new Usuario("Elvis", 120);
		Usuario user3 = new Usuario("Catita", 190);
		
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(user1);
		usuarios.add(user2);
		usuarios.add(user3);
		
		usuarios.removeIf(predicado);
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		// opção 2 - com lambda
		usuarios.removeIf(u -> u.getPontos() > 160);
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
	}
	
}
