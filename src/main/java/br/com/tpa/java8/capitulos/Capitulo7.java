package br.com.tpa.java8.capitulos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

import br.com.tpa.java8.model.Usuario;

public class Capitulo7 {

	public static void main(String[] args) {

		Usuario user1 = new Usuario("Thais", 50);
		Usuario user2 = new Usuario("Elvis", 120);
		Usuario user3 = new Usuario("Catita", 190);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

		// tornando moderadores os 3 usuários com mais pontos
		usuarios.sort(Comparator.comparing(Usuario::getPontos).reversed());
		usuarios.subList(0, 3).forEach(Usuario::tornaModerador);

		// filtrar uma coleção com stream
		// tornando moderados os usu�rios com mais de 100 pontos

		// antigo
		for (Usuario usuario : usuarios) {
			if (usuario.getPontos() > 100) {
				usuario.tornaModerador();
			}
		}

		// com stream - dessa forma retorna usuários que tem menos de 100 pontos,
		//pois, o método filter não altera o stream original
		usuarios.stream()
				.filter(u -> u.getPontos() > 100);
		usuarios.forEach(System.out::println);
		
		//dessa forma retorna apenas usu�rios que tem mais de 100 pontos
		Stream<Usuario> stream = usuarios.stream()
										 .filter(u -> u.getPontos() > 100);
		stream.forEach(System.out::println);
		
		//reduzido:
		usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.forEach(System.out::println);
		
		//colocar o resultado de um stream em uma lista
		List<Usuario> maisQue100 = usuarios.stream()
											.filter(u -> u.getPontos() > 100)
											.collect(toList());
		
		//sem map - listar os pontos de todos os usuários
		List<Integer> pontos = new ArrayList<>();
		usuarios.forEach(u -> pontos.add(u.getPontos()));
		
		//com map e lambda
		List<Integer> pontos1 = usuarios.stream()
				.map(u -> u.getPontos())
				.collect(Collectors.toList());
		
		//com map e method reference
		List<Integer> pontos2 = usuarios.stream()
				.map(Usuario::getPontos)
				.collect(Collectors.toList());
		
		//IntStream  e família de Streams 
		//São usados para evitar o autoboxing (converter de primitivo para objeto)
		IntStream stream2 = usuarios.stream()
				.mapToInt(Usuario::getPontos);
		
		//fica mais fácil obter a média de pontos
		double pontuacaoMedia = usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.average()
				.getAsDouble();
		
		//Optional 
		
		//abaixo retorna zero caso a lista de usuários estiver vazia
		double pontuacaoMedia2 = usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.average()
				.orElse(0.0);
		
		//lança exceção caso a lista de usuários estiver vazia
		double pontuacaoMedia3 = usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.average()
				.orElseThrow(IllegalStateException::new);
		
	}
}
