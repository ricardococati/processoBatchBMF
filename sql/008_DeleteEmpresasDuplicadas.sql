DELETE
FROM
  corporativo.EmpresaOperadora
WHERE
  idEmpresa IN(340, 464);

INSERT INTO corporativo.EmpresaOperadora(idEmpresa, numeroPV, idOperadora, dtCadastro, dtAtualizacao)
				  VALUES('464', '1052901457', '1', '2016-11-20 13:22:30', NULL);
DELETE
FROM
  corporativo.Empresa
WHERE
  id IN(340);

