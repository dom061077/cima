INSERT INTO medfireweb.especialidad_medica(VERSION,descripcion)
SELECT 0,descripcion FROM consultorio.especialidadesmedicas;

INSERT INTO medfireweb.cie10(VERSION,cie10,descripcion) 
SELECT 0,cie10,descripcion FROM consultorio.cie10;


ALTER TABLE `medfireweb`.`composicion`     CHANGE `id` `id` BIGINT(20) NOT NULL;
INSERT INTO medfireweb.composicion(id,descripcion)
SELECT * FROM consultorio.composicion;
ALTER TABLE `medfireweb`.`composicion`     CHANGE `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT;
/*----------------*/
ALTER TABLE `medfireweb`.`laboratorio`     CHANGE `id` `id` BIGINT(20) NOT NULL;
INSERT INTO medfireweb.laboratorio(id,nombre)
SELECT Id_Lab,nombre_laboratorio FROM consultorio.laboratorio;
ALTER TABLE `medfireweb`.`laboratorio`     CHANGE `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT;
/*---------------------------------*/

ALTER TABLE `medfireweb`.`contraindicacion`     CHANGE `id` `id` BIGINT(20) NOT NULL;
INSERT INTO medfireweb.contraindicacion(id,descripcion)
SELECT idcontra,descContraindicacion FROM consultorio.contraindicaciones;
ALTER TABLE `medfireweb`.`contraindicacion`     CHANGE `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT;
/*---------------------------------*/
ALTER TABLE `medfireweb`.`principio_activo`     CHANGE `id` `id` BIGINT(20) NOT NULL;
INSERT INTO medfireweb.principio_activo(id,contraindicaciones,embarazo_lactancia,interacciones,precauciones,principio_activo)
SELECT Id_PrincipioA,contraindicaciones,embarazo_lactancia,interacciones,Precauciones,Principio_Activo FROM consultorio.principioactivo;
ALTER TABLE `medfireweb`.`principio_activo`     CHANGE `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT;
/*--------------------------------------*/
ALTER TABLE `medfireweb`.`grupo_Terapeutico`     CHANGE `id` `id` BIGINT(20) NOT NULL;
INSERT INTO `medfireweb`.grupo_Terapeutico(id,grupo,nombre)
SELECT Id_GrupoT,GrupoTerapeutico,Nombre FROM consultorio.grupoterapeutico;
ALTER TABLE `medfireweb`.`grupo_Terapeutico`     CHANGE `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT;

INSERT INTO medfireweb.vademecum (accion_terapeutica,advertencias,asoc2
,composicion_id,
conservacion
,contra_indicacion_id,
dosificacion,embarazoy_lactancia,farmacocinetica,farmacodinamia,farmacologia,grupo_id,indicaciones
,laboratorio_id
,nombre_comercial,prestaciones,principio_id,reacciones_adversas,sobre_dosificacion
,presentacion)
SELECT 	
	nc.`AcciónTerapéutica`, 
	nc.`Advertencias`, 
	nc.`Asoc2`, 
	nc.`Composicion`, 
	nc.`Conservación`, 
	nc.`Contraindicaciones`,
	nc.`Dosificación`, 
	nc.`EmbarazoyLactancia`,
	nc.`Farmacocinética`,  
	nc.`Farmacodinamia`, 
	nc.`Farmacología`, 
	nc.`Grupo_Terapeutico`,
	nc.`Indicaciones`, 
	nc.`Laboratorio`,  
	nc.`Nombre_Comercial`, 
	nc.`Presentaciones`, 
	nc.`Principio_Activo`, 
	nc.`ReaccionesAdversas`, 
	nc.`Sobredosificación`, 	
	nc.`Presentaciones`	
	FROM 
	`consultorio`.`nombrecomercial` nc
INNER JOIN consultorio.principioactivo p ON nc.principio_activo=p.id_PrincipioA
INNER JOIN consultorio.laboratorio l ON nc.laboratorio=l.id_lab;




