package br.com.jdbc.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.mysql.cj.jdbc.StatementImpl;

import br.com.jdbc.persistence.entity.Beneficio;
import br.com.jdbc.persistence.entity.Colaborador;
import br.com.jdbc.persistence.entity.Contato;
import br.com.jdbc.persistence.entity.ModuloSistema;

public class ColaboradorDAO {

	private final ColaboradorModuloSistemaDAO colaboradorModuloSistemaDAO = new ColaboradorModuloSistemaDAO();
	
	public void insert(final Colaborador colaborador) {
		
		var sql = "INSERT INTO colaborador (nome, matricula) VALUES (?, ?)";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", "'" + colaborador.getNome() + "'")
          	  			  .replaceFirst("\\?", "'" + colaborador.getMatricula()+ "'");
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		) {

				prepareStatement.setString(1, colaborador.getNome());
				prepareStatement.setString(2, colaborador.getMatricula());
				
				prepareStatement.executeUpdate();
				
				//PEGANDO O ID INSERIDO NO BANCO
				if(prepareStatement instanceof StatementImpl impl) {
					colaborador.setId(impl.getLastInsertID());
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertWithModuloSistema(final Colaborador colaborador) {
		
		var sql = "INSERT INTO colaborador (nome, matricula) VALUES (?, ?)";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", "'" + colaborador.getNome() + "'")
          	  			  .replaceFirst("\\?", "'" + colaborador.getMatricula()+ "'");
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		) {

				prepareStatement.setString(1, colaborador.getNome());
				prepareStatement.setString(2, colaborador.getMatricula());
				
				prepareStatement.executeUpdate();
				
				//PEGANDO O ID INSERIDO NO BANCO
				if(prepareStatement instanceof StatementImpl impl) {
					colaborador.setId(impl.getLastInsertID());
				}
				
				colaborador.getModulos().stream().map(ModuloSistema::getId)
												.forEach(moduloId -> colaboradorModuloSistemaDAO.insert(colaborador.getId(), moduloId));
				
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void insertWithProcedure(final Colaborador colaborador) {
		
		var sql = "CALL prc_insert_colaborador (?, ?, ?)";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", "'" + colaborador.getId()+ "'")
						  .replaceFirst("\\?", "'" + colaborador.getNome() + "'")
          	  			  .replaceFirst("\\?", "'" + colaborador.getMatricula()+ "'");
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareCall(sql);
		) {
				
				prepareStatement.registerOutParameter(1, TimeZone.LONG);
				prepareStatement.setString(2, colaborador.getNome());
				prepareStatement.setString(3, colaborador.getMatricula());
				
				prepareStatement.execute();
				colaborador.setId(prepareStatement.getLong(1));
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void update(final Colaborador colaborador) {

		var sql = "UPDATE colaborador SET nome = ?, matricula = ? WHERE id = ?";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", "'" + colaborador.getNome() + "'")
          	  			  .replaceFirst("\\?", "'" + colaborador.getMatricula()+ "'")
						  .replaceFirst("\\?", "'" + colaborador.getId()+ "'");
		System.out.println("SQL: " + sqlDebug);
	
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		) {

				prepareStatement.setString(1, colaborador.getNome());
				prepareStatement.setString(2, colaborador.getMatricula());
				prepareStatement.setLong(3, colaborador.getId());
				
				prepareStatement.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(final Long id) {
		
		var sql = "DELETE FROM colaborador WHERE id = ?";

		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", String.valueOf(id));
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		) {
				
				prepareStatement.setLong(1, id);
				
				prepareStatement.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Colaborador findById(final Long id) {
		
		Colaborador colaborador = new Colaborador();
		var sql = " SELECT c.id, c.nome, c.matricula, ct.id contato_id, ct.tipo, ct.descricao "
				+ " FROM colaborador c"
				+ " INNER JOIN contato ct ON (c.id = ct.colaborador_id) "
				+ " WHERE c.id = ?";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", String.valueOf(id));
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		 ) {
			
			prepareStatement.setLong(1, id);
			
			var resultSet = prepareStatement.executeQuery();
			
			if (resultSet.next()) {
				
				colaborador.setId(resultSet.getLong("id"));
				colaborador.setNome(resultSet.getString("nome"));
				colaborador.setMatricula(resultSet.getString("matricula"));
				colaborador.setContato(new Contato());
				colaborador.getContato().setId(resultSet.getLong("contato_id"));
				colaborador.getContato().setTipo(resultSet.getString("tipo"));
				colaborador.getContato().setDescricao(resultSet.getString("descricao"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colaborador;
	}

	//METODO PARA ESTUDO 
	//COLABORADOR 1 PARA 1 COM CONTATO
	//COLABORADOR 1 PARA N COM BENEFICIO (POR ISSO O DO WHILE)
	public Colaborador findByIdWithBeneficios(final Long id) {
		
		Colaborador colaborador = new Colaborador();
		var sql = " SELECT c.id, c.nome, c.matricula, ct.id contato_id, ct.tipo, ct.descricao, bf.id beneficio_id, bf.descricao descricao_beneficio, bf.desconto_colaborador "
				+ " FROM colaborador c"
				+ " INNER JOIN contato ct ON (c.id = ct.colaborador_id) "
				+ " INNER JOIN beneficio bf ON (c.id = bf.colaborador_id) "
				+ " WHERE c.id = ?";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", String.valueOf(id));
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		 ) {
			
			prepareStatement.setLong(1, id);
			
			var resultSet = prepareStatement.executeQuery();
			
			if (resultSet.next()) {
				
				colaborador.setId(resultSet.getLong("id"));
				colaborador.setNome(resultSet.getString("nome"));
				colaborador.setMatricula(resultSet.getString("matricula"));
				colaborador.setContato(new Contato());
				colaborador.getContato().setId(resultSet.getLong("contato_id"));
				colaborador.getContato().setTipo(resultSet.getString("tipo"));
				colaborador.getContato().setDescricao(resultSet.getString("descricao"));
				colaborador.setBeneficios(new ArrayList<>());
				
				do {
					
					var beneficio = new Beneficio();
					beneficio.setId(resultSet.getLong("beneficio_id"));
					beneficio.setDescricao(resultSet.getString("descricao_beneficio"));
					beneficio.setDescontoColaborador(resultSet.getBigDecimal("desconto_colaborador"));
					colaborador.getBeneficios().add(beneficio);
					
				} while (resultSet.next());
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colaborador;
	}
	
	
	public List<Colaborador> findAll() {
		
		List<Colaborador> colaboradores = new ArrayList<>();
		var sql = "SELECT * FROM colaborador";
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		 ) {
			
			var resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				Colaborador colaborador = new Colaborador();
				colaborador.setId(resultSet.getLong("id"));
				colaborador.setNome(resultSet.getString("nome"));
				colaborador.setMatricula(resultSet.getString("matricula"));
				colaboradores.add(colaborador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return colaboradores;
	}
	
	public List<Colaborador> findAllWithView() {
		
		List<Colaborador> colaboradores = new ArrayList<>();
		var sql = "SELECT * FROM vw_colaborador";
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		 ) {
			
			var resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				Colaborador colaborador = new Colaborador();
				colaborador.setNome(resultSet.getString("nome"));
				colaborador.setMatricula(resultSet.getString("matricula"));
				colaboradores.add(colaborador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return colaboradores;
	}

}
