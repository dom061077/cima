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
INSERT INTO grupo_Terapeutico(id,grupo,nombre)
SELECT Id_GrupoT,GrupoTerapeutico,Nombre FROM consultorio.grupoterapeutico;
ALTER TABLE `medfireweb`.`grupo_Terapeutico`     CHANGE `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT;

INSERT INTO medfireweb.vademecum (accion_terapeutica,advertencias,asoc2
,composicion_id,
conservacion
,contra_indicacion_id,
dosificacion,embarazoy_lactancia,farmacocinetica,farmacodinamia,farmacologia,grupo_id,indicaciones
,laboratorio_id
,nombre_comercial,prestaciones,principio_id,reacciones_adversas,sobre_dosificacion)
SELECT 	
	`AcciónTerapéutica`, 
	`Advertencias`, 
	`Asoc2`, 
	`Composición`, 
	`Conservación`, 
	`Contraindicaciones`,
	`Dosificación`, 
	`EmbarazoyLactancia`,
	`Farmacocinética`,  
	`Farmacodinamia`, 
	`Farmacología`, 
	`Grupo_Terapeutico`,
	`Indicaciones`, 
	`Laboratorio`,  
	`Nombre_Comercial`, 
	`Presentaciones`, 
	`Principio_Activo`, 
	`ReaccionesAdversas`, 
	`Sobredosificación` 	
	FROM 
	`consultorio`.`nombrecomercial` ;
	


