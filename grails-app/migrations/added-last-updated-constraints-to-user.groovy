databaseChangeLog = {

	changeSet(author: "Andrea (generated)", id: "1317416942278-1") {
		dropNotNullConstraint(columnDataType: "BIGINT", columnName: "antecedente_label_id", tableName: "profesional")
	}

	changeSet(author: "Andrea (generated)", id: "1317416942278-2") {
		dropForeignKeyConstraint(baseTableName: "calendar_reminder", baseTableSchemaName: "cimainap", constraintName: "FK4690BC53AFAD62B")
	}

	changeSet(author: "Andrea (generated)", id: "1317416942278-3") {
		dropForeignKeyConstraint(baseTableName: "historia_clinica", baseTableSchemaName: "cimainap", constraintName: "FKCC0C2E97A4E98773")
	}

	changeSet(author: "Andrea (generated)", id: "1317416942278-4") {
		addForeignKeyConstraint(baseColumnNames: "cie10_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5BEE28441", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cie10", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317416942278-5") {
		addForeignKeyConstraint(baseColumnNames: "paciente_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5A4E98773", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "paciente", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317416942278-6") {
		addForeignKeyConstraint(baseColumnNames: "profesional_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5D9CAC41", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "profesional", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317416942278-7") {
		addForeignKeyConstraint(baseColumnNames: "consulta_id", baseTableName: "estudio_complementario", baseTableSchemaName: "medfireweb", constraintName: "FK4D3DB281DB86C8F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "consulta", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317416942278-8") {
		addForeignKeyConstraint(baseColumnNames: "estudio_complementario_id", baseTableName: "estudio_complementario_imagen", baseTableSchemaName: "medfireweb", constraintName: "FK89829F1CCC25C78", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "estudio_complementario", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317416942278-9") {
		addForeignKeyConstraint(baseColumnNames: "consulta_id", baseTableName: "prescripcion", baseTableSchemaName: "medfireweb", constraintName: "FK1B67E9CBDB86C8F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "consulta", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317416942278-10") {
		dropTable(tableName: "calendar_reminder")
	}

	changeSet(author: "Andrea (generated)", id: "1317416942278-11") {
		dropTable(tableName: "profesional_bi_image")
	}
}
