databaseChangeLog = {

	changeSet(author: "Andrea (generated)", id: "1317413279376-1") {
		createTable(tableName: "antecedente") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "antecedente_familiar", type: "LONGTEXT")

			column(name: "t1", type: "VARCHAR(255)")

			column(name: "t10", type: "VARCHAR(255)")

			column(name: "t10check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t11", type: "VARCHAR(255)")

			column(name: "t11check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t12", type: "VARCHAR(255)")

			column(name: "t12check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t13", type: "VARCHAR(255)")

			column(name: "t13check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t14", type: "VARCHAR(255)")

			column(name: "t14check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t15", type: "VARCHAR(255)")

			column(name: "t15check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t16", type: "VARCHAR(255)")

			column(name: "t16check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t17", type: "VARCHAR(255)")

			column(name: "t17check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t18", type: "VARCHAR(255)")

			column(name: "t18check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t19", type: "VARCHAR(255)")

			column(name: "t19check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t1check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t2", type: "VARCHAR(255)")

			column(name: "t20", type: "VARCHAR(255)")

			column(name: "t20check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t21", type: "VARCHAR(255)")

			column(name: "t21check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t22", type: "VARCHAR(255)")

			column(name: "t22check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t2check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t3", type: "VARCHAR(255)")

			column(name: "t3check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t4", type: "VARCHAR(255)")

			column(name: "t4check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t5", type: "VARCHAR(255)")

			column(name: "t5check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t6", type: "VARCHAR(255)")

			column(name: "t6check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t7", type: "VARCHAR(255)")

			column(name: "t7check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t8", type: "VARCHAR(255)")

			column(name: "t8check", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "t9", type: "VARCHAR(255)")

			column(name: "t9check", type: "BIT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-2") {
		createTable(tableName: "antecedente_label") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "t10label", type: "VARCHAR(255)")

			column(name: "t11label", type: "VARCHAR(255)")

			column(name: "t12label", type: "VARCHAR(255)")

			column(name: "t13label", type: "VARCHAR(255)")

			column(name: "t14label", type: "VARCHAR(255)")

			column(name: "t15label", type: "VARCHAR(255)")

			column(name: "t16label", type: "VARCHAR(255)")

			column(name: "t17label", type: "VARCHAR(255)")

			column(name: "t18label", type: "VARCHAR(255)")

			column(name: "t19label", type: "VARCHAR(255)")

			column(name: "t1label", type: "VARCHAR(255)")

			column(name: "t20label", type: "VARCHAR(255)")

			column(name: "t21label", type: "VARCHAR(255)")

			column(name: "t22label", type: "VARCHAR(255)")

			column(name: "t2label", type: "VARCHAR(255)")

			column(name: "t3label", type: "VARCHAR(255)")

			column(name: "t4label", type: "VARCHAR(255)")

			column(name: "t5label", type: "VARCHAR(255)")

			column(name: "t6label", type: "VARCHAR(255)")

			column(name: "t7label", type: "VARCHAR(255)")

			column(name: "t8label", type: "VARCHAR(255)")

			column(name: "t9label", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-3") {
		createTable(tableName: "audit_log") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "actor", type: "VARCHAR(255)")

			column(name: "class_name", type: "VARCHAR(255)")

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "event_name", type: "VARCHAR(255)")

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "new_value", type: "VARCHAR(255)")

			column(name: "old_value", type: "VARCHAR(255)")

			column(name: "persisted_object_id", type: "VARCHAR(255)")

			column(name: "persisted_object_version", type: "BIGINT")

			column(name: "property_name", type: "VARCHAR(255)")

			column(name: "uri", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-4") {
		createTable(tableName: "bi_images") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "data", type: "LONGBLOB") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "type", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-5") {
		createTable(tableName: "calendar_event") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "description", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(name: "end_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "event_type_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "location", type: "VARCHAR(255)")

			column(defaultValue: "", name: "name", type: "VARCHAR(60)") {
				constraints(nullable: "false")
			}

			column(name: "start_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "userid", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-6") {
		createTable(tableName: "calendar_event_type") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "name", type: "VARCHAR(25)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-7") {
		createTable(tableName: "calendar_reminder") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "calendar_event_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "trigger_date", type: "DATETIME")

			column(defaultValue: "", name: "unit_type", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "units", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "userid", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-8") {
		createTable(tableName: "cie10") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "cie10", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-9") {
		createTable(tableName: "composicion") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-10") {
		createTable(tableName: "consulta") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "actitud", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "cie10_id", type: "BIGINT")

			column(defaultValue: "", name: "contenido", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "estado", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "facie", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "fc", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "fecha_alta", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "fecha_consulta", type: "DATE") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "fr", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "habito", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "impresion", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "marcha", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "paciente_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "pesoa", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "pesoh", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "profesional_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "psiqui", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "pulso", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "ta", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "taxilar", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "trectal", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "ubicacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-11") {
		createTable(tableName: "contraindicacion") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-12") {
		createTable(tableName: "especialidad_medica") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-13") {
		createTable(tableName: "estado_civil") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-14") {
		createTable(tableName: "estudio_complementario") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "consulta_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "pedido", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "resultado", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(name: "secuencia", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-15") {
		createTable(tableName: "estudio_complementario_imagen") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "estudio_complementario_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "image_extension", type: "VARCHAR(255)")

			column(name: "secuencia", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-16") {
		createTable(tableName: "event") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "all_day", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "atendido", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "end", type: "INT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "estado", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "fecha_end", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "fecha_start", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "paciente_id", type: "BIGINT")

			column(name: "profesional_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "start", type: "INT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "titulo", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-17") {
		createTable(tableName: "grupo_terapeutico") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "grupo", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-18") {
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

	changeSet(author: "Andrea (generated)", id: "1317413279376-19") {
		createTable(tableName: "iva") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-20") {
		createTable(tableName: "laboratorio") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "codigo_postal", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "comentario", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "contacto", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "direccion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "localidad_id", type: "BIGINT")

			column(defaultValue: "", name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "telefono", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-21") {
		createTable(tableName: "localidad") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "codigo_postal", type: "INT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "provincia_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-22") {
		createTable(tableName: "obra_social") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "codigo_postal", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "contacto", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "cuit", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "domicilio", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "habilitada", type: "BIT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "razon_social", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "telefono", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-23") {
		createTable(tableName: "paciente") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "antecedente_id", type: "BIGINT")

			column(defaultValue: "", name: "apellido", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "codigo_postal", type: "VARCHAR(255)")

			column(name: "cuit", type: "VARCHAR(255)")

			column(name: "dni", type: "BIGINT")

			column(name: "domicilio", type: "VARCHAR(255)")

			column(name: "email", type: "VARCHAR(255)")

			column(name: "estado_civil", type: "VARCHAR(255)")

			column(name: "estado_iva", type: "VARCHAR(255)")

			column(name: "fecha_nacimiento", type: "DATETIME")

			column(name: "localidad_id", type: "BIGINT")

			column(name: "medico_enviante", type: "VARCHAR(255)")

			column(defaultValue: "", name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_afiliado", type: "BIGINT")

			column(name: "obra_social_id", type: "BIGINT")

			column(name: "ocupacion", type: "VARCHAR(255)")

			column(name: "sexo", type: "BIT")

			column(name: "telefono", type: "VARCHAR(255)")

			column(name: "tipo_documento", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-24") {
		createTable(tableName: "prescripcion") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cantidad", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "consulta_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "impresion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "nombre_comercial", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "nombre_generico", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "presentacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "secuencia", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-25") {
		createTable(tableName: "principio_activo") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "contraindicaciones", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "embarazo_lactancia", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "interacciones", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "precauciones", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "principio_activo", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-26") {
		createTable(tableName: "profesional") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "activo", type: "BIT")

			column(name: "antecedente_label_id", type: "BIGINT")

			column(name: "codigo_iva", type: "VARCHAR(255)")

			column(name: "codigo_postal", type: "VARCHAR(255)")

			column(name: "cuit", type: "VARCHAR(255)")

			column(name: "domicilio", type: "VARCHAR(255)")

			column(name: "especialidad_id", type: "BIGINT")

			column(name: "fecha_ingreso", type: "DATETIME")

			column(name: "fecha_nacimiento", type: "DATETIME")

			column(name: "image_extension", type: "VARCHAR(255)")

			column(name: "localidad_id", type: "BIGINT")

			column(name: "matricula", type: "INT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_documento", type: "BIGINT")

			column(name: "observaciones", type: "VARCHAR(255)")

			column(name: "sexo", type: "VARCHAR(255)")

			column(name: "telefono", type: "VARCHAR(255)")

			column(name: "tipo_documento", type: "VARCHAR(255)")

			column(name: "tipo_matricula", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-27") {
		createTable(tableName: "profesional_bi_image") {
			column(name: "profesional_bi_image_id", type: "BIGINT")

			column(name: "image_id", type: "BIGINT")

			column(name: "bi_image_idx", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-28") {
		createTable(tableName: "provincia") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-29") {
		createTable(tableName: "requestmap") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "config_attribute", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "url", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-30") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "authority", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "description", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-31") {
		createTable(tableName: "role_people") {
			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-32") {
		createTable(tableName: "tipo_documento") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-33") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "description", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "email", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "email_show", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "es_profesional", type: "BIT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "passwd", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "profesional_asignado_id", type: "BIGINT")

			column(defaultValue: "", name: "user_real_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-34") {
		createTable(tableName: "user_profesional") {
			column(name: "user_medicos_id", type: "BIGINT")

			column(name: "profesional_id", type: "BIGINT")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-35") {
		createTable(tableName: "vademecum") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "accion_terapeutica", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "advertencias", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "asoc2", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "composicion_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "conservacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "contra_indicacion_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "dosificacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "embarazoy_lactancia", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "farmacocinetica", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "farmacodinamia", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "farmacologia", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "grupo_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "indicaciones", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "laboratorio_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "nombre_comercial", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "presentacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "prestaciones", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "principio_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "reacciones_adversas", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValue: "", name: "sobre_dosificacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-36") {
		addPrimaryKey(columnNames: "role_id, user_id", tableName: "role_people")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-37") {
		createIndex(indexName: "name", tableName: "calendar_event_type", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-38") {
		createIndex(indexName: "paciente_id", tableName: "event", unique: "true") {
			column(name: "paciente_id")

			column(name: "start")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-39") {
		createIndex(indexName: "paciente_id_2", tableName: "event", unique: "true") {
			column(name: "paciente_id")

			column(name: "end")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-40") {
		createIndex(indexName: "FKCC0C2E97A4E98773", tableName: "historia_clinica", unique: "false") {
			column(name: "paciente_id")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-41") {
		createIndex(indexName: "dni", tableName: "paciente", unique: "true") {
			column(name: "dni")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-42") {
		createIndex(indexName: "url", tableName: "requestmap", unique: "true") {
			column(name: "url")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-43") {
		createIndex(indexName: "authority", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-44") {
		createIndex(indexName: "username", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-45") {
		addForeignKeyConstraint(baseColumnNames: "event_type_id", baseTableName: "calendar_event", baseTableSchemaName: "medfireweb", constraintName: "FK2A9BEA59A0CC175F", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "calendar_event_type", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-46") {
		addForeignKeyConstraint(baseColumnNames: "calendar_event_id", baseTableName: "calendar_reminder", baseTableSchemaName: "medfireweb", constraintName: "FK4690BC53AFAD62B", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "calendar_event", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-47") {
		addForeignKeyConstraint(baseColumnNames: "cie10_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5BEE28441", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cie10", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-48") {
		addForeignKeyConstraint(baseColumnNames: "paciente_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5A4E98773", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "paciente", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-49") {
		addForeignKeyConstraint(baseColumnNames: "profesional_id", baseTableName: "consulta", baseTableSchemaName: "medfireweb", constraintName: "FKDE2881F5D9CAC41", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "profesional", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-50") {
		addForeignKeyConstraint(baseColumnNames: "consulta_id", baseTableName: "estudio_complementario", baseTableSchemaName: "medfireweb", constraintName: "FK4D3DB281DB86C8F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "consulta", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-51") {
		addForeignKeyConstraint(baseColumnNames: "estudio_complementario_id", baseTableName: "estudio_complementario_imagen", baseTableSchemaName: "medfireweb", constraintName: "FK89829F1CCC25C78", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "estudio_complementario", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-52") {
		addForeignKeyConstraint(baseColumnNames: "paciente_id", baseTableName: "event", baseTableSchemaName: "medfireweb", constraintName: "FK5C6729AA4E98773", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "paciente", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-53") {
		addForeignKeyConstraint(baseColumnNames: "profesional_id", baseTableName: "event", baseTableSchemaName: "medfireweb", constraintName: "FK5C6729AD9CAC41", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "profesional", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-54") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "event", baseTableSchemaName: "medfireweb", constraintName: "FK5C6729A8D7F05B3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-55") {
		addForeignKeyConstraint(baseColumnNames: "localidad_id", baseTableName: "laboratorio", baseTableSchemaName: "medfireweb", constraintName: "FK67C7852C5EFC80E1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "localidad", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-56") {
		addForeignKeyConstraint(baseColumnNames: "provincia_id", baseTableName: "localidad", baseTableSchemaName: "medfireweb", constraintName: "FKB8337069D0438A61", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "provincia", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-57") {
		addForeignKeyConstraint(baseColumnNames: "antecedente_id", baseTableName: "paciente", baseTableSchemaName: "medfireweb", constraintName: "FK2CA71371FE662701", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "antecedente", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-58") {
		addForeignKeyConstraint(baseColumnNames: "localidad_id", baseTableName: "paciente", baseTableSchemaName: "medfireweb", constraintName: "FK2CA713715EFC80E1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "localidad", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-59") {
		addForeignKeyConstraint(baseColumnNames: "obra_social_id", baseTableName: "paciente", baseTableSchemaName: "medfireweb", constraintName: "FK2CA71371BA59ECD8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "obra_social", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-60") {
		addForeignKeyConstraint(baseColumnNames: "consulta_id", baseTableName: "prescripcion", baseTableSchemaName: "medfireweb", constraintName: "FK1B67E9CBDB86C8F3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "consulta", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-61") {
		addForeignKeyConstraint(baseColumnNames: "antecedente_label_id", baseTableName: "profesional", baseTableSchemaName: "medfireweb", constraintName: "FK433508CC535CC356", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "antecedente_label", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-62") {
		addForeignKeyConstraint(baseColumnNames: "especialidad_id", baseTableName: "profesional", baseTableSchemaName: "medfireweb", constraintName: "FK433508CC78A0D46E", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "especialidad_medica", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-63") {
		addForeignKeyConstraint(baseColumnNames: "localidad_id", baseTableName: "profesional", baseTableSchemaName: "medfireweb", constraintName: "FK433508CC5EFC80E1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "localidad", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-64") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "role_people", baseTableSchemaName: "medfireweb", constraintName: "FK28B75E78E85441D3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-65") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "role_people", baseTableSchemaName: "medfireweb", constraintName: "FK28B75E788D7F05B3", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-66") {
		addForeignKeyConstraint(baseColumnNames: "profesional_asignado_id", baseTableName: "user", baseTableSchemaName: "medfireweb", constraintName: "FK36EBCB5DA7DC8C", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "profesional", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-67") {
		addForeignKeyConstraint(baseColumnNames: "profesional_id", baseTableName: "user_profesional", baseTableSchemaName: "medfireweb", constraintName: "FKE635FA58D9CAC41", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "profesional", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-68") {
		addForeignKeyConstraint(baseColumnNames: "user_medicos_id", baseTableName: "user_profesional", baseTableSchemaName: "medfireweb", constraintName: "FKE635FA585DC33DC8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-69") {
		addForeignKeyConstraint(baseColumnNames: "composicion_id", baseTableName: "vademecum", baseTableSchemaName: "medfireweb", constraintName: "FK105534B71E6FEDE1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "composicion", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-70") {
		addForeignKeyConstraint(baseColumnNames: "contra_indicacion_id", baseTableName: "vademecum", baseTableSchemaName: "medfireweb", constraintName: "FK105534B7936A69B6", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "contraindicacion", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-71") {
		addForeignKeyConstraint(baseColumnNames: "grupo_id", baseTableName: "vademecum", baseTableSchemaName: "medfireweb", constraintName: "FK105534B767804142", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "grupo_terapeutico", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-72") {
		addForeignKeyConstraint(baseColumnNames: "laboratorio_id", baseTableName: "vademecum", baseTableSchemaName: "medfireweb", constraintName: "FK105534B724E6D041", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "laboratorio", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	changeSet(author: "Andrea (generated)", id: "1317413279376-73") {
		addForeignKeyConstraint(baseColumnNames: "principio_id", baseTableName: "vademecum", baseTableSchemaName: "medfireweb", constraintName: "FK105534B74DFCC011", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "principio_activo", referencedTableSchemaName: "medfireweb", referencesUniqueColumn: "false")
	}

	include file: 'added-last-updated-constraints-to-user.groovy'
}
