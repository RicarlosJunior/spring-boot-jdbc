package br.com.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.jdbc.persistence.BeneficioDAO;
import br.com.jdbc.persistence.ColaboradorDAO;
import br.com.jdbc.persistence.ContatoDAO;
import br.com.jdbc.persistence.ModuloSistemaDAO;
import br.com.jdbc.persistence.entity.Beneficio;
import br.com.jdbc.persistence.entity.Colaborador;
import br.com.jdbc.persistence.entity.Contato;
import br.com.jdbc.persistence.entity.ModuloSistema;

@Component
public class Main implements CommandLineRunner {

	
	private final ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
	private final ContatoDAO contatoDAO = new ContatoDAO();
	private final BeneficioDAO beneficioDAO = new BeneficioDAO();
	private final ModuloSistemaDAO moduloSistemaDAO = new ModuloSistemaDAO();
	
	
	@Override
	public void run(String... args) throws Exception {
		var flyway = Flyway.configure()
				.dataSource("jdbc:mysql://localhost:3306/java-jdbc", "root", "root")
				.load();
		
		flyway.migrate();
		
		
		System.out.println(colaboradorDAO.findByIdFull(17L));
		
	/*	
		var moduloSistemaTI = new ModuloSistema();
		moduloSistemaTI.setNome("TI");
		moduloSistemaDAO.insert(moduloSistemaTI);
		
		var moduloSistemaContabilidade = new ModuloSistema();
		moduloSistemaContabilidade.setNome("Contabilidade");
		moduloSistemaDAO.insert(moduloSistemaContabilidade);
		
		var moduloSistemaVendas = new ModuloSistema();
		moduloSistemaVendas.setNome("Vendas");
		moduloSistemaDAO.insert(moduloSistemaVendas);
		
		
		var colaborador = new Colaborador();
		colaborador.setNome("Ricarlos");
		colaborador.setMatricula("0001");
		
		colaborador.setModulos(new ArrayList<>());
		
		
		colaborador.getModulos().add(moduloSistemaTI);
		colaborador.getModulos().add(moduloSistemaContabilidade);
		colaborador.getModulos().add(moduloSistemaVendas);
		
		colaboradorDAO.insertWithModuloSistema(colaborador);
		
		/*
		
		
		/*var colaborador = new Colaborador();
		colaborador.setNome("Ricarlos");
		colaborador.setMatricula("0001");
		colaboradorDAO.insert(colaborador);
		
		System.out.println("****************************************************");
		
		var contato = new Contato();
		contato.setTipo("email");
		contato.setDescricao("ricarlosjr@hotmail.com");
		contato.setColaborador(colaborador);
		contatoDAO.insert(contato);
		
		System.out.println("****************************************************");
		
		
		
		var beneficioPlanoSaude = new Beneficio();
		beneficioPlanoSaude.setDescricao("Plano Bradesco Saude");
		beneficioPlanoSaude.setDescontoColaborador(new BigDecimal(0));
		beneficioPlanoSaude.setColaborador(colaborador);
		beneficioDAO.insert(beneficioPlanoSaude);
		
		
		
		var beneficioPlanoOdontologico = new Beneficio();
		beneficioPlanoOdontologico.setDescricao("Plano UniOdonto");
		beneficioPlanoOdontologico.setDescontoColaborador(new BigDecimal(30));
		beneficioPlanoOdontologico.setColaborador(colaborador);
		beneficioDAO.insert(beneficioPlanoOdontologico);
		
		System.out.println("****************************************************");*/
		
		
		
		
	}
}
