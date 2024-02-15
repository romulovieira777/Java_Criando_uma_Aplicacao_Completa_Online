INSERT INTO tb_movimentacao (placa, data_entrada) VALUES ('XXX-1111', '2016-05-06');

UPDATE tb_status_vaga SET ocupadas = 0 WHERE id = 1;

SELECT ocupadas FROM tb_status_vaga WHERE id = 1;

SELECT * FROM tb_movimentacao;

SELECT * FROM tb_status_vaga;