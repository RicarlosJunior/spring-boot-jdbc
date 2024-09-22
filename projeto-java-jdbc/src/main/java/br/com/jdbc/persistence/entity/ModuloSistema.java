package br.com.jdbc.persistence.entity;

import java.util.List;

public class ModuloSistema {

	private Long id;
	private String nome;
	private List<Colaborador> colaboradores;
	
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
	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}
	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}
	@Override
	public String toString() {
		return "ModuloSistema [id=" + id + ", nome=" + nome + ", colaboradores=" + colaboradores + "]";
	}
	
	
	
}
