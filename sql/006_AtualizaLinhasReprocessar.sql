UPDATE 
      processo_batch_cielo.CIELO_VENDA_PAGTO 
SET 
      STATUS_PROCESSAMENTO = 'N' 
WHERE 
      ID_LINHA IN(34587, 34783, 34784, 34569, 34568, 34624);
