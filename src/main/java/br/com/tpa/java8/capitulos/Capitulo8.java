package br.com.tpa.java8.capitulos;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.tpa.java8.model.Usuario;

public class Capitulo8 {

	public static void main(String[] args) {
				
		Usuario user1 = new Usuario("Thais", 50);
		Usuario user2 = new Usuario("Elvis", 120);
		Usuario user3 = new Usuario("Catita", 190);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		// 8.1 ordenando Streams
		usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.sorted(Comparator.comparing(Usuario::getNome));
		
		//colocando o resultado em uma lista
		List<Usuario> usuariosFiltrados = usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.sorted(Comparator.comparing(Usuario::getNome))
				.collect(Collectors.toList());
		
		//findAny - devolve qualquer um dos elementos
		Optional<Usuario> usuarioOpcional = usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.findAny();
		
		//findFirst - devolve o primeiro elemento
		Optional<Usuario> usuarioOpcional2 = usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.findFirst();
		
		//peek - executa uma tarefa toda vez que processar um elemento
		usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.peek(System.out::println)
			.findAny();
		
		//testar predicados - anyMatch, allMatch e noneMatch
		boolean hasModerator = usuarios.stream()
				.anyMatch(Usuario::isModerador);
			
		boolean hasModeratorLambda = usuarios.stream()
				.anyMatch(u -> u.isModerador());
		

	}

}
