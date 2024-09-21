DELIMITER $
CREATE TRIGGER trg_update_colaborador_auditoria BEFORE UPDATE ON colaborador
FOR EACH ROW
BEGIN
	INSERT INTO colaborador_auditoria (
		colaborador_id,
		nome,
		anterior_nome,
		matricula,
		anterior_matricula,
		operacao
	) VALUES (
		NEW.id,
		NEW.nome,
		OLD.nome,
		NEW.matricula,
		OLD.matricula,
		'U'
	);
END $