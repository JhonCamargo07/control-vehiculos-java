drop database if exists manejo_vehiculos;
Create database manejo_vehiculos;
use manejo_vehiculos;
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     21/10/2013 10:45:19                          */
/*==============================================================*/


drop table if exists CATEGORIA;

drop table if exists DATOSPERSONALES;

drop table if exists ROL;

drop table if exists USUARIO;

drop table if exists USUARIO_ROL;

drop table if exists VEHICULO;

/*==============================================================*/
/* Table: CATEGORIA                                             */
/*==============================================================*/
create table CATEGORIA
(
   CATID                int not null auto_increment,
   CATIPO               varchar(50) not null,
   primary key (CATID)
);

/*==============================================================*/
/* Table: DATOSPERSONALES                                       */
/*==============================================================*/
create table DATOSPERSONALES
(
   DATID                int not null auto_increment,
   USUID                int,
   DATNOMBRE            varchar(50) not null default 'Descanocido',
   DATAPELLIDO          varchar(50) not null default 'Descanocido',
   DATIPOID             varchar(20) not null default 'C.C',
   DATNUMEROID          varchar(20) not null default '1000000000',
   DATELEFONO           varchar(20) not null default '3100000000',
   DATCORREO            varchar(50) not null default 'desconocido@gmail.com',
   primary key (DATID)
);

/*==============================================================*/
/* Table: ROL                                                   */
/*==============================================================*/
create table ROL
(
   ROLID                int not null auto_increment,
   ROLTIPO              varchar(50) not null,
   primary key (ROLID)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   USUID                int not null auto_increment primary key,
   USULOGIN             varchar(20) unique not null,
   USUPASSWORD          varchar(20) not null
);

/*==============================================================*/
/* Table: USUARIO_ROL                                           */
/*==============================================================*/
create table USUARIO_ROL
(
   ROLID                int not null,
   USUID                int not null,
   primary key (ROLID, USUID)
);

/*==============================================================*/
/* Table: VEHICULO                                              */
/*==============================================================*/
create table VEHICULO
(
   VEHPLACA             varchar(10) not null,
   DATID                int not null,
   CATID                int not null,
   VEHMODELO            int not null,
   VEHMARCA             varchar(50) not null,
   VEHESTADO            varchar(30) not null,
   VEHPRECIO            double not null,
   primary key (VEHPLACA)
);

alter table DATOSPERSONALES add constraint FK_RELATIONSHIP_1 foreign key (USUID)
      references USUARIO (USUID) on delete restrict on update restrict;

alter table USUARIO_ROL add constraint FK_RELATIONSHIP_2 foreign key (USUID)
      references USUARIO (USUID) on delete restrict on update restrict;

alter table USUARIO_ROL add constraint FK_RELATIONSHIP_3 foreign key (ROLID)
      references ROL (ROLID) on delete restrict on update restrict;

alter table VEHICULO add constraint FK_REFERENCE_5 foreign key (CATID)
      references CATEGORIA (CATID) on delete restrict on update restrict;

alter table VEHICULO add constraint FK_REFERENCE_6 foreign key (DATID)
      references DATOSPERSONALES (DATID) on delete restrict on update restrict;

-- triggers

DELIMITER $$
CREATE TRIGGER insertarDatosDeUsuario
AFTER INSERT on usuario
FOR EACH ROW
BEGIN
	INSERT INTO datospersonales VALUES (null, NEW.USUID, 'Desconocido', 'Desconocido', 'C.C', '1000000000', '3100000000', 'desconocido@gmail.com');
END $$
DELIMITER ;


-- Inserciones

-- INSERT INTO usuario VALUES (null, 'JhonCamargo21', '1234567890');
-- INSERT INTO usuario VALUES (null,'JhonCamargo21', '1234567890');

INSERT INTO categoria (CATIPO) VALUES ('Campero'), ('Automóvil'), ('Camioneta'), ('Tractor');

INSERT INTO  rol (ROLTIPO) VALUES ('comprador'), ('vendedor'), ('camprador y vendedor');

-- INSERT INTO datospersonales VALUES(null, 1, 'Jhon', 'Camargo', 'C.C', '1014737507', '3144781527', 'jhonalexcamargo07@gmail.com'),
-- (null, 2, 'Alexander', 'Cadena', 'C.C', '1014737508', '3142577567', 'jhoncamargo07a@gmail.com'),
-- (null, 3, 'Jacc', 'Hernandez', 'C.C', '1014737509', '3143329921', 'jacchernandez@gmail.com');

SELECT * FROM usuario ORDER BY USUID DESC LIMIT 1;

select * from usuario;
select * from usuario_rol;
select * from rol;
select * from datospersonales;

-- SELECT * FROM usuario WHERE BINARY USULOGIN = 'Canserbero' AND BINARY USUPASSWORD = '1234567890';


SELECT * FROM USUARIO INNER JOIN datospersonales ON datospersonales.USUID = usuario.USUID INNER JOIN USUARIO_ROL ON USUARIO_ROL.USUID = USUARIO.USUID INNER JOIN ROL ON ROL.ROLID = USUARIO_ROL.ROLID  WHERE BINARY USULOGIN = 'JhonCamargo21' AND BINARY USUPASSWORD = '1234567890';

SELECT USUARIO.USUID, ROL.ROLID, USULOGIN, USUPASSWORD, DATNOMBRE, DATAPELLIDO, DATELEFONO, DATCORREO FROM USUARIO INNER JOIN datospersonales as datos ON datos.USUID = usuario.USUID INNER JOIN USUARIO_ROL as usuRol ON usuRol.USUID = usuario.USUID INNER JOIN ROL ON ROL.ROLID = usuRol.ROLID  WHERE BINARY USULOGIN = ? AND BINARY USUPASSWORD = ?;

SELECT datos.DATID FROM USUARIO INNER JOIN datospersonales as datos on usuario.USUID = datos.USUID WHERE usuario.USUID = 5;