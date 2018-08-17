package br.com.tpa.java8.capitulos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.tpa.java8.model.Usuario;

public class Capitulo9 {

	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Thais", 50, true);
		Usuario user2 = new Usuario("Elvis", 120);
		Usuario user3 = new Usuario("Catita", 190, true);
		Usuario user4 = new Usuario("Anja", 120);
		Usuario user5 = new Usuario("Leandro", 100);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);
		
		//mapear todos os usuários utilizando o nome como chave:
		Map<String, Usuario> nameToUser = usuarios.stream()
												  .collect(Collectors.toMap(
														  Usuario::getNome,
														  Function.identity()));
		
		//mapa em que a chave é a pontuação e o valor é a lista de usuários que possuem essa pontuação
		
		//versão anterior
		Map<Integer, List<Usuario>> pontuacao = new HashMap<>();
		
		for (Usuario u: usuarios) {
			if (!pontuacao.containsKey(u.getPontos())) {
				pontuacao.put(u.getPontos(), new ArrayList<>());
			}
			pontuacao.get(u.getPontos()).add(u);
		}
		
		System.out.println(pontuacao);
		
		//no Java 8
		Map<Integer, List<Usuario>> pontuacao2 = new HashMap<>();
		
		for (Usuario u: usuarios) {
			pontuacao2.computeIfAbsent(u.getPontos(), user -> new ArrayList<>()).add(u);
		}
		
		System.out.println(pontuacao2);
		
		//usando o groupingBy
		Map<Integer, List<Usuario>> pontuacao3 = usuarios
												.stream()
												.collect(Collectors.groupingBy(Usuario::getPontos));
		
		System.out.println(pontuacao3);
		
		//partitionBy - particionar usuários entre moderadores e não moderadores
		Map<Boolean, List<Usuario>> moderadores = usuarios		
												.stream()
												.collect(Collectors.partitioningBy(Usuario::isModerador));
		
		System.out.println(moderadores);
		
		//apenas o nome do usuário:
		Map<Boolean, List<String>> nomesPorTipo = usuarios
													.stream()
													.collect(
														Collectors.partitioningBy(
																Usuario::isModerador,
																Collectors.mapping(Usuario::getNome, 
																		Collectors.toList())));
		
		System.out.println(nomesPorTipo);
		
		//summingInt - soma dos pontos dos usuários moderadores e não moderadores
		Map<Boolean, Integer> pontuacaoPorTipo = usuarios
												.stream()
												.collect(
													Collectors.partitioningBy(
															Usuario::isModerador,
															Collectors.summingInt((Usuario::getPontos))));
		
		System.out.println(pontuacaoPorTipo);
		
		//joining - concatenar os nomes dos usuários
		String nomes = usuarios
					.stream()
					.map(Usuario::getNome)
					.collect(Collectors.joining(", "));

		System.out.println(nomes);
		
	}

}
