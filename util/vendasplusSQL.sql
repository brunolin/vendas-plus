CREATE TABLE TB_EMPRESA (
  ID_EMPRESA NUMBER NOT NULL,
  NOME_EMPRESA VARCHAR2(50) NOT NULL,
  CNPJ VARCHAR2(38) NOT NULL,
  CIDADE VARCHAR2(50) NOT NULL,
  ESTADO VARCHAR2(50) NOT NULL,
  TELEFONE NUMBER NOT NULL,
  EMAIL VARCHAR2(100) NOT NULL,
  SENHA VARCHAR2(250) NULL,
  PRIMARY KEY(ID_EMPRESA)
);
 
CREATE TABLE TB_VENDAS (
  ID_VENDA VARCHAR2(44) NOT NULL,
  ID_VENDEDOR NUMBER NOT NULL,
  ID_PRODUTO NUMBER NOT NULL,
  ID_EMPRESA NUMBER NOT NULL,
  NOME_PRODUTO VARCHAR2(50) NULL,
  DATA_VENDA VARCHAR2(50) NULL,
  APROVADA VARCHAR2(1) NULL,
  PRIMARY KEY(ID_VENDA)
);
 
 
CREATE TABLE TB_PRODUTO (
  ID_PRODUTO NUMBER NOT NULL,
  NOME_PRODUTO VARCHAR2(150) NOT NULL,
  ID_EMPRESA NUMBER NOT NULL,
  INICIO_CAMPANHA VARCHAR2(50) NOT NULL,
  VIGENCIA_CAMPANHA VARCHAR2(50) NOT NULL,
  PONTOS_RECOMPENSA NUMBER NOT NULL,
  IMG VARCHAR2(50) NULL,
  PRIMARY KEY(ID_PRODUTO)
);
 
CREATE TABLE TB_VENDEDOR (
  ID_VENDEDOR NUMBER NOT NULL,
  NOME_VENDEDOR VARCHAR2(100) NOT NULL,
  CPF VARCHAR2(20) NOT NULL,
  PONTOS NUMBER NOT NULL,
  TELEFONE NUMBER NULL,
  CIDADE VARCHAR2(50) NOT NULL,
  ESTADO VARCHAR2(50) NOT NULL,
  EMAIL VARCHAR2(100) NOT NULL,
  SENHA VARCHAR2(250) NOT NULL,
  PRIMARY KEY(ID_VENDEDOR)
);
 
 
CREATE TABLE TB_BONUS (
  ID_BONUS NUMBER,
  DESCRICAO VARCHAR2(50) NOT NULL,
  PONTOS_NECESSARIOS VARCHAR2(50) NOT NULL,
  NOME_BONUS VARCHAR2(50) NOT NULL,
  PRIMARY KEY(ID_BONUS)
);
 
 -- SEQUENCES

 CREATE SEQUENCE id_seq
 START WITH     10
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

 CREATE SEQUENCE produto_seq
 START WITH     10
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

 CREATE SEQUENCE bonus_seq 
 START WITH     10
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

 -- STORED PROCEDURE
 create or replace PROCEDURE proc_vendedor_insert
(
   P_ID_VENDEDOR IN NUMBER,
   P_NOME_VENDEDOR IN VARCHAR2,
   P_CPF IN VARCHAR2,
   P_PONTOS IN NUMBER,
   P_TELEFONE IN NUMBER,
   P_CIDADE IN VARCHAR2,
   P_ESTADO IN VARCHAR2,
   P_EMAIL IN VARCHAR2,
   P_SENHA IN VARCHAR2
)
AS
BEGIN
  INSERT INTO TB_VENDEDOR(ID_VENDEDOR,
        NOME_VENDEDOR,
        CPF,
        PONTOS,
        TELEFONE,
        CIDADE,
        ESTADO,
        EMAIL,
        SENHA
        )
        VALUES
        (id_seq.nextval, P_NOME_VENDEDOR, P_CPF, P_PONTOS, P_TELEFONE, P_CIDADE, P_ESTADO, P_EMAIL, P_SENHA);
