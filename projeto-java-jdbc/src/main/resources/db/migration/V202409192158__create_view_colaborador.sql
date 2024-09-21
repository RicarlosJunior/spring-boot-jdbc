CREATE VIEW vw_colaborador AS 
	SELECT nome, 
		   matricula
    FROM colaborador
    ORDER BY id;