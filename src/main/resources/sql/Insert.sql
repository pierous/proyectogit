delete from administradores;
delete from departamentos;
delete from medicos;
delete from pacientes;
delete from parametros;
delete from plantillas;
delete from plantillas_parametros;
delete from imagenes;
delete from informes;
delete from pruebas;
delete from pruebas_valores;
delete from pruebas;
delete from valores;



insert into administradores (nombreLogin, dni, nombre, ap1, ap2, password, administradorID)
        values ('pierous', '47365744G', 'David', 'Ulloa', 'Sobrino', '6LEaRXOicZAlUcoXAzuy5w', 1);  -- 581985
insert into administradores (nombreLogin, dni, nombre, ap1, ap2, password, administradorID)
        values ('carsobrino', '32760331M', 'Carmen María', 'Sobrino', 'Gómez', '47jhaeHIc7rctMBHeRZSbA', 2);  -- carsobrino
insert into administradores (nombreLogin, dni, nombre, ap1, ap2, password, administradorID)
        values ('pacopresedo', '32185230L', 'Francisco', 'Sobrino', 'Mazás', 'ShLZYJyPhrlVLAOFZBieIQ', 3);  -- pacopresedo
insert into administradores (nombreLogin, dni, nombre, ap1, ap2, password, administradorID)
        values ('lolapresedo', '32346896H', 'Dolores', 'Gómez', 'Vázquez', 'TjAhFQkaEQawX-rgfvAm8w', 4);  -- lolapresedo
insert into administradores (nombreLogin, dni, nombre, ap1, ap2, password, administradorID)
        values ('juansantaya', '76343189W', 'Juan Antonio', 'Ulloa', 'Blanco', '6u-Bk-Xw8oAx-Mf0hsWOGw', 5);  -- juansantaya
insert into administradores (nombreLogin, dni, nombre, ap1, ap2, password, administradorID)
        values ('charosobrino', '51275480T', 'María del Rosario', 'Sobrino', 'Gómez', 'aJV4FAU-Rm2ZvYb3ljXZZA', 6);  -- charosobrino



insert into departamentos (nombre, supervisorID, departamentoID) values ('Cardiología', NULL, 1);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Traumatología', NULL, 2);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Neurología', NULL, 3);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Neurocirugía', NULL, 4);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Digestivo', NULL, 5);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Neumología', NULL, 6);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Urgencias', NULL, 7);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Análisis clínicos', NULL, 8);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Radiología', NULL, 9);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Cirugía plástica', NULL, 10);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Cirugía vascular', NULL, 11);
insert into departamentos (nombre, supervisorID, departamentoID) values ('Departamento Prueba', NULL, 12);



insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('56687691S', 'Ignacio', 'Camacho', 'Durán', '1984-12-4', 'nachocamacho', 'xoBjfAwklHyejxv_EA6Hpg', 8, 1);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('33218974G', 'Elena', 'Salgado', 'Santiago', '1984-12-4', 'elsalsa', '7yHOLzfik1ds_rar4VvwLQ', 8, 2);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('77987213F', 'Felisa', 'Cifuentes', 'Romero', '1984-12-4', 'felici', 'LWYzrqNkY5jDWslq-ZaRZw', 8, 3);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('81974612Z', 'Manuel Antonio', 'Iglesias', 'Izquierdo', '1984-12-4', 'mantonio', 'NWiWtEB9aXAZhLqmCLyb-w', 8, 4);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('44437691B', 'Ana María', 'Contreras', 'Sánchez', '1984-12-4', 'maconsa', 'QCh5uPkcmTyQ46-lethy4Q', 8, 5);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('61895183H', 'Cristina', 'Ramallo', 'Sánchez', '1984-12-4', 'crisrama', '3DwC5ZM07ja086D6e1_iKg', 8, 6);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('24638814R', 'Carlos Manuel', 'Rodriguez', 'Antelo', '1984-12-4', 'camaro', 't9gA8nAAek5T2qQVDGGaMA', 9, 7);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('48376443Q', 'Jose María', 'Mascaró', 'Castillo', '1984-12-4', 'josemama', 'pkb_JAfzXIFUO2lCqIghWA', 9, 8);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('92834658T', 'Fernando', 'Insua', 'Rivero', '1984-12-4', 'nandoriver', 'n9pygSxJlZggcMAuzUwUkw', 9, 9);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('84736294R', 'Juan Carlos', 'Cabrero', 'Cuentas', '1984-12-4', 'juancaca', '1fIRdMf9tH_Wezwxk3iN5Q', 9, 10);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('48390553Q', 'Concepcion', 'Villanueva', 'Cuevas', '1984-12-4', 'conchivilla', 'qIXCXtOULMf_amkotMsPmg', 9, 11);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('58473629W', 'Melani', 'Silva', 'Olivares', '1984-12-4', 'mesilva', 'RfbNM344rRoercQZO1IO3g', 9, 12);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('12345678P', 'Medico', 'Prueba', 'Prueba', '1984-12-5', '12345678P', 'VCJlUFNfAZqvwA29bRkdMw', 12, 13);



