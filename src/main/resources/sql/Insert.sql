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



/*insert into departamentos (nombre, supervisorID, departamentoID) values ('Departamento Prueba', NULL, 12);*/
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



/*insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('12345678P', 'Medico', 'Prueba', 'Prueba', '1984-12-5', '12345678P', 'VCJlUFNfAZqvwA29bRkdMw', 12, 13);*/
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('56687691S', 'Ignacio', 'Camacho', 'Durán', '1984-12-4', 'nachocamacho', 'xoBjfAwklHyejxv_EA6Hpg', 6, 1);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('33218974G', 'Elena', 'Salgado', 'Santiago', '1984-12-4', 'elsalsa', '7yHOLzfik1ds_rar4VvwLQ', 6, 2);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('77987213F', 'Felisa', 'Cifuentes', 'Romero', '1984-12-4', 'felici', 'LWYzrqNkY5jDWslq-ZaRZw', 6, 3);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('81974612Z', 'Manuel Antonio', 'Iglesias', 'Izquierdo', '1984-12-4', 'mantonio', 'NWiWtEB9aXAZhLqmCLyb-w', 6, 4);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('44437691B', 'Ana María', 'Contreras', 'Sánchez', '1984-12-4', 'maconsa', 'QCh5uPkcmTyQ46-lethy4Q', 6, 5);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('61895183H', 'Cristina', 'Ramallo', 'Sánchez', '1984-12-4', 'crisrama', '3DwC5ZM07ja086D6e1_iKg', 6, 6);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('24638814R', 'Carlos Manuel', 'Rodriguez', 'Antelo', '1984-12-4', 'camaro', 't9gA8nAAek5T2qQVDGGaMA', 8, 7);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('48376443Q', 'Jose María', 'Mascaró', 'Castillo', '1984-12-4', 'josemama', 'pkb_JAfzXIFUO2lCqIghWA', 8, 8);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('92834658T', 'Fernando', 'Insua', 'Rivero', '1984-12-4', 'nandoriver', 'n9pygSxJlZggcMAuzUwUkw', 8, 9);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('84736294R', 'Juan Carlos', 'Cabrero', 'Cuentas', '1984-12-4', 'juancaca', '1fIRdMf9tH_Wezwxk3iN5Q', 8, 10);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('48390553Q', 'Concepcion', 'Villanueva', 'Cuevas', '1984-12-4', 'conchivilla', 'qIXCXtOULMf_amkotMsPmg', 8, 11);
insert into medicos (dni, nombre, ap1, ap2, fNacim, nombreLogin, password, departamentoID, medicoID)
        values ('58473629W', 'Melani', 'Silva', 'Olivares', '1984-12-4', 'mesilva', 'RfbNM344rRoercQZO1IO3g', 8, 12);



/*update departamentos set supervisorID=13 where departamentoID=12;*/
update departamentos set supervisorID=1 where departamentoID=6;
update departamentos set supervisorID=7 where departamentoID=8;



/*insert into pacientes (dni, nombre, ap1, ap2, fNacim, pacienteID)
        values ('87654321Q', 'Paciente', 'Prueba', 'Prueba', '1984-4-4', 41);*/
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



/*insert into parametros (nombre, unidad, unidadAbr, parametroID) values ('Parámetro prueba', 'Porcentaje', '%', 15);*/
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



/*insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Plantilla prueba', '2001-1-23', 12, 7);*/
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Hematología', '2000-10-12', 8, 1);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Bioquímica sérica', '2001-1-23', 8, 2);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Radiografía', '2001-1-23', 9, 3);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Electroencefalograma', '2001-1-23', 3, 4);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Electromiografía', '2001-1-23', 3, 5);
insert into plantillas (nombre, fecha, departamentoID, plantillaID) values ('Espirometría', '2001-1-23', 6, 6);


/*insert into plantillas_parametros (plantillaID, parametroID) values (7, 15);*/

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


/*update pacientes set medicoID=13 where pacienteID=41;*/

update pacientes set medicoID=1 where pacienteID=2;
update pacientes set medicoID=1 where pacienteID=33;
update pacientes set medicoID=1 where pacienteID=25;
update pacientes set medicoID=1 where pacienteID=7;

