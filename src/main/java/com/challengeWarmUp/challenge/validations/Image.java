package com.challengeWarmUp.challenge.validations;

public class Image {
	
	public static boolean imageExtension(String imageName) {
		return !imageName.contains(".png") && !imageName.contains(".jpg") && !imageName.contains(".jpeg") ? false : true;
	}
}
