DELIMITER $
CREATE TRIGGER trg_delete_colaborador_auditoria BEFORE DELETE ON colaborador
FOR EACH ROW
BEGIN
	INSERT INTO colaborador_auditoria (
		colaborador_id,
		nome,
		matricula,
		operacao
	) VALUES (
		OLD.id,
		OLD.nome,
		OLD.matricula,
		'D'
	);
END $