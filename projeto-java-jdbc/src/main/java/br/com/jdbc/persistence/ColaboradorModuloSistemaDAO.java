package br.com.jdbc.persistence;

import java.sql.SQLException;

import com.mysql.cj.jdbc.StatementImpl;

import br.com.jdbc.persistence.entity.Beneficio;
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
}
