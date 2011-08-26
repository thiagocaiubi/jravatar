package com.jravatar;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.jravatar.exception.JravatarDownloadException;
import com.jravatar.image.DefaultImage;
import com.jravatar.rating.Rating;

public final class Jravatar {

	private static final int IMAGE_MIN_SIZE = 1;
	private static final int IMAGE_MAX_SIZE = 512;
	
	private final static String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
	private final static String SECURE_GRAVATAR_URL = "https://secure.gravatar.com/avatar/";
	
	private String gravatarUrl = GRAVATAR_URL;
	private String forceDefault;
	
	private List<String> parameters = new ArrayList<String>();
	
	public Jravatar withSecure() {
		gravatarUrl = SECURE_GRAVATAR_URL;
		return this;
	}
	
	public Jravatar forceDefault() {
		parameters.add("f=y");
		return this;
	}
	
	public Jravatar withSize(int sizeInPixels) {
		Validate.isTrue(sizeInPixels >= IMAGE_MIN_SIZE && sizeInPixels <= IMAGE_MAX_SIZE, "sizeInPixels needs to be between 1 and 512");
		parameters.add("s="+ sizeInPixels);
		return this;
	}

	public Jravatar withRating(Rating rating) {
		Validate.notNull(rating, "rating");
		parameters.add("r="+ rating.getCode());
		return this;
	}

	public Jravatar withDefaultImage(DefaultImage defaultImage) {
		Validate.notNull(defaultImage, "defaultImage");
		parameters.add("d=" + defaultImage.getCode());
		return this;
	}

	public String getUrl(String email) {
		Validate.notNull(email, "email");
		String emailHash = DigestUtils.md5Hex(email.toLowerCase().trim());
		String parameters = formatUrlParameters();
		return gravatarUrl + emailHash + ".jpg" + parameters;
	}

	public byte[] download(String email) throws JravatarDownloadException {
		InputStream stream = null;
		try {
			URL url = new URL(getUrl(email));
			stream = url.openStream();
			return IOUtils.toByteArray(stream);
		} catch (Exception e) {
			throw new JravatarDownloadException(e);
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}

	private String formatUrlParameters() {
		if (parameters.isEmpty()) {
			return "";
		}
		
		if (forceDefault != null) {
			parameters = new ArrayList<String>();
			parameters.add("f=" + forceDefault);
		}
		return "?" + StringUtils.join(parameters.iterator(), "&");
	}
}