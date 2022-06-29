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
   DATCORREO            varchar(100) unique not null,
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
   USULOGIN             varchar(100) unique not null,
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

/*==============================================================*/
/* Relaciones de cada tabla                                       */
/*==============================================================*/
alter table DATOSPERSONALES 
add constraint FK_RELATIONSHIP_1 
foreign key (USUID) 
references USUARIO (USUID) on delete restrict on update restrict;

alter table USUARIO_ROL 
add constraint FK_RELATIONSHIP_2 
foreign key (USUID)
references USUARIO (USUID) on delete restrict on update restrict;

alter table USUARIO_ROL 
add constraint FK_RELATIONSHIP_3 
foreign key (ROLID)
references ROL (ROLID) on delete restrict on update restrict;

alter table VEHICULO 
add constraint FK_REFERENCE_5 
foreign key (CATID)
references CATEGORIA (CATID) on delete restrict on update restrict;

alter table VEHICULO 
add constraint FK_REFERENCE_6 
foreign key (DATID)
references DATOSPERSONALES (DATID) on delete restrict on update restrict;

/*==============================================================*/
/* Inserción del usuario principal (Administrador)                                           */
/*==============================================================*/
INSERT INTO  rol (ROLTIPO) VALUES ('comprador'), ('vendedor'), ('camprador y vendedor'), ('administrador');

INSERT INTO categoria (CATIPO) VALUES ('Campero'), ('Automóvil'), ('Camioneta'), ('Tractor');

INSERT INTO usuario VALUES (null, 'jhoncamargo07a@gmail.com', '1234567890');

INSERT INTO datospersonales VALUES(null, 1, 'Jhon Alexander', 'Camargo Cadena', 'C.C', '1010101010', '3141011010', 'jhoncamargo07a@gmail.com');

INSERT INTO usuario_rol VALUES(4,1);

