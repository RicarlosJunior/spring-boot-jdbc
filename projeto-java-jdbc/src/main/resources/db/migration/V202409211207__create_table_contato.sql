CREATE TABLE contato(
	id BIGINT not null auto_increment,
	tipo VARCHAR(50) not null,
	descricao VARCHAR(150) not null,
	colaborador_id BIGINT not null,
	CONSTRAINT fk_contato_colaborador FOREIGN KEY (colaborador_id) REFERENCES colaborador(id),
	PRIMARY KEY(id)
)engine=InnoDB default charset=utf8;