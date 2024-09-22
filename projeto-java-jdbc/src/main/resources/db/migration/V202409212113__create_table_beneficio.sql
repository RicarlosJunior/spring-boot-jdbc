CREATE TABLE beneficio(
	id BIGINT not null auto_increment,
	descricao VARCHAR(50) not null,
	desconto_colaborador DECIMAL(10,2) not null,
	colaborador_id BIGINT not null,
	CONSTRAINT fk_beneficio_colaborador FOREIGN KEY (colaborador_id) REFERENCES colaborador(id),
	PRIMARY KEY(id)
)engine=InnoDB default charset=utf8;