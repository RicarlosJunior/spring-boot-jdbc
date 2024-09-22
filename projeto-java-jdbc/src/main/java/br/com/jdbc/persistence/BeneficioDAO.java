package br.com.jdbc.persistence;

import java.sql.SQLException;

import com.mysql.cj.jdbc.StatementImpl;

import br.com.jdbc.persistence.entity.Beneficio;
import br.com.jdbc.persistence.entity.Contato;

public class BeneficioDAO {

public void insert(final Beneficio beneficio) {
		
		var sql = "INSERT INTO beneficio (descricao, desconto_colaborador, colaborador_id) VALUES (?, ?, ?)";
		
		//CODIGO PARA DESENVOLVIMENTO
		var sqlDebug = sql.replaceFirst("\\?", "'" + beneficio.getDescricao() + "'")
          	  			  .replaceFirst("\\?", "'" + beneficio.getDescontoColaborador()+ "'")
						  .replaceFirst("\\?", "'" + beneficio.getColaborador().getId()+ "'");
		System.out.println("SQL: " + sqlDebug);
		
		try (var connection = ConnectionUtil.getConnection(); 
			 var prepareStatement = connection.prepareStatement(sql);
		) {

				prepareStatement.setString(1, beneficio.getDescricao());
				prepareStatement.setBigDecimal(2, beneficio.getDescontoColaborador());
				prepareStatement.setLong(3, beneficio.getColaborador().getId());
				
				prepareStatement.executeUpdate();
				
				//PEGANDO O ID INSERIDO NO BANCO
				if(prepareStatement instanceof StatementImpl impl) {
					beneficio.setId(impl.getLastInsertID());
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
