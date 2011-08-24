package com.jravatar.rating;

public enum Rating {

	GENERAL_AUDIENCES("g"),

	PARENTAL_GUIDANCE_SUGGESTED("pg"),

	RESTRICTED("r"),

	XPLICIT("x");

	private String code;

	private Rating(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}