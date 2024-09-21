CREATE TABLE colaborador(
	id BIGINT not null auto_increment,
	nome VARCHAR(150) not null,
	matricula VARCHAR(20) not null,
	PRIMARY KEY(id)
)engine=InnoDB default charset=utf8;