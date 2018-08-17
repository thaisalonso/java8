package br.com.tpa.java8.capitulos;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import br.com.tpa.java8.model.Usuario;

public class Capitulo2 {

	public static void main(String[] args) {
		
		//opção 1
		Usuario user1 = new Usuario("Thais", 150);
		Usuario user2 = new Usuario("Elvis", 120);
		Usuario user3 = new Usuario("Catita", 190);
		
		// Arrays.asList é uma maneira simples de criar uma List imutável
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		for(Usuario u: usuarios) {
			System.out.println(u.getNome());
		}
		
		//opcao 2
		Mostrador mostrador = new Mostrador();
		usuarios.forEach(mostrador);
		
		//opcao 3 - classe anônima
		Consumer<Usuario> mostrador2 = new Consumer<Usuario>() {

			@Override
			public void accept(Usuario u) {
				System.out.println(u.getNome());
				
			}
 		};
 		usuarios.forEach(mostrador2);
 		
 		//opcao 4
 		usuarios.forEach(new Consumer<Usuario>( ) {
 			public void accept(Usuario u) {
 				System.out.println(u.getNome());
 			}
 		});
 		
 		//opcao 5 - lambda!
 		Consumer<Usuario> mostrador3 = u -> System.out.println(u.getNome());
 		usuarios.forEach(mostrador3);
 		
 		//não é necessário criar a variável mostrador:
 		usuarios.forEach(u -> System.out.println(u.getNome()));

	}

}
