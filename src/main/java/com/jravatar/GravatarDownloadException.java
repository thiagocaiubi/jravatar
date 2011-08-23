package com.jravatar;

public class GravatarDownloadException extends RuntimeException {

	private static final long serialVersionUID = -1728713150704843183L;

	public GravatarDownloadException(Throwable cause) {
		super("Gravatar could not be downloaded: " + cause.getMessage(), cause);
	}
}