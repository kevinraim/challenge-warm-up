package com.challengeWarmUp.challenge.projections;

import java.util.Calendar;

public interface PostProjection {
	
	Long getId();
	
	String getTitle();
	
	String getImage();
	
	String getCategory();
	
	Calendar getDate();
}
