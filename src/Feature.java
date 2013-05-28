import java.util.Vector;

/**
 * Feature class
 * @author Lukas J. Wensby
 */

public class Feature {
	public static final int NUM_FEATURES = 11;
	
	// Feature array indices
	private static final int USER_BIRTH_YEAR		= 0;
	private static final int USER_GENDER 			= 1;
	private static final int USER_NUM_TWEETS		= 2;
	private static final int USER_NUM_FOLLOWING		= 3;
	private static final int ITEM_BIRTH_YEAR		= 4;
	private static final int ITEM_GENDER			= 5;
	private static final int ITEM_NUM_TWEETS		= 6;
	private static final int ITEM_NUM_FOLLOWING 	= 7;
	private static final int NUM_COMMENTS_BETWEEN 	= 8;
	private static final int NUM_AT_ACTION_BETWEEN	= 9;
	private static final int NUM_RETWEETS_BETWEEN	= 10;
	
	/**
	 * Empty constructor since only the static method getFeatureVector will be used.
	 */
	private Feature() {	}
	
	/**
	 * Will construct the feature vector of Integers and return it.
	 */
	public static Vector<Integer> getFeatureVector(User user, Item item) {
		// Construct the holder for the features
		Vector<Integer> featureVector = new Vector<Integer>();
		featureVector.ensureCapacity(NUM_FEATURES);
		
		// Fill it away, Scotty!
		featureVector.set(USER_BIRTH_YEAR, getBirthYear(user));
		featureVector.set(USER_GENDER, getGender(user));
		featureVector.set(USER_NUM_TWEETS, getNumTweets(user));
		featureVector.set(USER_NUM_FOLLOWING, getNumFollowing(user));
		featureVector.set(ITEM_BIRTH_YEAR, getBirthYear(item));
		featureVector.set(ITEM_GENDER, getGender(item));
		featureVector.set(ITEM_NUM_TWEETS, getNumTweets(item));
		featureVector.set(ITEM_NUM_FOLLOWING, getNumFollowing(item));
		featureVector.set(NUM_COMMENTS_BETWEEN, calcNumCommentsBetween(user, item));
		featureVector.set(NUM_AT_ACTION_BETWEEN, calcNumAtActionBetween(user, item));
		featureVector.set(NUM_RETWEETS_BETWEEN, calcNumRetweetsBetween(user, item));
		
		return featureVector;
	}
	
	private static Integer calcNumRetweetsBetween(User user, Item item) {
		return 0;
	}

	private static Integer calcNumAtActionBetween(User user, Item item) {
		return 0;
	}

	private static Integer calcNumCommentsBetween(User user, Item item) {
		return 0;
	}

	private static Integer getNumFollowing(Item item) {
		return 0;
	}

	private static Integer getNumTweets(Item item) {
		return 0;
	}

	private static Integer getGender(Item item) {
		return 0;
	}

	private static Integer getNumFollowing(User user) {
		return 0;
	}

	private static Integer getNumTweets(User user) {
		return 0;
	}

	private static Integer getGender(User user) {
		return 0;
	}

	private static Integer getBirthYear(User user) {
		return 0;
	}
	
	private static Integer getBirthYear(Item item) {
		return 0;
	}
}
