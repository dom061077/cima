package com.medfire

/**
 * Request Map domain class.
 */
class Requestmap {
	static auditable = true

	String url
	String configAttribute

	static constraints = {
		url(blank: false, unique: true)
		configAttribute(blank: false)
	}
}
