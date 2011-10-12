databaseChangeLog = {

	changeSet(author: "Andrea (generated)", id: "1318448170048-1") {
		dropForeignKeyConstraint(baseTableName: "calendar_event", baseTableSchemaName: "medfireweb", constraintName: "FK2A9BEA59A0CC175F")
	}

	changeSet(author: "Andrea (generated)", id: "1318448170048-2") {
		dropIndex(indexName: "name", tableName: "calendar_event_type")
	}

	changeSet(author: "Andrea (generated)", id: "1318448170048-3") {
		dropIndex(indexName: "FKCC0C2E97A4E98773", tableName: "historia_clinica")
	}

	changeSet(author: "Andrea (generated)", id: "1318448170048-4") {
		dropTable(tableName: "calendar_event")
	}

	changeSet(author: "Andrea (generated)", id: "1318448170048-5") {
		dropTable(tableName: "calendar_event_type")
	}

	changeSet(author: "Andrea (generated)", id: "1318448170048-6") {
		dropTable(tableName: "historia_clinica")
	}
}
