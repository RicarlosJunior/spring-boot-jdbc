package br.com.jdbc.persistence.entity;

import java.math.BigDecimal;

public class Beneficio {

	private Long id;
	private String descricao;
	private BigDecimal descontoColaborador;
	private Colaborador colaborador;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getDescontoColaborador() {
		return descontoColaborador;
	}
	public void setDescontoColaborador(BigDecimal descontoColaborador) {
		this.descontoColaborador = descontoColaborador;
	}
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	
	@Override
	public String toString() {
		return "Beneficio [id=" + id + ", descricao=" + descricao + ", descontoColaborador=" + descontoColaborador+ ", colaborador=" + colaborador + "]";
	}
	
	
	
}
