databaseChangeLog = {

	changeSet(author: "Andrea (generated)", id: "1335475159903-1") {
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

	changeSet(author: "Andrea (generated)", id: "1335475159903-2") {
		addNotNullConstraint(columnDataType: "BIGINT", columnName: "paciente_id", tableName: "antecedente")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-3") {
		addNotNullConstraint(columnDataType: "BIGINT", columnName: "profesional_id", tableName: "antecedente")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-4") {
		addNotNullConstraint(columnDataType: "BIT", columnName: "sobre_turno", tableName: "event")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-5") {
		dropForeignKeyConstraint(baseTableName: "paciente", baseTableSchemaName: "medfireweb", constraintName: "FK2CA71371FE662701")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-6") {
		createIndex(indexName: "profesional_id", tableName: "antecedente", unique: "true") {
			column(name: "profesional_id")

			column(name: "paciente_id")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-7") {
		addForeignKeyConstraint(baseColumnNames: "cie10_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5BEE28441", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cie10", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-8") {
		addForeignKeyConstraint(baseColumnNames: "evento_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5334A3E66", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "event", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-9") {
		addForeignKeyConstraint(baseColumnNames: "paciente_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5A4E98773", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "paciente", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-10") {
		addForeignKeyConstraint(baseColumnNames: "profesional_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5D9CAC41", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "profesional", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-11") {
		addForeignKeyConstraint(baseColumnNames: "consulta_id", baseTableName: "estudio_complementario", baseTableSchemaName: "medfireweb", constraintName: "FK4D3DB281DB86C8F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "consulta", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-12") {
		addForeignKeyConstraint(baseColumnNames: "estudio_complementario_id", baseTableName: "estudio_complementario_imagen", baseTableSchemaName: "medfireweb", constraintName: "FK89829F1CCC25C78", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "estudio_complementario", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-13") {
		addForeignKeyConstraint(baseColumnNames: "consulta_id", baseTableName: "event", baseTableSchemaName: "medfireweb", constraintName: "FK5C6729ADB86C8F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "consulta", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-14") {
		addForeignKeyConstraint(baseColumnNames: "consulta_id", baseTableName: "prescripcion", baseTableSchemaName: "medfireweb", constraintName: "FK1B67E9CBDB86C8F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "consulta", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-15") {
		dropIndex(indexName: "fk_pacientes_codigoiva", tableName: "zzzpacientes")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-16") {
		dropIndex(indexName: "fk_pacientes_estadocivil", tableName: "zzzpacientes")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-17") {
		dropIndex(indexName: "fk_pacientes_obrasocial", tableName: "zzzpacientes")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-18") {
		dropIndex(indexName: "fk_pacientes_tipodocumento", tableName: "zzzpacientes")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-19") {
		dropColumn(columnName: "antecedente_id", tableName: "paciente")
	}

	changeSet(author: "Andrea (generated)", id: "1335475159903-20") {
		dropTable(tableName: "zzzpacientes")
	}
}
