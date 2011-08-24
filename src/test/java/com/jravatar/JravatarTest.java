package com.jravatar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.jravatar.exception.JravatarDownloadException;
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
		jravatar.withSize(100);
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?s=100&d=404", jravatar.getUrl("iHaveAn@email.com"));
	}

	@Test
	public void testGetImageUrlRating() {
		jravatar.withRating(Rating.PARENTAL_GUIDANCE_SUGGESTED);
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?r=pg&d=404", jravatar.getUrl("iHaveAn@email.com"));
	}

	@Test
	public void testGetImageUrlDefaultImage() {
		jravatar.withDefaultImage(GravatarDefaultImage.IDENTICON);
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?d=identicon", jravatar.getUrl("iHaveAn@email.com"));
	}

	@Test
	public void testGetImageUrlCombined() {
		jravatar.withSize(123).withRating(Rating.PARENTAL_GUIDANCE_SUGGESTED).withDefaultImage(GravatarDefaultImage.IDENTICON);
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?s=123&r=pg&d=identicon", jravatar.getUrl("iHaveAn@email.com"));
	}
	
	@Test
	public void testGetImageUrlSecure() {
		jravatar.withSecure();
		assertEquals("https://secure.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?d=404", jravatar.getUrl("iHaveAn@email.com"));
	}

	@Test
	public void testDownload() {
		byte[] bytes = new Jravatar().download("info@ralfebert.de");
		assertTrue("content present", bytes.length>100);
	}
	
	@Test(expected=JravatarDownloadException.class)
	public void testDownlaodFail() throws Exception {
		assertNull("null for no gravatar by default", new Jravatar().download("doesntexist@example.com"));
	}
}