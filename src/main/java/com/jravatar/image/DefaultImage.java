package com.jravatar.image;

public enum DefaultImage {

	GRAVATAR_ICON(""),
	
	IDENTICON("identicon"),
	
	MONSTERID("monsterid"),
	
	WAVATAR("wavatar"),
	
	HTTP_404("404"),
	
	MISTERY_MAN("mm"),
	
	RETRO("retro");

	private String code;

	private DefaultImage(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}