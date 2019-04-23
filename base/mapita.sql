DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
  idusuario serial NOT NULL PRIMARY KEY,
  nombre text NOT NULL,
  correo  text NOT NULL,
  contrasenia text NOT NULL,
  fechanacimiento date,
  rol text NOT NULL
);