update pacientes set medicoID=4 where pacienteID=5;
update pacientes set medicoID=4 where pacienteID=23;
update pacientes set medicoID=4 where pacienteID=28;
update pacientes set medicoID=4 where pacienteID=30;

update pacientes set medicoID=8 where pacienteID=4;
update pacientes set medicoID=8 where pacienteID=15;
update pacientes set medicoID=8 where pacienteID=17;

update pacientes set medicoID=9 where pacienteID=13;


/*insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-4-12', NULL, 'Esto es un informe de prueba', NULL, NULL, 41, 13, 111);*/
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-8-12 22:03', '2013-9-10 14:03', 'El paciente sufre una deficiencia respiratoria.', 'Asma', 'Ventolín', 2, 1, 1);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-8-13 11:13', '2013-10-03 18:13', 'El paciente sufre una deficiencia respiratoria.', 'Asma', 'Ventolín', 28, 4, 2);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-8-17 13:43', '2013-11-22 17:40', 'Altos indices de colesterol en sangre', 'Colesterol', 'Dieta sana', 4, 8, 3);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-8-19 09:55', NULL, 'Altos indices de colesterol en sangre', NULL, NULL, 17, 8, 4);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-8-25 19:00', '2013-9-18 16:33', 'El paciente sufre una deficiencia respiratoria.', 'Asma', 'Ventolín', 33, 1, 5);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-9-02 10:53', '2013-11-03 14:25', 'Altos indices de colesterol en sangre', 'Colesterol', 'Dieta sana', 13, 9, 6);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-9-02 17:01', NULL, 'El paciente sufre una deficiencia respiratoria.', 'Asma', 'Ventolín', 25, 1, 7);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-9-13 22:03', '2013-12-07 22:03', 'El paciente sufre una deficiencia respiratoria.', 'Asma', 'Ventolín', 7, 1, 8);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-9-18 22:03', '2013-10-21 22:03', 'El paciente sufre una deficiencia respiratoria.', 'Asma', 'Ventolín', 30, 4, 9);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-10-01 22:03', '2013-12-20 22:03', 'Altos indices de colesterol en sangre', 'Colesterol', 'Dieta sana', 15, 8, 10);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-10-21 22:03', NULL, 'El paciente sufre una deficiencia respiratoria.', NULL, NULL, 33, 1, 11);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-11-05 22:03', NULL, 'El paciente sufre una deficiencia respiratoria.', NULL, NULL, 7, 1, 12);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-11-09 22:03', NULL, 'Altos indices de colesterol en sangre', NULL, NULL, 13, 9, 13);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-11-18 22:03', NULL, 'Altos indices de colesterol en sangre', NULL, NULL, 4, 8, 14);
insert into informes (fechaInicio, fechaFin, observacionesPrevias, diagnostico, tratamiento, pacienteID, medicoID, informeID)
        values ('2013-12-03 22:03', NULL, 'Altos indices de colesterol en sangre', NULL, NULL, 15, 8, 15);


