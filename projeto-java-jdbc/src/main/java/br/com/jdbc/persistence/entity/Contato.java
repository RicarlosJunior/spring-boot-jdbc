package br.com.jdbc.persistence.entity;

public class Contato {
	
	private Long id;
	private String tipo;
	private String descricao;
	private Colaborador colaborador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", tipo=" + tipo + ", descricao=" + descricao + ", colaborador=" + colaborador+ "]";
	}
	
	
}
