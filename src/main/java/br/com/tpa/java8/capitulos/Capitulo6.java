package br.com.tpa.java8.capitulos;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import br.com.tpa.java8.model.Usuario;

public class Capitulo6 {

	public static void main(String[] args) {
		//method reference
		Usuario user1 = new Usuario("Thais", 150);
		Usuario user2 = new Usuario("Elvis", 120);
		Usuario user3 = new Usuario("Catita", 190);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		//o method reference é traduzido para uma interface funcional, assim como uma expressão lambda
		usuarios.forEach(Usuario::tornaModerador);
		
		//os códigos abaixo geram o mesmo consumidor
		Consumer<Usuario> tornaModerador = Usuario::tornaModerador;
				
		Consumer<Usuario> tornaModerador1 = u -> u.tornaModerador();
		
		//usando comparing
		usuarios.sort(Comparator.comparing(Usuario::getNome));

		//comparingInt para evitar o boxing desnecessário
		usuarios.sort(Comparator.comparingInt(Usuario::getPontos));
		
		//thenComparing para colocar dois crit�rios de comparação
		//existe a variação thenComparingInt, para evitar o boxing de primitivos
		//ordena pelos pontos e, no caso de empate, ordena pelo nome
		Comparator<Usuario> c = Comparator.comparingInt(Usuario::getPontos).thenComparing(Usuario::getNome);
		
		//tudo em apenas uma linha
		usuarios.sort(Comparator.comparingInt(Usuario::getPontos).thenComparing(Usuario::getNome));
		
		//nullsLast - posiciona os nulos no fim
		usuarios.sort(Comparator.nullsLast(Comparator.comparing(Usuario::getNome)));

		//reversed - ordena por ordem decrescente
		usuarios.sort(Comparator.comparingInt(Usuario::getPontos).reversed());
		
		//referenciando todos que recebem argumentos
		//method reference
		usuarios.forEach(System.out::println);
		
		//lambda equivalente:
		usuarios.forEach(u -> System.out.println(u));
		
		//equivalente no Java 7:
		for(Usuario u: usuarios) {
			System.out.println(u);
		}
		
		//constructor reference
		//referencia o construtor padrão
		Supplier<Usuario> criadorDeUsuarios = Usuario::new;
		Usuario novo = criadorDeUsuarios.get();
		
		//referencia o construtor que recebe uma String. precisa de uma interface funcional (Function)
		Function<String, Usuario> criadorDeUsuarios2 = Usuario::new;
		Usuario thais = criadorDeUsuarios2.apply("Thaís");
		Usuario leandro = criadorDeUsuarios2.apply("Leandro");
		
		//referencia o construtor com dois par�metros
		BiFunction<String, Integer, Usuario> criadorDeUsuarios3 = Usuario::new;
		Usuario anja = criadorDeUsuarios3.apply("Anja", 50);
		Usuario bete = criadorDeUsuarios3.apply("Bete", 100);
	}

}
