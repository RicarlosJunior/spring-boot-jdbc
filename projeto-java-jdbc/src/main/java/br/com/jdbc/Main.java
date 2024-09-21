package br.com.jdbc;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.jdbc.persistence.ColaboradorDAO;
import br.com.jdbc.persistence.ContatoDAO;
import br.com.jdbc.persistence.entity.Colaborador;
import br.com.jdbc.persistence.entity.Contato;

@Component
public class Main implements CommandLineRunner {

	
	private final ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
	private final ContatoDAO contatoDAO = new ContatoDAO();
	
	
	@Override
	public void run(String... args) throws Exception {
		var flyway = Flyway.configure()
				.dataSource("jdbc:mysql://localhost:3306/java-jdbc", "root", "root")
				.load();
		
		flyway.migrate();
		
		
		
		var colaborador = new Colaborador();
		colaborador.setNome("Ricarlos");
		colaborador.setMatricula("0001");
		System.out.println("Colaborador ANTES do insert: "+colaborador);
		colaboradorDAO.insert(colaborador);
		System.out.println("Colaborador DEPOIS do insert: "+colaborador);
		
		System.out.println("****************************************************");
		
		var contato = new Contato();
		contato.setTipo("email");
		contato.setDescricao("ricarlosjr@hotmail.com");
		contato.setColaborador(colaborador);
		System.out.println("Contato ANTES do insert: "+contato);
		contatoDAO.insert(contato);
		System.out.println("Contato DEPOIS do insert: "+contato);
		
		System.out.println("****************************************************");
		System.out.println("Consultando Colaborador por Codigo");
		
		System.out.println(colaboradorDAO.findById(colaborador.getId()));
		
		
		/*System.out.println("Consultando todos os colaboradores");
		System.out.println(colaboradorDAO.findAll());
		
		
		System.out.println("Consultando colaborador com codigo 1");
		System.out.println(colaboradorDAO.findById(1L));
		
		System.out.println("Atualizando colaborador com codigo 2");
		var colaborador = new Colaborador();
		colaborador.setId(2L);
		colaborador.setNome("Fernanda");
		colaborador.setMatricula("0002");
		colaboradorDAO.update(colaborador);
		
		
		System.out.println("Atualizando colaborador com codigo 3");
		colaborador.setId(3L);
		colaborador.setNome("Cecilia");
		colaborador.setMatricula("0003");
		colaboradorDAO.update(colaborador);
		
		
		System.out.println(colaboradorDAO.findAll());
		
		
		System.out.println("Deletando colaborador com codigo 3");
		colaboradorDAO.delete(3L);
		
		System.out.println(colaboradorDAO.findAll());*/
	}
}
