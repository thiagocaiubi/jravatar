package com.jravatar;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.jravatar.exception.DownloadException;
import com.jravatar.image.DefaultImage;
import com.jravatar.image.ImageSizeRange;
import com.jravatar.rating.Rating;

public final class Jravatar {

	private final static String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
	private final static String SECURE_GRAVATAR_URL = "https://secure.gravatar.com/avatar/";
	
	private String gravatarUrl = GRAVATAR_URL;
	
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
		parameters.add("s="+ new ImageSizeRange().ensureSizeIsInRange(sizeInPixels));
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

	public byte[] download(String email) throws DownloadException {
		InputStream stream = null;
		try {
			URL url = new URL(getUrl(email));
			stream = url.openStream();
			return IOUtils.toByteArray(stream);
		} catch (Exception e) {
			throw new DownloadException(e);
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}

	private String formatUrlParameters() {
		if (parameters.isEmpty()) {
			return "";
		}
		return "?" + StringUtils.join(parameters.iterator(), "&");
	}
}