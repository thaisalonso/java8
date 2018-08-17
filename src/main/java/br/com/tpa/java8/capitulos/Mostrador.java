package br.com.tpa.java8.capitulos;
import java.util.function.Consumer;

import br.com.tpa.java8.model.Usuario;

class Mostrador implements Consumer<Usuario> {
	
	@Override
	public void accept(Usuario u) {
		System.out.println(u.getNome());
		
	}

}
