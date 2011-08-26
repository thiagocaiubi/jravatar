package com.jravatar.image;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ImageSizeRangeTest {

	@Test
	public void testEnsureSizeIsInRange() throws Exception {
		ImageSizeRange range = new ImageSizeRange();
		assertEquals(1, range.ensureSizeIsInRange(1));
		assertEquals(256, range.ensureSizeIsInRange(256));
		assertEquals(512, range.ensureSizeIsInRange(512));
	}
	
	@Test
	public void testReturnMinSizeWhenSizeIsLessThanMin() throws Exception {
		ImageSizeRange range = new ImageSizeRange();
		assertEquals(1, range.ensureSizeIsInRange(0));
	}
	
	@Test
	public void testReturnMaxSizeWhenSizeIsGreaterThanMax() throws Exception {
		ImageSizeRange range = new ImageSizeRange();
		assertEquals(512, range.ensureSizeIsInRange(513));
	}
}