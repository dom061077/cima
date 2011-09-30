databaseChangeLog = {

	changeSet(author: "danielmedina (generated)", id: "1317391658131-1") {
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

	changeSet(author: "danielmedina (generated)", id: "1317391658131-2") {
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

	changeSet(author: "danielmedina (generated)", id: "1317391658131-3") {
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

	changeSet(author: "danielmedina (generated)", id: "1317391658131-4") {
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

	changeSet(author: "danielmedina (generated)", id: "1317391658131-5") {
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

			column(name: "type", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-6") {
		createTable(tableName: "cie10") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "cie10", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-7") {
		createTable(tableName: "composicion") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-8") {
		createTable(tableName: "consulta") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "actitud", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "cie10_id", type: "BIGINT")

			column(name: "contenido", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(name: "estado", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "facie", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "fc", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "fecha_alta", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "fecha_consulta", type: "DATE") {
				constraints(nullable: "false")
			}

			column(name: "fr", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "habito", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "impresion", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(name: "marcha", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "paciente_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "pesoa", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "pesoh", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "profesional_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "psiqui", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "pulso", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "ta", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "taxilar", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "trectal", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "ubicacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-9") {
		createTable(tableName: "contraindicacion") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-10") {
		createTable(tableName: "especialidad_medica") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-11") {
		createTable(tableName: "estado_civil") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-12") {
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

			column(name: "pedido", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "resultado", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(name: "secuencia", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-13") {
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

	changeSet(author: "danielmedina (generated)", id: "1317391658131-14") {
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

			column(name: "estado", type: "VARCHAR(255)") {
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

			column(name: "titulo", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-15") {
		createTable(tableName: "grupo_terapeutico") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "grupo", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-16") {
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

	changeSet(author: "danielmedina (generated)", id: "1317391658131-17") {
		createTable(tableName: "historia_clinica_consulta") {
			column(name: "historia_clinica_consultas_id", type: "BIGINT")

			column(name: "consulta_id", type: "BIGINT")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-18") {
		createTable(tableName: "iva") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-19") {
		createTable(tableName: "laboratorio") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "codigo_postal", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(name: "contacto", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "direccion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "localidad_id", type: "BIGINT")

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "telefono", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-20") {
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

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "provincia_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-21") {
		createTable(tableName: "obra_social") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "codigo_postal", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "contacto", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "cuit", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "domicilio", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "habilitada", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "razon_social", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "telefono", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-22") {
		createTable(tableName: "paciente") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "antecedente_id", type: "BIGINT")

			column(name: "apellido", type: "VARCHAR(255)") {
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

			column(name: "nombre", type: "VARCHAR(255)") {
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

	changeSet(author: "danielmedina (generated)", id: "1317391658131-23") {
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

			column(name: "impresion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_comercial", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_generico", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "presentacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "secuencia", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-24") {
		createTable(tableName: "principio_activo") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "contraindicaciones", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "embarazo_lactancia", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(name: "interacciones", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(name: "precauciones", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(name: "principio_activo", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-25") {
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

			column(name: "nombre", type: "VARCHAR(255)") {
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

	changeSet(author: "danielmedina (generated)", id: "1317391658131-26") {
		createTable(tableName: "profesional_bi_image") {
			column(name: "profesional_bi_image_id", type: "BIGINT")

			column(name: "image_id", type: "BIGINT")

			column(name: "bi_image_idx", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-27") {
		createTable(tableName: "provincia") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-28") {
		createTable(tableName: "requestmap") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "config_attribute", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "url", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-29") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-30") {
		createTable(tableName: "role_people") {
			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-31") {
		createTable(tableName: "tipo_documento") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-32") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "VARCHAR(255)") {
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

			column(name: "passwd", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "profesional_asignado_id", type: "BIGINT")

			column(name: "user_real_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-33") {
		createTable(tableName: "user_profesional") {
			column(name: "user_medicos_id", type: "BIGINT")

			column(name: "profesional_id", type: "BIGINT")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-34") {
		createTable(tableName: "vademecum") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "accion_terapeutica", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "advertencias", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "asoc2", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "composicion_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "conservacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "contra_indicacion_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "dosificacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "embarazoy_lactancia", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "farmacocinetica", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "farmacodinamia", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "farmacologia", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "grupo_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "indicaciones", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "laboratorio_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "nombre_comercial", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "presentacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "prestaciones", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "principio_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "reacciones_adversas", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "sobre_dosificacion", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-35") {
		addPrimaryKey(columnNames: "role_id, user_id", tableName: "role_people")
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-36") {
		createIndex(indexName: "FKDE2881F5A4E98773", tableName: "consulta", unique: "false") {
			column(name: "paciente_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-37") {
		createIndex(indexName: "FKDE2881F5BEE28441", tableName: "consulta", unique: "false") {
			column(name: "cie10_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-38") {
		createIndex(indexName: "FKDE2881F5D9CAC41", tableName: "consulta", unique: "false") {
			column(name: "profesional_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-39") {
		createIndex(indexName: "FK4D3DB281DB86C8F3", tableName: "estudio_complementario", unique: "false") {
			column(name: "consulta_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-40") {
		createIndex(indexName: "FK89829F1CCC25C78", tableName: "estudio_complementario_imagen", unique: "false") {
			column(name: "estudio_complementario_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-41") {
		createIndex(indexName: "FK5C6729A8D7F05B3", tableName: "event", unique: "false") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-42") {
		createIndex(indexName: "FK5C6729AA4E98773", tableName: "event", unique: "false") {
			column(name: "paciente_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-43") {
		createIndex(indexName: "FK5C6729AD9CAC41", tableName: "event", unique: "false") {
			column(name: "profesional_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-44") {
		createIndex(indexName: "paciente_id", tableName: "event", unique: "true") {
			column(name: "paciente_id")

			column(name: "start")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-45") {
		createIndex(indexName: "paciente_id_2", tableName: "event", unique: "true") {
			column(name: "paciente_id")

			column(name: "end")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-46") {
		createIndex(indexName: "FKCC0C2E97A4E98773", tableName: "historia_clinica", unique: "false") {
			column(name: "paciente_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-47") {
		createIndex(indexName: "FK2EA6FE9DBD408467", tableName: "historia_clinica_consulta", unique: "false") {
			column(name: "historia_clinica_consultas_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-48") {
		createIndex(indexName: "FK2EA6FE9DDB86C8F3", tableName: "historia_clinica_consulta", unique: "false") {
			column(name: "consulta_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-49") {
		createIndex(indexName: "FK67C7852C5EFC80E1", tableName: "laboratorio", unique: "false") {
			column(name: "localidad_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-50") {
		createIndex(indexName: "FKB8337069D0438A61", tableName: "localidad", unique: "false") {
			column(name: "provincia_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-51") {
		createIndex(indexName: "FK2CA713715EFC80E1", tableName: "paciente", unique: "false") {
			column(name: "localidad_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-52") {
		createIndex(indexName: "FK2CA71371BA59ECD8", tableName: "paciente", unique: "false") {
			column(name: "obra_social_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-53") {
		createIndex(indexName: "FK2CA71371FE662701", tableName: "paciente", unique: "false") {
			column(name: "antecedente_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-54") {
		createIndex(indexName: "dni", tableName: "paciente", unique: "true") {
			column(name: "dni")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-55") {
		createIndex(indexName: "FK1B67E9CBDB86C8F3", tableName: "prescripcion", unique: "false") {
			column(name: "consulta_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-56") {
		createIndex(indexName: "FK433508CC535CC356", tableName: "profesional", unique: "false") {
			column(name: "antecedente_label_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-57") {
		createIndex(indexName: "FK433508CC5EFC80E1", tableName: "profesional", unique: "false") {
			column(name: "localidad_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-58") {
		createIndex(indexName: "FK433508CC78A0D46E", tableName: "profesional", unique: "false") {
			column(name: "especialidad_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-59") {
		createIndex(indexName: "url", tableName: "requestmap", unique: "true") {
			column(name: "url")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-60") {
		createIndex(indexName: "authority", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-61") {
		createIndex(indexName: "FK28B75E788D7F05B3", tableName: "role_people", unique: "false") {
			column(name: "user_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-62") {
		createIndex(indexName: "FK28B75E78E85441D3", tableName: "role_people", unique: "false") {
			column(name: "role_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-63") {
		createIndex(indexName: "FK36EBCB5DA7DC8C", tableName: "user", unique: "false") {
			column(name: "profesional_asignado_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-64") {
		createIndex(indexName: "username", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-65") {
		createIndex(indexName: "FKE635FA585DC33DC8", tableName: "user_profesional", unique: "false") {
			column(name: "user_medicos_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-66") {
		createIndex(indexName: "FKE635FA58D9CAC41", tableName: "user_profesional", unique: "false") {
			column(name: "profesional_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-67") {
		createIndex(indexName: "FK105534B71E6FEDE1", tableName: "vademecum", unique: "false") {
			column(name: "composicion_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-68") {
		createIndex(indexName: "FK105534B724E6D041", tableName: "vademecum", unique: "false") {
			column(name: "laboratorio_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-69") {
		createIndex(indexName: "FK105534B74DFCC011", tableName: "vademecum", unique: "false") {
			column(name: "principio_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-70") {
		createIndex(indexName: "FK105534B767804142", tableName: "vademecum", unique: "false") {
			column(name: "grupo_id")
		}
	}

	changeSet(author: "danielmedina (generated)", id: "1317391658131-71") {
		createIndex(indexName: "FK105534B7936A69B6", tableName: "vademecum", unique: "false") {
			column(name: "contra_indicacion_id")
		}
	}
}
