package br.com.tpa.java8.model;

public class Usuario {

	private String nome;
	private int pontos;
	private boolean moderador;
	
	public Usuario() {
		super();
	}

	public Usuario(String nome, int pontos) {
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = false;
	}
	
	public Usuario(String nome, int pontos, boolean moderador) {
		super();
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = moderador;
	}

	public Usuario(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public void tornaModerador() {
		this.moderador = true;
	}

	public boolean isModerador() {
		return moderador;
	}

	public String toString() {
		return "Usu�rio " + nome;
	}

}
