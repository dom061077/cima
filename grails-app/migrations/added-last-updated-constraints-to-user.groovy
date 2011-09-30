databaseChangeLog = {

	changeSet(author: "danielmedina (generated)", id: "1317390859320-1") {
		createTable(tableName: "antecedentes") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t1", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t10", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t11", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t12", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t13", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t14", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t15", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t16", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t17", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t18", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t19", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t2", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t20", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t21", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t22", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t3", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t4", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t5", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t6", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t7", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t8", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "t9", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-2") {
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

			column(name: "paciente_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "danielmedina (generated)", id: "1317390859320-3") {
		createTable(tableName: "historia_clinica_consulta") {
			column(name: "historia_clinica_consultas_id", type: "BIGINT")

			column(name: "consulta_id", type: "BIGINT")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-4") {
		addColumn(tableName: "profesional") {
			column(name: "image_extension", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-5") {
		dropNotNullConstraint(columnDataType: "BIGINT", columnName: "antecedente_label_id", tableName: "profesional")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-6") {
		dropForeignKeyConstraint(baseTableName: "event", baseTableSchemaName: "cimahost", constraintName: "FK5C6729AA4E98773")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-7") {
		dropForeignKeyConstraint(baseTableName: "event", baseTableSchemaName: "cimahost", constraintName: "FK5C6729AD9CAC41")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-8") {
		dropForeignKeyConstraint(baseTableName: "event", baseTableSchemaName: "cimahost", constraintName: "FK5C6729A8D7F05B3")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-9") {
		dropForeignKeyConstraint(baseTableName: "laboratorio", baseTableSchemaName: "cimahost", constraintName: "FK67C7852C5EFC80E1")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-10") {
		dropForeignKeyConstraint(baseTableName: "localidad", baseTableSchemaName: "cimahost", constraintName: "FKB8337069D0438A61")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-11") {
		dropForeignKeyConstraint(baseTableName: "paciente", baseTableSchemaName: "cimahost", constraintName: "FK2CA71371FE662701")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-12") {
		dropForeignKeyConstraint(baseTableName: "paciente", baseTableSchemaName: "cimahost", constraintName: "FK2CA713715EFC80E1")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-13") {
		dropForeignKeyConstraint(baseTableName: "paciente", baseTableSchemaName: "cimahost", constraintName: "FK2CA71371BA59ECD8")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-14") {
		dropForeignKeyConstraint(baseTableName: "profesional", baseTableSchemaName: "cimahost", constraintName: "FK433508CC535CC356")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-15") {
		dropForeignKeyConstraint(baseTableName: "profesional", baseTableSchemaName: "cimahost", constraintName: "FK433508CC78A0D46E")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-16") {
		dropForeignKeyConstraint(baseTableName: "profesional", baseTableSchemaName: "cimahost", constraintName: "FK433508CC5EFC80E1")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-17") {
		dropForeignKeyConstraint(baseTableName: "role_people", baseTableSchemaName: "cimahost", constraintName: "FK28B75E78E85441D3")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-18") {
		dropForeignKeyConstraint(baseTableName: "role_people", baseTableSchemaName: "cimahost", constraintName: "FK28B75E788D7F05B3")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-19") {
		dropForeignKeyConstraint(baseTableName: "user", baseTableSchemaName: "cimahost", constraintName: "FK36EBCB5DA7DC8C")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-20") {
		dropForeignKeyConstraint(baseTableName: "user_profesional", baseTableSchemaName: "cimahost", constraintName: "FKE635FA58D9CAC41")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-21") {
		dropForeignKeyConstraint(baseTableName: "user_profesional", baseTableSchemaName: "cimahost", constraintName: "FKE635FA585DC33DC8")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-22") {
		dropForeignKeyConstraint(baseTableName: "vademecum", baseTableSchemaName: "cimahost", constraintName: "FK105534B71E6FEDE1")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-23") {
		dropForeignKeyConstraint(baseTableName: "vademecum", baseTableSchemaName: "cimahost", constraintName: "FK105534B7936A69B6")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-24") {
		dropForeignKeyConstraint(baseTableName: "vademecum", baseTableSchemaName: "cimahost", constraintName: "FK105534B767804142")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-25") {
		dropForeignKeyConstraint(baseTableName: "vademecum", baseTableSchemaName: "cimahost", constraintName: "FK105534B724E6D041")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-26") {
		dropForeignKeyConstraint(baseTableName: "vademecum", baseTableSchemaName: "cimahost", constraintName: "FK105534B74DFCC011")
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-27") {
		createIndex(indexName: "FKCC0C2E97A4E98773", tableName: "historia_clinica", unique: "false") {
			column(name: "paciente_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-28") {
		createIndex(indexName: "FK2EA6FE9DBD408467", tableName: "historia_clinica_consulta", unique: "false") {
			column(name: "historia_clinica_consultas_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317390859320-29") {
		createIndex(indexName: "FK2EA6FE9DDB86C8F3", tableName: "historia_clinica_consulta", unique: "false") {
			column(name: "consulta_id")
		}
	}
}
