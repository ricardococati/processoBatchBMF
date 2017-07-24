DELETE
FROM
  corporativo.EmpresaOperadora
WHERE
  idEmpresa IN(294 , 295 , 296 , 308 , 309 , 310 , 346 , 347 , 353 , 354 , 357
  , 358 , 359 , 361 , 362 , 364 , 365 , 366 , 368 , 372 , 373 , 374 , 376 , 377
  , 378 , 379 , 380 , 382 , 388 , 390 , 396 , 397 , 407 , 409 , 412 , 413 , 414
  , 429 , 444);
  
DELETE
FROM
  tiquei.Push
WHERE
  idUsuario IN
  (
    SELECT
      id
    FROM
      corporativo.Usuario
    WHERE
      idEmpresa IN
      (
        SELECT
          id
        FROM
          corporativo.Empresa
        WHERE
          id IN(294 , 295 , 296 , 308 , 309 , 310 , 346 , 347 , 353 , 354 , 357
          , 358 , 359 , 361 , 362 , 364 , 365 , 366 , 368 , 372 , 373 , 374 ,
          376 , 377 , 378 , 379 , 380 , 382 , 388 , 390 , 396 , 397 , 407 , 409
          , 412 , 413 , 414 , 429 , 444)
      )
  );
  
DELETE
FROM
  corporativo.Usuario
WHERE
  idEmpresa IN
  (
    SELECT
      id
    FROM
      corporativo.Empresa
    WHERE
      id IN(294 , 295 , 296 , 308 , 309 , 310 , 346 , 347 , 353 , 354 , 357 ,
      358 , 359 , 361 , 362 , 364 , 365 , 366 , 368 , 372 , 373 , 374 , 376 ,
      377 , 378 , 379 , 380 , 382 , 388 , 390 , 396 , 397 , 407 , 409 , 412 ,
      413 , 414 , 429 , 444)
  );
  
DELETE
FROM
  corporativo.Empresa
WHERE
  id IN(294 , 295 , 296 , 308 , 309 , 310 , 346 , 347 , 353 , 354 , 357 , 358 ,
  359 , 361 , 362 , 364 , 365 , 366 , 368 , 372 , 373 , 374 , 376 , 377 , 378 ,
  379 , 380 , 382 , 388 , 390 , 396 , 397 , 407 , 409 , 412 , 413 , 414 , 429 ,
  444);