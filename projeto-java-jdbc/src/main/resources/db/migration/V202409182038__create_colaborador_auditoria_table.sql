CREATE TABLE colaborador_auditoria(
	id BIGINT not null auto_increment,
	nome VARCHAR(150),
	anterior_nome VARCHAR(150),
	matricula VARCHAR(20), 
	anterior_matricula VARCHAR(20),
	operacao CHAR(1),
	criado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(id)
)engine=InnoDB default charset=utf8;