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
import com.jravatar.rating.Rating;

public final class Jravatar {

	private final static int IMAGE_DEFAULT_SIZE = 80;
	private static final int IMAGE_MIN_SIZE = 1;
	private static final int IMAGE_MAX_SIZE = 512;
	
	private final static String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
	private final static String SECURE_GRAVATAR_URL = "https://secure.gravatar.com/avatar/";
	
	
	private static final Rating DEFAULT_RATING = Rating.GENERAL_AUDIENCES;
	private static final GravatarDefaultImage DEFAULT_IMAGE = GravatarDefaultImage.HTTP_404;

	private int size = IMAGE_DEFAULT_SIZE;
	private Rating rating = DEFAULT_RATING;
	private GravatarDefaultImage defaultImage = DEFAULT_IMAGE;
	private String gravatarUrl = GRAVATAR_URL;

	public Jravatar withSecure() {
		gravatarUrl = SECURE_GRAVATAR_URL;
		return this;
	}
	
	public Jravatar withSize(int sizeInPixels) {
		Validate.isTrue(sizeInPixels >= IMAGE_MIN_SIZE && sizeInPixels <= IMAGE_MAX_SIZE, "sizeInPixels needs to be between 1 and 512");
		this.size = sizeInPixels;
		return this;
	}

	public Jravatar withRating(Rating rating) {
		Validate.notNull(rating, "rating");
		this.rating = rating;
		return this;
	}

	public Jravatar withDefaultImage(GravatarDefaultImage defaultImage) {
		Validate.notNull(defaultImage, "defaultImage");
		this.defaultImage = defaultImage;
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
		List<String> params = new ArrayList<String>();

		if (size != IMAGE_DEFAULT_SIZE)
			params.add("s=" + size);
		if (rating != DEFAULT_RATING)
			params.add("r=" + rating.getCode());
		if (defaultImage != GravatarDefaultImage.GRAVATAR_ICON)
			params.add("d=" + defaultImage.getCode());

		if (params.isEmpty()) {
			return "";
		}
		
		return "?" + StringUtils.join(params.iterator(), "&");
	}
}
