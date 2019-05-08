DROP TABLE IF EXISTS tema;

DROP TABLE IF EXISTS marcador;

DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
  idusuario serial NOT NULL PRIMARY KEY,
  nombre text NOT NULL,
  correo  text NOT NULL,
  contrasenia text NOT NULL,
  fechanacimiento date,
  rol text NOT NULL
);

CREATE TABLE marcador (
  idmarcador serial NOT NULL,
  descripcion text NOT NULL,
  longitud double precision NOT NULL,
  latitud double precision NOT NULL,
  icon text NOT NULL,
  PRIMARY KEY (idmarcador),
  usuarioid integer REFERENCES usuario(idusuario) ON DELETE CASCADE
);

CREATE TABLE tema (
  idtema serial NOT NULL,
  nombre text NOT NULL,
  color text NOT NULL,
  PRIMARY KEY (idtema),
  usuarioid integer REFERENCES usuario(idusuario) ON DELETE CASCADE
);
