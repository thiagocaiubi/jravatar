package com.jravatar.image;

public class ImageSizeRange {

	private static final int IMAGE_MIN_SIZE = 1;
	private static final int IMAGE_MAX_SIZE = 512;
	
	public int ensureSizeIsInRange(int size) {
		if (IMAGE_MIN_SIZE < size && IMAGE_MAX_SIZE > size) {
			return size;
		}
		if (IMAGE_MIN_SIZE >= size) {
			return IMAGE_MIN_SIZE;
		}
		if (IMAGE_MAX_SIZE <= size) {
			return IMAGE_MAX_SIZE;
		}
		return 0;
	}
}