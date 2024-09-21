package br.com.jdbc.persistence.entity;

public class Colaborador {
		
	private Long id;
	private String nome;
	private String matricula;
	private Contato contato;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	@Override
	public String toString() {
		return "Colaborador [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", contato=" + contato + "]";
	}
}
