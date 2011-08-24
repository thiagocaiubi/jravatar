package com.jravatar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.jravatar.exception.JravatarDownloadException;
import com.jravatar.image.DefaultImage;
import com.jravatar.rating.Rating;

public class JravatarTest {

	private Jravatar jravatar;

	@Before
	public void setup() {
		jravatar = new Jravatar();
	}

	@Test
	public void testGetImageUrlDefaults() {
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?d=404", jravatar.getUrl("iHaveAn@email.com"));
		assertEquals("http://www.gravatar.com/avatar/fa8771dec9da9299afed9ffce70c2c18.jpg?d=404", jravatar.getUrl("info@ralfebert.de"));
	}

	@Test
	public void testGetImageUrlSize() {
		String url = jravatar.withSize(100).getUrl("iHaveAn@email.com");
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?s=100&d=404", url);
	}

	@Test
	public void testGetImageUrlRating() {
		String url = jravatar.withRating(Rating.PARENTAL_GUIDANCE_SUGGESTED).getUrl("iHaveAn@email.com");
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?r=pg&d=404", url);
	}

	@Test
	public void testGetImageUrlDefaultImage() {
		String url = jravatar.withDefaultImage(DefaultImage.IDENTICON).getUrl("iHaveAn@email.com");
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?d=identicon", url);
	}

	@Test
	public void testGetImageUrlCombined() {
		String url = jravatar
			.withSize(123)
			.withRating(Rating.PARENTAL_GUIDANCE_SUGGESTED)
			.withDefaultImage(DefaultImage.IDENTICON)
			.getUrl("iHaveAn@email.com");
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?s=123&r=pg&d=identicon", url);
	}
	
	@Test
	public void testGetImageUrlSecure() {
		assertEquals("https://secure.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?d=404", jravatar.withSecure().getUrl("iHaveAn@email.com"));
	}

	@Test
	public void testDownload() {
		byte[] bytes = jravatar.download("info@ralfebert.de");
		assertTrue("content present", bytes.length>100);
	}
	
	@Test(expected=JravatarDownloadException.class)
	public void testDownlaodFail() throws Exception {
		assertNull("null for no gravatar by default", jravatar.download("doesntexist@example.com"));
	}
}