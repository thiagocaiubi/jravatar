package com.jravatar.exception;

public class JravatarDownloadException extends RuntimeException {

	private static final long serialVersionUID = -1728713150704843183L;

	public JravatarDownloadException(Throwable cause) {
		super("Gravatar could not be downloaded: " + cause.getMessage(), cause);
	}
}