package br.com.jdbc.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.StatementImpl;

import br.com.jdbc.persistence.entity.Beneficio;
import br.com.jdbc.persistence.entity.Colaborador;
import br.com.jdbc.persistence.entity.Contato;
import br.com.jdbc.persistence.entity.ModuloSistema;

public class ColaboradorModuloSistemaDAO {

 public void insert(final Long colaboradorId, final Long moduloSistemaId) {
		
		var sql = "INSERT INTO colaborador_modulo_sistema (colaborador_id, modulo_sistema_id) VALUES (?, ?)";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", "'" + colaboradorId + "'")
						  .replaceFirst("\\?", "'" + moduloSistemaId + "'");
          	  			
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		) {

				prepareStatement.setLong(1, colaboradorId);
				prepareStatement.setLong(2, moduloSistemaId);
				
				prepareStatement.executeUpdate();
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
 public List<ModuloSistema> findModuloSistemaByColaborador(final Long colaboradorId) {
		
	 	List<ModuloSistema> modulos = new ArrayList<>();
		var sql = " SELECT DISTINCT ms.id, ms.nome "
				+ " FROM colaborador_modulo_sistema cms"
				+ " INNER JOIN modulo_sistema ms ON (cms.modulo_sistema_id = ms.id) "
				+ " WHERE cms.colaborador_id = ?";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", "'"+colaboradorId+"'");
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		 ) {
			
			prepareStatement.setLong(1, colaboradorId);
			
			var resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				
				var moduloSistema = new ModuloSistema();
				moduloSistema.setId(resultSet.getLong("id"));
				moduloSistema.setNome(resultSet.getString("nome"));
				modulos.add(moduloSistema);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modulos;
	}
 
}
