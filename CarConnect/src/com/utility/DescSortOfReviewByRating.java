/*Author: AKSHAY PAWAR*/

package com.utility;

import java.util.Comparator;

import com.model.Review;

public class DescSortOfReviewByRating implements Comparator<Review>{

@Override
public int compare(Review r1, Review r2) {
	
	return (int) -(r1.getReview_rating()-r2.getReview_rating());
}

}