DELIMITER $
CREATE TRIGGER trg_insert_colaborador_auditoria AFTER INSERT ON colaborador
FOR EACH ROW
BEGIN
	INSERT INTO colaborador_auditoria (
		colaborador_id,
		nome,
		matricula,
		operacao
	) VALUES (
		NEW.id,
		NEW.nome,
		NEW.matricula,
		'I'
	);
END $