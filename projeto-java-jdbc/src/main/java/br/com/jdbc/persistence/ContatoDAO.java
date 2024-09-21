package br.com.jdbc.persistence;

import java.sql.SQLException;

import com.mysql.cj.jdbc.StatementImpl;

import br.com.jdbc.persistence.entity.Contato;

public class ContatoDAO {

public void insert(final Contato contato) {
		
		var sql = "INSERT INTO contato (tipo, descricao, colaborador_id) VALUES (?, ?, ?)";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", "'" + contato.getTipo() + "'")
          	  			  .replaceFirst("\\?", "'" + contato.getDescricao()+ "'")
						  .replaceFirst("\\?", "'" + contato.getColaborador().getId()+ "'");
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		) {

				prepareStatement.setString(1, contato.getTipo());
				prepareStatement.setString(2, contato.getDescricao());
				prepareStatement.setLong(3, contato.getColaborador().getId());
				
				prepareStatement.executeUpdate();
				
				//PEGANDO O ID INSERIDO NO BANCO
				if(prepareStatement instanceof StatementImpl impl) {
					contato.setId(impl.getLastInsertID());
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
