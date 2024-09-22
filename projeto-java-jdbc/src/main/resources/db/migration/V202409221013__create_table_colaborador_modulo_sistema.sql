CREATE TABLE colaborador_modulo_sistema(
	colaborador_id BIGINT not null,
	modulo_sistema_id BIGINT not null,
	CONSTRAINT fk_colaborador FOREIGN KEY (colaborador_id) REFERENCES colaborador(id),
	CONSTRAINT fk_modulo_sistema FOREIGN KEY (modulo_sistema_id) REFERENCES modulo_sistema(id),
	PRIMARY KEY(colaborador_id, modulo_sistema_id)
)engine=InnoDB default charset=utf8;