END;


 -- PERMISSOES 
 
CREATE USER vp_user IDENTIFIED BY vp_user;
GRANT CREATE SESSION TO VP_USER;
GRANT SELECT, DELETE, INSERT, UPDATE ON TB_VENDAS TO vp_user;
GRANT SELECT, DELETE, INSERT, UPDATE ON TB_VENDEDOR TO vp_user;
GRANT SELECT, DELETE, INSERT, UPDATE ON TB_EMPRESA TO vp_user;
GRANT SELECT, DELETE, INSERT, UPDATE ON TB_PRODUTO TO vp_user;
GRANT SELECT, DELETE, INSERT, UPDATE ON TB_BONUS TO vp_user;

-- POPULAR BANCO

INSERT INTO TB_VENDEDOR(ID_VENDEDOR, NOME_VENDEDOR, CPF,PONTOS, TELEFONE, CIDADE, ESTADO, EMAIL, SENHA) VALUES(1, 'Bruno', '11111111111', 0, 41999483552, 'Curitiba', 'Paraná', 'bruno@bruno.com', '123456');
INSERT INTO TB_VENDEDOR(ID_VENDEDOR, NOME_VENDEDOR, CPF,PONTOS, TELEFONE, CIDADE, ESTADO, EMAIL, SENHA) VALUES(2, 'Wally', '22222222222', 0, 41997854785, 'São Paulo', 'São Paulo', 'wally@bruno.com', '123456');

INSERT INTO TB_EMPRESA(ID_EMPRESA, NOME_EMPRESA, CNPJ, CIDADE, ESTADO, TELEFONE, EMAIL, SENHA) VALUES(3, 'Eletrolux', '33333333333333', 'Curitiba', 'Paraná', 41998563214, 'eletro@lux.com', '123456');
INSERT INTO TB_EMPRESA(ID_EMPRESA, NOME_EMPRESA, CNPJ, CIDADE, ESTADO, TELEFONE, EMAIL, SENHA) VALUES(4, 'Sony', '44444444444444', 'Curitiba', 'Paraná', 41985632547, 'sony@sony.com', '123456');

INSERT INTO TB_PRODUTO(NOME_PRODUTO, ID_PRODUTO, ID_EMPRESA, PONTOS_RECOMPENSA, IMG, INICIO_CAMPANHA, VIGENCIA_CAMPANHA)VALUES('Liquidificador', 1, 3, 750, 'no', '2016-11-10T02:00:00.000Z', '2016-11-15T02:00:00.000Z');
INSERT INTO TB_PRODUTO(NOME_PRODUTO, ID_PRODUTO, ID_EMPRESA, PONTOS_RECOMPENSA, IMG, INICIO_CAMPANHA, VIGENCIA_CAMPANHA)VALUES('Tv 29', 2, 4, 900, 'no', '2016-11-10T02:00:00.000Z', '2016-11-15T02:00:00.000Z');

INSERT INTO TB_VENDAS(ID_VENDA, ID_PRODUTO, ID_VENDEDOR, ID_EMPRESA, NOME_PRODUTO, DATA_VENDA, APROVADA) VALUES('1111111111', 1, 1, 3, 'Liquidificador', '2016-11-14T02:00:00.000Z', 'F');
INSERT INTO TB_VENDAS(ID_VENDA, ID_PRODUTO, ID_VENDEDOR, ID_EMPRESA, NOME_PRODUTO, DATA_VENDA, APROVADA) VALUES('2222222222', 1, 2, 4, 'Tv 29', '2016-11-14T02:00:00.000Z', 'F');

INSERT INTO TB_BONUS(ID_BONUS, DESCRICAO, PONTOS_NECESSARIOS, NOME_BONUS) VALUES(2, 'Divertida de brincar', 500, 'Amoeba');
INSERT INTO TB_BONUS(ID_BONUS, DESCRICAO, PONTOS_NECESSARIOS, NOME_BONUS) VALUES(1, 'Muita potência', 25985, 'Ford Ka');

COMMIT;