/*insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (111, '2013-4-13 13:41', NULL, NULL, 41, 13, NULL, 7, 'Esto es una prueba de prueba', NULL, 111);*/
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (1, '2013-8-12 22:40', '2013-8-23 17:30', '2013-8-23 18:30', 2, 1, 1, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 1);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (2, '2013-8-13 22:40', '2013-8-24 17:30', '2013-8-24 18:30', 28, 4, 5, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 2);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (3, '2013-8-17 22:40', '2013-8-22 17:30', '2013-8-22 18:30', 4, 8, 8, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 3);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (4, '2013-8-19 22:40', '2013-8-25 17:30', '2013-8-25 18:30', 17, 8, 9, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 4);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (1, '2013-8-23 22:40', '2013-9-10 17:30', '2013-9-10 18:30', 2, 1, 3, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 5);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (2, '2013-8-24 22:40', '2013-8-30 17:30', '2013-8-30 18:30', 28, 4, 5, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 6);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (4, '2013-8-25 22:40', NULL, NULL, 17, 8, 8, 1, 'Se debe comprobar los niveles de colesterol.', NULL, 7);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (5, '2013-8-25 22:40', '2013-8-29 17:30', '2013-8-29 18:30', 33, 1, 2, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 8);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (5, '2013-8-29 22:40', '2013-9-02 17:30', '2013-9-02 18:30', 33, 1, 4, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 9);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (2, '2013-8-30 22:40', '2013-10-02 17:30', '2013-10-02 18:30', 28, 4, 2, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 10);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (5, '2013-9-02 22:40', '2013-9-17 17:30', '2013-9-17 18:30', 33, 1, 1, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 11);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (6, '2013-9-02 22:40', '2013-10-01 17:30', '2013-10-01 18:30', 13, 9, 9, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 12);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (7, '2013-9-02 22:40', '2013-10-07 17:30', '2013-10-07 18:30', 25, 1, 4, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 13);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (8, '2013-9-13 22:40', '2013-10-21 17:30', '2013-10-21 18:30', 7, 1, 2, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 14);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (9, '2013-9-18 22:40', '2013-10-20 17:30', '2013-10-20 18:30', 30, 4, 4, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 15);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (6, '2013-10-01 22:40', '2013-11-02 17:30', '2013-11-02 18:30', 13, 9, 11, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 16);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (10, '2013-10-01 22:40', '2013-10-23 17:30', '2013-10-23 18:30', 15, 8, 7, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 17);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (7, '2013-10-07 22:40', NULL, NULL, 25, 1, 5, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', NULL, 18);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (10, '2013-11-15 22:40', '2013-12-19 17:30', '2013-12-19 18:30', 15, 8, 9, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 19);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (8, '2013-10-21 22:40', '2013-12-06 17:30', '2013-12-06 18:30', 7, 1, 1, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 20);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (11, '2013-10-21 22:40', '2013-11-02 17:30', '2013-11-02 18:30', 33, 1, 1, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 21);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (10, '2013-10-23 22:40', '2013-11-15 17:30', '2013-11-15 18:30', 15, 8, 8, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 22);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (11, '2013-11-02 22:40', '2013-12-15 17:30', '2013-12-15 18:30', 33, 1, 3, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 23);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (12, '2013-11-05 22:40', '2013-12-04 17:30', '2013-12-04 18:30', 7, 1, 2, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', 'Capacidad pulmonar deficiente.', 24);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (13, '2013-11-09 22:40', '2013-11-13 17:30', '2013-11-13 18:30', 13, 9, 9, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 25);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (14, '2013-11-18 22:40', '2013-12-02 17:30', '2013-12-02 18:30', 4, 8, 10, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 26);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (13, '2013-11-13 22:40', '2013-12-08 17:30', '2013-12-08 18:30', 13, 9, 7, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 27);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (14, '2013-12-02 22:40', '2013-12-16 17:30', '2013-12-16 18:30', 4, 8, 8, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 28);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (15, '2013-12-03 22:40', '2013-12-20 17:30', '2013-12-20 18:30', 15, 8, 7, 1, 'Se debe comprobar los niveles de colesterol.', 'Colesterol alto.', 29);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (12, '2013-12-04 22:40', NULL, NULL, 7, 1, NULL, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', NULL, 30);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (13, '2013-12-08 22:40', NULL, NULL, 13, 9, NULL, 1, 'Se debe comprobar los niveles de colesterol.', NULL, 31);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (11, '2013-12-15 22:40', NULL, NULL, 33, 1, 6, 6, 'Se debe comprobar la capacidad pulmonar del paciente.', NULL, 32);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (14, '2013-12-16 22:40', NULL, NULL, 4, 8, NULL, 1, 'Se debe comprobar los niveles de colesterol.', NULL, 33);
insert into pruebas (informeID, fechaSolicitud, fechaInicio, fechaFin, pacienteID, solicitanteID, medicoID, plantillaID, observacionesSolicitante, observaciones, pruebaID)
        values (15, '2013-12-20 22:40', NULL, NULL, 15, 8, NULL, 1, 'Se debe comprobar los niveles de colesterol.', NULL, 34);


/*insert into valores (dato, parametroID, valorID) values (NULL, 15, 111);*/
insert into valores (dato, parametroID, valorID) values (12, 12, 1);/*1*/
insert into valores (dato, parametroID, valorID) values (45, 13, 2);
insert into valores (dato, parametroID, valorID) values (77, 14, 3);

insert into valores (dato, parametroID, valorID) values (12, 12, 4);/*2*/
insert into valores (dato, parametroID, valorID) values (45, 13, 5);
insert into valores (dato, parametroID, valorID) values (77, 14, 6);

insert into valores (dato, parametroID, valorID) values (12, 1, 7);/*3*/
insert into valores (dato, parametroID, valorID) values (45, 2, 8);
insert into valores (dato, parametroID, valorID) values (77, 3, 9);
insert into valores (dato, parametroID, valorID) values (45, 4, 10);
insert into valores (dato, parametroID, valorID) values (77, 5, 11);

insert into valores (dato, parametroID, valorID) values (12, 1, 12);/*4*/
insert into valores (dato, parametroID, valorID) values (45, 2, 13);
insert into valores (dato, parametroID, valorID) values (77, 3, 14);
insert into valores (dato, parametroID, valorID) values (45, 4, 15);
insert into valores (dato, parametroID, valorID) values (77, 5, 16);

insert into valores (dato, parametroID, valorID) values (12, 12, 17);/*5*/
insert into valores (dato, parametroID, valorID) values (45, 13, 18);
insert into valores (dato, parametroID, valorID) values (77, 14, 19);

insert into valores (dato, parametroID, valorID) values (12, 12, 20);/*6*/
insert into valores (dato, parametroID, valorID) values (45, 13, 21);
insert into valores (dato, parametroID, valorID) values (77, 14, 22);

insert into valores (dato, parametroID, valorID) values (NULL, 1, 23);/*7*/
insert into valores (dato, parametroID, valorID) values (NULL, 2, 24);
insert into valores (dato, parametroID, valorID) values (NULL, 3, 25);
insert into valores (dato, parametroID, valorID) values (NULL, 4, 26);
insert into valores (dato, parametroID, valorID) values (NULL, 5, 27);

insert into valores (dato, parametroID, valorID) values (12, 12, 28);/*8*/
insert into valores (dato, parametroID, valorID) values (45, 13, 29);
insert into valores (dato, parametroID, valorID) values (77, 14, 30);

insert into valores (dato, parametroID, valorID) values (12, 12, 31);/*9*/
insert into valores (dato, parametroID, valorID) values (45, 13, 32);
insert into valores (dato, parametroID, valorID) values (77, 14, 33);

insert into valores (dato, parametroID, valorID) values (12, 12, 34);/*10*/
insert into valores (dato, parametroID, valorID) values (45, 13, 35);
insert into valores (dato, parametroID, valorID) values (77, 14, 36);

insert into valores (dato, parametroID, valorID) values (12, 12, 37);/*11*/
insert into valores (dato, parametroID, valorID) values (45, 13, 38);
insert into valores (dato, parametroID, valorID) values (77, 14, 39);

insert into valores (dato, parametroID, valorID) values (12, 1, 40);/*12*/
insert into valores (dato, parametroID, valorID) values (45, 2, 41);
insert into valores (dato, parametroID, valorID) values (77, 3, 42);
insert into valores (dato, parametroID, valorID) values (45, 4, 43);
insert into valores (dato, parametroID, valorID) values (77, 5, 44);

insert into valores (dato, parametroID, valorID) values (12, 12, 45);/*13*/
insert into valores (dato, parametroID, valorID) values (45, 13, 46);
insert into valores (dato, parametroID, valorID) values (77, 14, 47);

insert into valores (dato, parametroID, valorID) values (12, 12, 48);/*14*/
insert into valores (dato, parametroID, valorID) values (45, 13, 49);
insert into valores (dato, parametroID, valorID) values (77, 14, 50);

insert into valores (dato, parametroID, valorID) values (12, 12, 51);/*15*/
insert into valores (dato, parametroID, valorID) values (45, 13, 52);
insert into valores (dato, parametroID, valorID) values (77, 14, 53);

insert into valores (dato, parametroID, valorID) values (12, 1, 54);/*16*/
insert into valores (dato, parametroID, valorID) values (45, 2, 55);
insert into valores (dato, parametroID, valorID) values (77, 3, 56);
insert into valores (dato, parametroID, valorID) values (45, 4, 57);
insert into valores (dato, parametroID, valorID) values (77, 5, 58);

insert into valores (dato, parametroID, valorID) values (12, 1, 59);/*17*/
insert into valores (dato, parametroID, valorID) values (45, 2, 60);
insert into valores (dato, parametroID, valorID) values (77, 3, 61);
insert into valores (dato, parametroID, valorID) values (45, 4, 62);
insert into valores (dato, parametroID, valorID) values (77, 5, 63);

insert into valores (dato, parametroID, valorID) values (12, 12, 64);/*18*/
insert into valores (dato, parametroID, valorID) values (45, 13, 65);
insert into valores (dato, parametroID, valorID) values (77, 14, 66);

insert into valores (dato, parametroID, valorID) values (12, 1, 67);/*19*/
insert into valores (dato, parametroID, valorID) values (45, 2, 68);
insert into valores (dato, parametroID, valorID) values (77, 3, 69);
insert into valores (dato, parametroID, valorID) values (45, 4, 70);
insert into valores (dato, parametroID, valorID) values (77, 5, 71);

insert into valores (dato, parametroID, valorID) values (12, 12, 72);/*20*/
insert into valores (dato, parametroID, valorID) values (45, 13, 73);
insert into valores (dato, parametroID, valorID) values (77, 14, 74);

insert into valores (dato, parametroID, valorID) values (12, 12, 75);/*21*/
insert into valores (dato, parametroID, valorID) values (45, 13, 76);
insert into valores (dato, parametroID, valorID) values (77, 14, 77);

insert into valores (dato, parametroID, valorID) values (12, 1, 78);/*22*/
insert into valores (dato, parametroID, valorID) values (45, 2, 79);
insert into valores (dato, parametroID, valorID) values (77, 3, 80);
insert into valores (dato, parametroID, valorID) values (45, 4, 81);
insert into valores (dato, parametroID, valorID) values (77, 5, 82);

insert into valores (dato, parametroID, valorID) values (12, 12, 83);/*23*/
insert into valores (dato, parametroID, valorID) values (45, 13, 84);
insert into valores (dato, parametroID, valorID) values (77, 14, 85);

insert into valores (dato, parametroID, valorID) values (12, 12, 86);/*24*/
insert into valores (dato, parametroID, valorID) values (45, 13, 87);
insert into valores (dato, parametroID, valorID) values (77, 14, 88);

insert into valores (dato, parametroID, valorID) values (12, 1, 89);/*25*/
insert into valores (dato, parametroID, valorID) values (45, 2, 90);
insert into valores (dato, parametroID, valorID) values (77, 3, 91);
insert into valores (dato, parametroID, valorID) values (45, 4, 92);
insert into valores (dato, parametroID, valorID) values (77, 5, 93);

insert into valores (dato, parametroID, valorID) values (12, 1, 94);/*26*/
insert into valores (dato, parametroID, valorID) values (45, 2, 95);
insert into valores (dato, parametroID, valorID) values (77, 3, 96);
insert into valores (dato, parametroID, valorID) values (45, 4, 97);
insert into valores (dato, parametroID, valorID) values (77, 5, 98);

insert into valores (dato, parametroID, valorID) values (12, 1, 99);/*27*/
insert into valores (dato, parametroID, valorID) values (45, 2, 100);
insert into valores (dato, parametroID, valorID) values (77, 3, 101);
insert into valores (dato, parametroID, valorID) values (45, 4, 102);
insert into valores (dato, parametroID, valorID) values (77, 5, 103);

insert into valores (dato, parametroID, valorID) values (12, 1, 104);/*28*/
insert into valores (dato, parametroID, valorID) values (45, 2, 105);
insert into valores (dato, parametroID, valorID) values (77, 3, 106);
insert into valores (dato, parametroID, valorID) values (45, 4, 107);
insert into valores (dato, parametroID, valorID) values (77, 5, 108);

insert into valores (dato, parametroID, valorID) values (12, 1, 109);/*29*/
insert into valores (dato, parametroID, valorID) values (45, 2, 110);
insert into valores (dato, parametroID, valorID) values (77, 3, 111);
insert into valores (dato, parametroID, valorID) values (45, 4, 112);
insert into valores (dato, parametroID, valorID) values (77, 5, 113);

insert into valores (dato, parametroID, valorID) values (NULL, 12, 114);/*30*/
insert into valores (dato, parametroID, valorID) values (NULL, 13, 115);
insert into valores (dato, parametroID, valorID) values (NULL, 14, 116);

insert into valores (dato, parametroID, valorID) values (NULL, 1, 117);/*31*/
insert into valores (dato, parametroID, valorID) values (NULL, 2, 118);
insert into valores (dato, parametroID, valorID) values (NULL, 3, 119);
insert into valores (dato, parametroID, valorID) values (NULL, 4, 120);
insert into valores (dato, parametroID, valorID) values (NULL, 5, 121);

insert into valores (dato, parametroID, valorID) values (NULL, 12, 122);/*32*/
insert into valores (dato, parametroID, valorID) values (NULL, 13, 123);
insert into valores (dato, parametroID, valorID) values (NULL, 14, 124);

insert into valores (dato, parametroID, valorID) values (NULL, 1, 125);/*33*/
insert into valores (dato, parametroID, valorID) values (NULL, 2, 126);
insert into valores (dato, parametroID, valorID) values (NULL, 3, 127);
insert into valores (dato, parametroID, valorID) values (NULL, 4, 128);
insert into valores (dato, parametroID, valorID) values (NULL, 5, 129);

insert into valores (dato, parametroID, valorID) values (NULL, 1, 130);/*34*/
insert into valores (dato, parametroID, valorID) values (NULL, 2, 131);
insert into valores (dato, parametroID, valorID) values (NULL, 3, 132);
insert into valores (dato, parametroID, valorID) values (NULL, 4, 133);
insert into valores (dato, parametroID, valorID) values (NULL, 5, 134);



/*insert into pruebas_valores (pruebaID, valorID) values (111, 111);*/
insert into pruebas_valores (pruebaID, valorID) values (1, 1);
insert into pruebas_valores (pruebaID, valorID) values (1, 2);
insert into pruebas_valores (pruebaID, valorID) values (1, 3);

insert into pruebas_valores (pruebaID, valorID) values (2, 4);
insert into pruebas_valores (pruebaID, valorID) values (2, 5);
insert into pruebas_valores (pruebaID, valorID) values (2, 6);

insert into pruebas_valores (pruebaID, valorID) values (3, 7);
insert into pruebas_valores (pruebaID, valorID) values (3, 8);
insert into pruebas_valores (pruebaID, valorID) values (3, 9);
insert into pruebas_valores (pruebaID, valorID) values (3, 10);
insert into pruebas_valores (pruebaID, valorID) values (3, 11);

insert into pruebas_valores (pruebaID, valorID) values (4, 12);
insert into pruebas_valores (pruebaID, valorID) values (4, 13);
insert into pruebas_valores (pruebaID, valorID) values (4, 14);
insert into pruebas_valores (pruebaID, valorID) values (4, 15);
insert into pruebas_valores (pruebaID, valorID) values (4, 16);

insert into pruebas_valores (pruebaID, valorID) values (5, 17);
insert into pruebas_valores (pruebaID, valorID) values (5, 18);
insert into pruebas_valores (pruebaID, valorID) values (5, 19);

insert into pruebas_valores (pruebaID, valorID) values (6, 20);
insert into pruebas_valores (pruebaID, valorID) values (6, 21);
insert into pruebas_valores (pruebaID, valorID) values (6, 22);

insert into pruebas_valores (pruebaID, valorID) values (7, 23);
insert into pruebas_valores (pruebaID, valorID) values (7, 24);
insert into pruebas_valores (pruebaID, valorID) values (7, 25);
insert into pruebas_valores (pruebaID, valorID) values (7, 26);
insert into pruebas_valores (pruebaID, valorID) values (7, 27);

insert into pruebas_valores (pruebaID, valorID) values (8, 28);
insert into pruebas_valores (pruebaID, valorID) values (8, 29);
insert into pruebas_valores (pruebaID, valorID) values (8, 30);

insert into pruebas_valores (pruebaID, valorID) values (9, 31);
insert into pruebas_valores (pruebaID, valorID) values (9, 32);
insert into pruebas_valores (pruebaID, valorID) values (9, 33);

insert into pruebas_valores (pruebaID, valorID) values (10, 34);
insert into pruebas_valores (pruebaID, valorID) values (10, 35);
insert into pruebas_valores (pruebaID, valorID) values (10, 36);

insert into pruebas_valores (pruebaID, valorID) values (11, 37);
insert into pruebas_valores (pruebaID, valorID) values (11, 38);
insert into pruebas_valores (pruebaID, valorID) values (11, 39);

insert into pruebas_valores (pruebaID, valorID) values (12, 40);
insert into pruebas_valores (pruebaID, valorID) values (12, 41);
insert into pruebas_valores (pruebaID, valorID) values (12, 42);
insert into pruebas_valores (pruebaID, valorID) values (12, 43);
insert into pruebas_valores (pruebaID, valorID) values (12, 44);

insert into pruebas_valores (pruebaID, valorID) values (13, 45);
insert into pruebas_valores (pruebaID, valorID) values (13, 46);
insert into pruebas_valores (pruebaID, valorID) values (13, 47);

insert into pruebas_valores (pruebaID, valorID) values (14, 48);
insert into pruebas_valores (pruebaID, valorID) values (14, 49);
insert into pruebas_valores (pruebaID, valorID) values (14, 50);

insert into pruebas_valores (pruebaID, valorID) values (15, 51);
insert into pruebas_valores (pruebaID, valorID) values (15, 52);
insert into pruebas_valores (pruebaID, valorID) values (15, 53);

insert into pruebas_valores (pruebaID, valorID) values (16, 54);
insert into pruebas_valores (pruebaID, valorID) values (16, 55);
insert into pruebas_valores (pruebaID, valorID) values (16, 56);
insert into pruebas_valores (pruebaID, valorID) values (16, 57);
insert into pruebas_valores (pruebaID, valorID) values (16, 58);

insert into pruebas_valores (pruebaID, valorID) values (17, 59);
insert into pruebas_valores (pruebaID, valorID) values (17, 60);
insert into pruebas_valores (pruebaID, valorID) values (17, 61);
insert into pruebas_valores (pruebaID, valorID) values (17, 62);
insert into pruebas_valores (pruebaID, valorID) values (17, 63);

insert into pruebas_valores (pruebaID, valorID) values (18, 64);
insert into pruebas_valores (pruebaID, valorID) values (18, 65);
insert into pruebas_valores (pruebaID, valorID) values (18, 66);

insert into pruebas_valores (pruebaID, valorID) values (19, 67);
insert into pruebas_valores (pruebaID, valorID) values (19, 68);
insert into pruebas_valores (pruebaID, valorID) values (19, 69);
insert into pruebas_valores (pruebaID, valorID) values (19, 70);
insert into pruebas_valores (pruebaID, valorID) values (19, 71);

insert into pruebas_valores (pruebaID, valorID) values (20, 72);
insert into pruebas_valores (pruebaID, valorID) values (20, 73);
insert into pruebas_valores (pruebaID, valorID) values (20, 74);

insert into pruebas_valores (pruebaID, valorID) values (21, 75);
insert into pruebas_valores (pruebaID, valorID) values (21, 76);
insert into pruebas_valores (pruebaID, valorID) values (21, 77);

insert into pruebas_valores (pruebaID, valorID) values (22, 78);
insert into pruebas_valores (pruebaID, valorID) values (22, 79);
insert into pruebas_valores (pruebaID, valorID) values (22, 80);
insert into pruebas_valores (pruebaID, valorID) values (22, 81);
insert into pruebas_valores (pruebaID, valorID) values (22, 82);

insert into pruebas_valores (pruebaID, valorID) values (23, 83);
insert into pruebas_valores (pruebaID, valorID) values (23, 84);
insert into pruebas_valores (pruebaID, valorID) values (23, 85);

insert into pruebas_valores (pruebaID, valorID) values (24, 86);
insert into pruebas_valores (pruebaID, valorID) values (24, 87);
insert into pruebas_valores (pruebaID, valorID) values (24, 88);

insert into pruebas_valores (pruebaID, valorID) values (25, 89);
insert into pruebas_valores (pruebaID, valorID) values (25, 90);
insert into pruebas_valores (pruebaID, valorID) values (25, 91);
insert into pruebas_valores (pruebaID, valorID) values (25, 92);
insert into pruebas_valores (pruebaID, valorID) values (25, 93);

insert into pruebas_valores (pruebaID, valorID) values (26, 94);
insert into pruebas_valores (pruebaID, valorID) values (26, 95);
insert into pruebas_valores (pruebaID, valorID) values (26, 96);
insert into pruebas_valores (pruebaID, valorID) values (26, 97);
insert into pruebas_valores (pruebaID, valorID) values (26, 98);

insert into pruebas_valores (pruebaID, valorID) values (27, 99);
insert into pruebas_valores (pruebaID, valorID) values (27, 100);
insert into pruebas_valores (pruebaID, valorID) values (27, 101);
insert into pruebas_valores (pruebaID, valorID) values (27, 102);
insert into pruebas_valores (pruebaID, valorID) values (27, 103);

insert into pruebas_valores (pruebaID, valorID) values (28, 104);
insert into pruebas_valores (pruebaID, valorID) values (28, 105);
insert into pruebas_valores (pruebaID, valorID) values (28, 106);
insert into pruebas_valores (pruebaID, valorID) values (28, 107);
insert into pruebas_valores (pruebaID, valorID) values (28, 108);

insert into pruebas_valores (pruebaID, valorID) values (29, 109);
insert into pruebas_valores (pruebaID, valorID) values (29, 110);
insert into pruebas_valores (pruebaID, valorID) values (29, 111);
insert into pruebas_valores (pruebaID, valorID) values (29, 112);
insert into pruebas_valores (pruebaID, valorID) values (29, 113);

insert into pruebas_valores (pruebaID, valorID) values (30, 114);
insert into pruebas_valores (pruebaID, valorID) values (30, 115);
insert into pruebas_valores (pruebaID, valorID) values (30, 116);

insert into pruebas_valores (pruebaID, valorID) values (31, 117);
insert into pruebas_valores (pruebaID, valorID) values (31, 118);
insert into pruebas_valores (pruebaID, valorID) values (31, 119);
insert into pruebas_valores (pruebaID, valorID) values (31, 120);
insert into pruebas_valores (pruebaID, valorID) values (31, 121);

insert into pruebas_valores (pruebaID, valorID) values (32, 122);
insert into pruebas_valores (pruebaID, valorID) values (32, 123);
insert into pruebas_valores (pruebaID, valorID) values (32, 124);

insert into pruebas_valores (pruebaID, valorID) values (33, 125);
insert into pruebas_valores (pruebaID, valorID) values (33, 126);
insert into pruebas_valores (pruebaID, valorID) values (33, 127);
insert into pruebas_valores (pruebaID, valorID) values (33, 128);
insert into pruebas_valores (pruebaID, valorID) values (33, 129);

insert into pruebas_valores (pruebaID, valorID) values (34, 130);
insert into pruebas_valores (pruebaID, valorID) values (34, 131);
insert into pruebas_valores (pruebaID, valorID) values (34, 132);
insert into pruebas_valores (pruebaID, valorID) values (34, 133);
insert into pruebas_valores (pruebaID, valorID) values (34, 134);


/*update pruebas set medicoID=13 where pruebaID=111;*/
update pruebas set medicoID=1 where pruebaID=1;
update pruebas set medicoID=5 where pruebaID=2;
update pruebas set medicoID=8 where pruebaID=3;
update pruebas set medicoID=9 where pruebaID=4;
update pruebas set medicoID=3 where pruebaID=5;
update pruebas set medicoID=5 where pruebaID=6;
update pruebas set medicoID=8 where pruebaID=7;
update pruebas set medicoID=2 where pruebaID=8;
update pruebas set medicoID=4 where pruebaID=9;
update pruebas set medicoID=2 where pruebaID=10;
update pruebas set medicoID=1 where pruebaID=11;
update pruebas set medicoID=9 where pruebaID=12;
update pruebas set medicoID=4 where pruebaID=13;
update pruebas set medicoID=2 where pruebaID=14;
update pruebas set medicoID=4 where pruebaID=15;
update pruebas set medicoID=11 where pruebaID=16;
update pruebas set medicoID=7 where pruebaID=17;
update pruebas set medicoID=5 where pruebaID=18;
update pruebas set medicoID=9 where pruebaID=19;
update pruebas set medicoID=1 where pruebaID=20;
update pruebas set medicoID=1 where pruebaID=21;
update pruebas set medicoID=8 where pruebaID=22;
update pruebas set medicoID=3 where pruebaID=23;
update pruebas set medicoID=2 where pruebaID=24;
update pruebas set medicoID=9 where pruebaID=25;
update pruebas set medicoID=10 where pruebaID=26;
update pruebas set medicoID=7 where pruebaID=27;
update pruebas set medicoID=8 where pruebaID=28;
update pruebas set medicoID=7 where pruebaID=29;
update pruebas set medicoID=6 where pruebaID=32;