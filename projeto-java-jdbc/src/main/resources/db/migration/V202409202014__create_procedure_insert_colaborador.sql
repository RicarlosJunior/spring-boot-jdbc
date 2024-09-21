DELIMITER $$
CREATE PROCEDURE prc_insert_colaborador(
	OUT p_id BIGINT,
	IN p_nome VARCHAR(150),	
	IN p_matricula VARCHAR(20)	
)
BEGIN
	INSERT INTO colaborador (nome, matricula) VALUES (p_nome, p_matricula);
	SET p_id = LAST_INSERT_ID(); 
END $$