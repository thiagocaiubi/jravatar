package com.jravatar.exception;

public class DownloadException extends RuntimeException {

	private static final long serialVersionUID = -1728713150704843183L;

	public DownloadException(Throwable cause) {
		super("Gravatar could not be downloaded: " + cause.getMessage(), cause);
	}
}