update departamentos set supervisorID=1 where departamentoID=8;
update departamentos set supervisorID=7 where departamentoID=9;
update departamentos set supervisorID=13 where departamentoID=12;



insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('74638599S', 'Antón', 'López', 'Mesejo', '1984-12-4', 1);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48375743J', 'Fernando', 'Saco', 'Goday', '1984-12-4', 2);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48375633G', 'Adrián', 'Míquez', 'Sánchez', '1984-12-4', 3);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('55768438K', 'Victor', 'Sánchez', 'Camino', '1984-12-4', 4);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('98473888L', 'Noel', 'Miguélez', 'Rodríguez', '1984-12-4', 5);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('49385664P', 'Daniel', 'Nogueira', 'Carro', '1984-12-4', 6);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48573658U', 'Francisco Javier', 'Sandá', 'Castro', '1984-12-4', 7);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('99947374O', 'Inés', 'Pardo', 'Gómez', '1984-12-4', 8);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('35477735T', 'Paula', 'López', 'Monteagudo', '1984-12-4', 9);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48736452P', 'María', 'Bermúdez', 'Rodríguez', '1984-12-4', 10);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('49837456J', 'Lucía', 'Triana', 'Seoane', '1984-12-4', 11);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('33394735V', 'Rafael', 'Pérez', 'Burgos', '1984-12-4', 12);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('47773645B', 'Darío', 'Martínez', 'Jove', '1984-12-4', 13);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('98743346C', 'Alan', 'González', 'Bermúdez', '1984-12-4', 14);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('88847363N', 'Yolanda', 'Sánchez', 'Regueira', '1984-12-4', 15);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('12312333M', 'Javier', 'Silveira', 'Caramés', '1984-12-4', 16);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('44473648M', 'Hernán', 'Martin-Barbadillo', 'Alonso', '1984-12-4', 17);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48376661Z', 'Patricia', 'Bujía', 'Otero', '1984-12-4', 18);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('49387677X', 'Ricardo', 'Pérez', 'Santalla', '1984-12-4', 19);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('28376993D', 'Hugo', 'Iglesias', 'Vigil', '1984-12-4', 20);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('93846833B', 'Sara', 'González', 'García', '1984-12-4', 21);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48823729I', 'Ana Belicia', 'Pérez', 'Giorgini', '1984-12-4', 22);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48372994V', 'Antonio', 'Mendaña', 'Palmáz', '1984-12-4', 23);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('44998833C', 'Paula', 'Maceiras', 'Martínez', '1984-12-4', 24);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('99383745B', 'Victor Manuel', 'Reyes', 'Tasende', '1984-12-4', 25);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('88477563N', 'Juan Carlos', 'Sandá', 'Castro', '1984-12-4', 26);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48237392H', 'Natalia', 'Regueira', 'Brea', '1984-12-4', 27);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('44857368G', 'Rubén', 'Noya', 'Ferro', '1984-12-4', 28);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('84736937F', 'Pablo', 'Quijada', 'Cortes', '1984-12-4', 29);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48374992V', 'Iván', 'Martínez', 'Quintá', '1984-12-4', 30);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48736388B', 'Ana', 'Roura', 'Blanco', '1984-12-4', 31);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('37294759M', 'Mallo', 'Rodríguez', 'Bernal', '1984-12-4', 32);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('58477739R', 'Paula', 'Bujía', 'Otero', '1984-12-4', 33);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('12954736Y', 'Rubén', 'Campos', 'González', '1984-12-4', 34);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('64836729W', 'Marta', 'Álvarez', 'Miguel', '1984-12-4', 35);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('58655383P', 'Juan', 'Quesada', 'Irurita', '1984-12-4', 36);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('48857676Q', 'Luis', 'Ruano', 'Conejero', '1984-12-4', 37);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('85678553Y', 'Javier', 'Domínguez', 'Sánchez', '1984-12-4', 38);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('55865537R', 'Adrián', 'Rojo', 'Beizana', '1984-12-4', 39);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('21223847I', 'Antonio', 'Caruncho', 'Méndez', '1984-12-4', 40);
insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('87654321Q', 'Paciente', 'Prueba', 'Prueba', '1984-4-4', 41);



insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Hemoglobina', 'Gramos/Litro', 'g/l', 1);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Hematocritos', 'Gramos/Litro', 'g/l', 2);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Plaquetas', 'Moles/Litro', 'mol/l', 3);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Hematies', 'Moles/Litro', 'mol/l', 4);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Leucocitos', 'Moles/Litro', 'mol/l', 5);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Glucosa', 'Miligramos/Decilitro', 'mg/dl', 6);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Urea', 'Miligramos/Decilitro', 'mg/dl', 7);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Creatinina', 'Miligramos/Decilitro', 'mg/dl', 8);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Acido úrico', 'Miligramos/Decilitro', 'mg/dl', 9);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Bilirrubina', 'Miligramos/Decilitro', 'mg/dl', 10);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Colesterol', 'Miligramos/Decilitro', 'mg/dl', 11);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Capacidad vital forzada', 'Porcentaje', '%', 12);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Volumen espiratorio forzado', 'Porcentaje', '%', 13);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Flujo espiratorio forzado', 'Porcentaje', '%', 14);
insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Parámetro prueba', 'Porcentaje', '%', 15);



insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Hematología', '2000-10-12', 8, 1);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Bioquímica sérica', '2001-1-23', 8, 2);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Radiografía', '2001-1-23', 9, 3);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Electroencefalograma', '2001-1-23', 3, 4);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Electromiografía', '2001-1-23', 3, 5);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Espirometría', '2001-1-23', 6, 6);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Plantilla prueba', '2001-1-23', 12, 7);



insert into plantillas_parametros (plantillaID, parametroID) values (1, 1);
insert into plantillas_parametros (plantillaID, parametroID) values (1, 2);
insert into plantillas_parametros (plantillaID, parametroID) values (1, 3);
insert into plantillas_parametros (plantillaID, parametroID) values (1, 4);
insert into plantillas_parametros (plantillaID, parametroID) values (1, 5);

insert into plantillas_parametros (plantillaID, parametroID) values (2, 6);
insert into plantillas_parametros (plantillaID, parametroID) values (2, 7);
insert into plantillas_parametros (plantillaID, parametroID) values (2, 8);
insert into plantillas_parametros (plantillaID, parametroID) values (2, 9);
insert into plantillas_parametros (plantillaID, parametroID) values (2, 10);
insert into plantillas_parametros (plantillaID, parametroID) values (2, 11);

insert into plantillas_parametros (plantillaID, parametroID) values (6, 12);
insert into plantillas_parametros (plantillaID, parametroID) values (6, 13);
insert into plantillas_parametros (plantillaID, parametroID) values (6, 14);

insert into plantillas_parametros (plantillaID, parametroID) values (7, 15);


update pacientes set medicoID=13 where pacienteID=41;


insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-4-12', NULL, 'Esto es un informe de prueba', NULL, NULL, 41, 13, 1);


insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (1, '2013-4-13 13:41', NULL, NULL, 41, 13, NULL, 7, 'Esto es una prueba de prueba', NULL, 1);
insert into valores (dato, parametroID, valorID) values (NULL, 15, 1);
insert into pruebas_valores (pruebaID, valorID) values (1, 1);


update pruebas set medicoID=13 where pruebaID=1;