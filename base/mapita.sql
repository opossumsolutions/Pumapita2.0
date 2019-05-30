DROP TABLE IF EXISTS comentario;

DROP TABLE IF EXISTS marcador;

DROP TABLE IF EXISTS tema;

DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
  idusuario serial NOT NULL PRIMARY KEY,
  nombre text NOT NULL,
  correo  text NOT NULL,
  contrasenia text NOT NULL,
  fechanacimiento date,
  rol text NOT NULL
);

CREATE TABLE tema (
  idtema serial NOT NULL,
  nombre text NOT NULL,
  color text NOT NULL,
  icon text NOT NULL,
  PRIMARY KEY (idtema),
  usuarioid integer REFERENCES usuario(idusuario) ON DELETE CASCADE
);

CREATE TABLE marcador (
  idmarcador serial NOT NULL,
  descripcion text NOT NULL,
  longitud double precision NOT NULL,
  latitud double precision NOT NULL,
  PRIMARY KEY (idmarcador),
  temaid integer REFERENCES tema(idtema) ON DELETE CASCADE
);

CREATE TABLE comentario (
  idcomentario serial NOT NULL,
  contenido text NOT NULL,
  PRIMARY KEY (idcomentario),
  usuarioid integer REFERENCES usuario(idusuario) ON DELETE CASCADE,
  marcadorid integer REFERENCES marcador(idmarcador) ON DELETE CASCADE
);

INSERT INTO usuario(nombre,correo,contrasenia,fechanacimiento,rol)
       VALUES ('administrador','admin@gmail.com','admin','1994-12-16','ADMINISTRADOR'),('adolfo','adolfo@gmail.com','1234','2018-08-04','INFORMADOR'),('martin','martin@gmail.com','1234','2018-08-04','INFORMADOR'),('isaias','isaias@gmail.com','1234','2018-08-04','INFORMADOR'),('juan','juan@gmail.com','1234','1994-12-16','COMENTARISTA')
