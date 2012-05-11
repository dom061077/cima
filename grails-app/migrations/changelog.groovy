databaseChangeLog = {
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-1") {
			createTable(tableName: "historia_clinica") {
				column(autoIncrement: "true", name: "id", type: "BIGINT") {
					constraints(nullable: "false", primaryKey: "true")
				}
	
				column(name: "version", type: "BIGINT") {
					constraints(nullable: "false")
				}
	
				column(name: "descripcion", type: "VARCHAR(255)")
	
				column(name: "numero", type: "BIGINT") {
					constraints(nullable: "false")
				}
	
				column(name: "paciente_id", type: "BIGINT")
	
				column(name: "t1", type: "VARCHAR(255)")
	
				column(name: "t10", type: "VARCHAR(255)")
	
				column(name: "t11", type: "VARCHAR(255)")
	
				column(name: "t12", type: "VARCHAR(255)")
	
				column(name: "t13", type: "VARCHAR(255)")
	
				column(name: "t14", type: "VARCHAR(255)")
	
				column(name: "t15", type: "VARCHAR(255)")
	
				column(name: "t16", type: "VARCHAR(255)")
	
				column(name: "t17", type: "VARCHAR(255)")
	
				column(name: "t18", type: "VARCHAR(255)")
	
				column(name: "t19", type: "VARCHAR(255)")
	
				column(name: "t2", type: "VARCHAR(255)")
	
				column(name: "t20", type: "VARCHAR(255)")
	
				column(name: "t21", type: "VARCHAR(255)")
	
				column(name: "t22", type: "VARCHAR(255)")
	
				column(name: "t3", type: "VARCHAR(255)")
	
				column(name: "t4", type: "VARCHAR(255)")
	
				column(name: "t5", type: "VARCHAR(255)")
	
				column(name: "t6", type: "VARCHAR(255)")
	
				column(name: "t7", type: "VARCHAR(255)")
	
				column(name: "t8", type: "VARCHAR(255)")
	
				column(name: "t9", type: "VARCHAR(255)")
			}
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-2") {
			addNotNullConstraint(columnDataType: "BIGINT", columnName: "paciente_id", tableName: "antecedente")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-3") {
			addNotNullConstraint(columnDataType: "BIGINT", columnName: "profesional_id", tableName: "antecedente")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-4") {
			addNotNullConstraint(columnDataType: "BIGINT", columnName: "institucion_id", tableName: "consulta")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-5") {
			addNotNullConstraint(columnDataType: "BIT", columnName: "sobre_turno", tableName: "event")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-6") {
			dropNotNullConstraint(columnDataType: "VARCHAR(255)", columnName: "direccion", tableName: "institucion")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-7") {
			dropNotNullConstraint(columnDataType: "VARCHAR(255)", columnName: "telefonos", tableName: "institucion")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-8") {
			addNotNullConstraint(columnDataType: "BIGINT", columnName: "institucion_id", tableName: "obra_social")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-9") {
			addNotNullConstraint(columnDataType: "BIGINT", columnName: "institucion_id", tableName: "paciente")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-10") {
			addNotNullConstraint(columnDataType: "BIT", columnName: "activo", tableName: "profesional")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-11") {
			addNotNullConstraint(columnDataType: "BIGINT", columnName: "institucion_id", tableName: "profesional")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-12") {
			addNotNullConstraint(columnDataType: "BIGINT", columnName: "institucion_id", tableName: "user")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-13") {
			dropForeignKeyConstraint(baseTableName: "paciente", baseTableSchemaName: "cimahost", constraintName: "FK2CA71371FE662701")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-14") {
			createIndex(indexName: "profesional_id", tableName: "antecedente", unique: "true") {
				column(name: "profesional_id")
	
				column(name: "paciente_id")
			}
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-15") {
			addForeignKeyConstraint(baseColumnNames: "cie10_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5BEE28441", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cie10", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-16") {
			addForeignKeyConstraint(baseColumnNames: "evento_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5334A3E66", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "event", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-17") {
			addForeignKeyConstraint(baseColumnNames: "institucion_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F51D20CB21", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "institucion", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-18") {
			addForeignKeyConstraint(baseColumnNames: "paciente_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5A4E98773", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "paciente", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-19") {
			addForeignKeyConstraint(baseColumnNames: "profesional_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5D9CAC41", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "profesional", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-20") {
			addForeignKeyConstraint(baseColumnNames: "consulta_id", baseTableName: "estudio_complementario", baseTableSchemaName: "medfireweb", constraintName: "FK4D3DB281DB86C8F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "consulta", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-21") {
			addForeignKeyConstraint(baseColumnNames: "estudio_complementario_id", baseTableName: "estudio_complementario_imagen", baseTableSchemaName: "medfireweb", constraintName: "FK89829F1CCC25C78", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "estudio_complementario", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-22") {
			addForeignKeyConstraint(baseColumnNames: "consulta_id", baseTableName: "event", baseTableSchemaName: "medfireweb", constraintName: "FK5C6729ADB86C8F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "consulta", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-23") {
			addForeignKeyConstraint(baseColumnNames: "institucion_id", baseTableName: "obra_social", baseTableSchemaName: "medfireweb", constraintName: "FKE20A530A1D20CB21", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "institucion", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-24") {
			addForeignKeyConstraint(baseColumnNames: "institucion_id", baseTableName: "paciente", baseTableSchemaName: "medfireweb", constraintName: "FK2CA713711D20CB21", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "institucion", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-25") {
			addForeignKeyConstraint(baseColumnNames: "consulta_id", baseTableName: "prescripcion", baseTableSchemaName: "medfireweb", constraintName: "FK1B67E9CBDB86C8F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "consulta", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-26") {
			addForeignKeyConstraint(baseColumnNames: "institucion_id", baseTableName: "profesional", baseTableSchemaName: "medfireweb", constraintName: "FK433508CC1D20CB21", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "institucion", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-27") {
			addForeignKeyConstraint(baseColumnNames: "institucion_id", baseTableName: "user", baseTableSchemaName: "medfireweb", constraintName: "FK36EBCB1D20CB21", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "institucion", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-28") {
			dropIndex(indexName: "fk_pacientes_codigoiva", tableName: "zzzpacientes")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-29") {
			dropIndex(indexName: "fk_pacientes_estadocivil", tableName: "zzzpacientes")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-30") {
			dropIndex(indexName: "fk_pacientes_obrasocial", tableName: "zzzpacientes")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-31") {
			dropIndex(indexName: "fk_pacientes_tipodocumento", tableName: "zzzpacientes")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-32") {
			dropColumn(columnName: "antecedente_id", tableName: "paciente")
		}
	
		changeSet(author: "Andrea (generated)", id: "1336508271225-33") {
			dropTable(tableName: "zzzpacientes")
		}
	}
	