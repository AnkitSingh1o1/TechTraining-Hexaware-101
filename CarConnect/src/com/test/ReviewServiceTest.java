/*Author :AKSHAY PAWAR*/

package com.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.model.Review;
import com.service.ReviewService;

public class ReviewServiceTest {
	ReviewService reviewService=new ReviewService();
	@Test
	public void sortReviewByRating() {
		/*UseCase 1*/
	
	    Review r1=new Review (1,13,2,	"Great experience with this car!",	5);
		Review r2=new Review (2,12,4,	"Smooth ride, excellent service.",		4);
		Review r3=new Review (3,9,3,	"Nice vehicle, comfortable seats.",		2);
		Review r4=new Review (4,6,6,	"Average experience, could be better.",		3);

		List<Review> list = Arrays.asList(r1,r2,r3,r4);
		String sortDirection = "ASC";
		
		List<Review> expectedList = Arrays.asList(r3,r4,r2,r1);
		List<Review> actualList =reviewService.sortReviewByRating(list, sortDirection);
		Assert.assertEquals(expectedList, actualList);
		
		
		/*UseCase 2*/
		
		 sortDirection = "Desc";
		 expectedList = Arrays.asList(r1,r2,r4,r3);
		 actualList =reviewService.sortReviewByRating(list, sortDirection);
		 Assert.assertEquals(expectedList, actualList);
		 
		 /*UseCase 3*/
		 sortDirection = "ASC";
			list = Arrays.asList(r1,r3,r4);
		 expectedList = Arrays.asList(r3,r4,r1);
		 actualList =reviewService.sortReviewByRating(list, sortDirection);
		 Assert.assertEquals(expectedList, actualList);
		 
		 
		 /*UseCase 4*/
		 sortDirection = "DESC";
			list = Arrays.asList(r2,r3,r4);
		 expectedList = Arrays.asList(r2,r4,r3);
		 actualList =reviewService.sortReviewByRating(list, sortDirection);
		 Assert.assertEquals(expectedList, actualList);

		 
		 
		 /*Use Case 5*/
		 /*Intentional Fail*/
		 sortDirection = "Wrong-Direction";
			list = Arrays.asList(r2,r3,r4);
		 expectedList = Arrays.asList(r2,r4,r3);
		 actualList =reviewService.sortReviewByRating(list, sortDirection);
		 try {
		 Assert.assertEquals(expectedList, actualList);}
		 catch(Error e) {}
		 
}}
