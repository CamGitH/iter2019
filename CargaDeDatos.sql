insert into ordendeservicio(numeroorden,tipo) VALUES (2, 'TERAPIA' );
insert into ordendeservicio(numeroorden,tipo) VALUES (3, 'HOSPITALIZACIÓN' );
insert into ordendeservicio(numeroorden,tipo) VALUES (4, 'PROCEDIMIENTO' );
insert into ordendeservicio(numeroorden,tipo) VALUES (5, 'ESPECIALISTA' );
insert into ordendeservicio(numeroorden,tipo) VALUES (6, 'TERAPIA' );
insert into ordendeservicio(numeroorden,tipo) VALUES (7, 'HOSPITALIZACIÓN' );
insert into ordendeservicio(numeroorden,tipo) VALUES (8, 'PROCEDIMIENTO' );
insert into ordendeservicio(numeroorden,tipo) VALUES (9, 'ESPECIALISTA' );
insert into ordendeservicio(numeroorden,tipo) VALUES (10, 'TERAPIA' );
insert into ordendeservicio(numeroorden,tipo) VALUES (11, 'HOSPITALIZACIÓN' );
insert into ordendeservicio(numeroorden,tipo) VALUES (12, 'PROCEDIMIENTO' );
insert into ordendeservicio(numeroorden,tipo) VALUES (13, 'ESPECIALISTA' );
insert into ordendeservicio(numeroorden,tipo) VALUES (14, 'TERAPIA' );
insert into ordendeservicio(numeroorden,tipo) VALUES (15, 'HOSPITALIZACIÓN' );
insert into ordendeservicio(numeroorden,tipo) VALUES (16, 'PROCEDIMIENTO' );
insert into ordendeservicio(numeroorden,tipo) VALUES (17, 'ESPECIALISTA' );
insert into ordendeservicio(numeroorden,tipo) VALUES (18, 'TERAPIA' );
insert into ordendeservicio(numeroorden,tipo) VALUES (19, 'HOSPITALIZACIÓN' );
insert into ordendeservicio(numeroorden,tipo) VALUES (20, 'PROCEDIMIENTO' );
insert into ordendeservicio(numeroorden,tipo) VALUES (21, 'ESPECIALISTA' );



insert into medicamento(nombre, Empresa) VALUES ('paracetamol','Bayer');
insert into medicamento(nombre, Empresa) VALUES ('aspirina','Sanofi');
insert into medicamento(nombre, Empresa) VALUES ('omeprazol','J+J');
insert into medicamento(nombre, Empresa) VALUES ('Ramipril','Baxter');
insert into medicamento(nombre, Empresa) VALUES ('simvastatina','La Sante');
insert into medicamento(nombre, Empresa) VALUES ('Amlodipina','GSK');
insert into medicamento(nombre, Empresa) VALUES ('Atorvastatina','Bayer');
insert into medicamento(nombre, Empresa) VALUES ('Lansoprazol','Sanofi');
insert into medicamento(nombre, Empresa) VALUES ('colecalciferol','J+J');
insert into medicamento(nombre, Empresa) VALUES ('amoxicilina','Baxter');
insert into medicamento(nombre, Empresa) VALUES ('furosemida','La Sante');
insert into medicamento(nombre, Empresa) VALUES ('Co-codamol','GSK');


insert into receta(id, orden) VALUES ('123', 1);
insert into receta(id, orden) VALUES ('143', 1);
insert into receta(id, orden) VALUES ('125', 1);
insert into receta(id, orden) VALUES ('163', 5);
insert into receta(id, orden) VALUES ('223', 3);
insert into receta(id, orden) VALUES ('723', 6);
insert into receta(id, orden) VALUES ('923', 21);
insert into receta(id, orden) VALUES ('193', 14);
insert into receta(id, orden) VALUES ('120', 15);
insert into receta(id, orden) VALUES ('129', 17);
insert into receta(id, orden) VALUES ('623', 18);
insert into receta(id, orden) VALUES ('173', 13);
insert into receta(id, orden) VALUES ('343', 16);
insert into receta(id, orden) VALUES ('333', 11);
insert into receta(id, orden) VALUES ('283', 17);
insert into receta(id, orden) VALUES ('113', 18);
insert into receta(id, orden) VALUES ('191', 13);
insert into receta(id, orden) VALUES ('111', 7);
insert into receta(id, orden) VALUES ('222', 9);


insert into eps(nombre, gerente) VALUES ('SURA', 'Ricardo Milos');
insert into eps(nombre, gerente) VALUES ('Colsanitas', 'Michael Jackson');


insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('1','Milos@gmail.com','Ricardo','Milos','CEDULA','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('2','maira@gmail.com','maira','perez','TARJETA DE IDENTIDAD','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('3','maria@gmail.com','maria','perez','CEDULA','Colsanitas');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('4','Enrique@gmail.com','Enrique','perez','TARJETA DE IDENTIDAD','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('5','Jorge@gmail.com','Jorge','perez','CEDULA DE EXTRANJERIA','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('6','Karen@gmail.com','Karen','perez','CEDULA','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('7','Cecilia@gmail.com','Cecilia','perez','CEDULA','Colsanitas');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('8','Santiago@gmail.com','Santiago','perez','CEDULA','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('9','Daniel@gmail.com','Daniel','perez','CEDULA DE EXTRANJERIA','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('10','Francisco@gmail.com','Francisco','perez','CEDULA','Colsanitas');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('11','Antonio@gmail.com','Antonio','perez','CEDULA DE EXTRANJERIA','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('12','Diana@gmail.com','Diana','perez','CEDULA','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('13','Michael@gmail.com','Michael','Jackson','CEDULA','Colsanitas');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('14','Jose@gmail.com','Jose','perez','TARJETA DE IDENTIDAD','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('15','Jaime@gmail.com','Jaime','perez','CEDULA','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('16','Carlos@gmail.com','Carlos','perez','CEDULA','Colsanitas');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('17','Carla@gmail.com','Carla','perez','TARJETA DE IDENTIDAD','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('18','Arturo@gmail.com','Arturo','perez','CEDULA','Colsanitas');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('19','Marcela@gmail.com','Marcela','perez','CEDULA','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('20','Juliana@gmail.com','Juliana','perez','TARJETA DE IDENTIDAD','Colsanitas');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('21','Caro@gmail.com','Caro','perez','CEDULA','SURA');
insert into usuario(id, correo, nombre, apellido, tipoid, nombre_eps) values ('22','Felipe@gmail.com','Felipe','perez','CEDULA','Colsanitas');

insert into IPS(nombre, localizacion, recepcionista, nombreeps) VALUES ('Clínica Nueva','231412431','3','SURA');
insert into IPS(nombre, localizacion, recepcionista, nombreeps) VALUES ('Clínica Chía','231434331','22','Colsanitas');
insert into IPS(nombre, localizacion, recepcionista, nombreeps) VALUES ('Laboratorio Ángela','2978812431','13','Colsanitas');
insert into IPS(nombre, localizacion, recepcionista, nombreeps) VALUES ('Clínica El Country','2314532431','6','SURA');


insert into medico(identificacion, registroMedico, NombreIPS) VALUES ('1','213123','Clínica Nueva');
insert into medico(identificacion, registroMedico, NombreIPS) VALUES ('2','213143','Clínica Chía');
insert into medico(identificacion, registroMedico, NombreIPS) VALUES ('3','213163','Laboratorio Ángela');
insert into medico(identificacion, registroMedico, NombreIPS) VALUES ('4','213187','Clínica El Country');

