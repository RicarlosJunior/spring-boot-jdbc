package br.com.jdbc.persistence;

import java.sql.SQLException;

import com.mysql.cj.jdbc.StatementImpl;

import br.com.jdbc.persistence.entity.Beneficio;
import br.com.jdbc.persistence.entity.Contato;
import br.com.jdbc.persistence.entity.ModuloSistema;

public class ModuloSistemaDAO {

public void insert(final ModuloSistema moduloSistema) {
		
		var sql = "INSERT INTO modulo_sistema (nome) VALUES (?)";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", "'" + moduloSistema.getNome() + "'");
          	  			
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		) {

				prepareStatement.setString(1, moduloSistema.getNome());
				
				prepareStatement.executeUpdate();
				
				//PEGANDO O ID INSERIDO NO BANCO
				if(prepareStatement instanceof StatementImpl impl) {
					moduloSistema.setId(impl.getLastInsertID());
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
