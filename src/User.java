/**
 * @author Lukas J. Wensby (The most awesome of them all)
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class User {
	private int userID;
	private int birthyear;
	private int gender;
	private int numTweets;
	private int numFollowing;
	private HashMap<Integer, Integer> numComments = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> numAtActions = new HashMap<Integer, Integer>();;
	private HashMap<Integer, Integer> numReTweets = new HashMap<Integer, Integer>();;
	
	/**
	 * Constructs a User object of user with specified ID from the data in the specified database.
	 */
    public User(int userID, Database database) {
    	if (!database.hasOpenConnection()) {
    		Debug.pl("! ERROR: Can't create User object when the database connection is closed.");
    		throw new IllegalArgumentException("Database object must have an open connection.");
    	}
    	
    	// We want the statement object so we can execute queries
    	Statement stat = database.getStatement();
    	
    	try {
    		ResultSet userProfileResult; // will contain the result set from the user_profile query
    		ResultSet userSNSResult; // will contain the result set from the userSNS query
    		ResultSet userActionResult; // will contain the result set from the userAction query
    		
    		// Fetch all the data
			stat.execute("SELECT * FROM user_profile WHERE UserID = " + userID + " LIMIT 1;");
			userProfileResult = stat.getResultSet();
			stat.execute("SELECT * FROM userSNS WHERE followerUserID = " + userID + ";");
			userSNSResult = stat.getResultSet();
			stat.execute("SELECT * FROM user_action WHERE userID = " + userID + ";");
			userActionResult = stat.getResultSet();
			
			// Build it, and he will come (the User, that is)
			this.userID = userProfileResult.getInt("UserID");
			this.birthyear = userProfileResult.getInt("birthYear");
			this.gender = userProfileResult.getInt("gender");
			this.numTweets = userProfileResult.getInt("tweets");
	    	initNumFollowing(userSNSResult);
	    	initActions(userActionResult);
	    	
	    	// Clear the result sets, no need for those.
	    	userProfileResult.close();
	    	userSNSResult.close();
	    	userActionResult.close();
		} catch (SQLException e) { e.printStackTrace(); }
    }
    
    private void initActions(ResultSet userActionResult) throws SQLException {
    	do {
    		int destUserID = userActionResult.getInt("destinationUserID");
    		int numComments = userActionResult.getInt("comment");
    		int numAtActions = userActionResult.getInt("atAction");
    		int numReTweets = userActionResult.getInt("reTweet");
    		this.numComments.put(destUserID, numComments);
    		this.numAtActions.put(destUserID, numAtActions);
    		this.numReTweets.put(destUserID, numReTweets);
    	} while (userActionResult.next());
	}

    
    private void initNumFollowing(ResultSet userSNSResult) throws SQLException {
    	int numFollowing = 0;
    	while (userSNSResult.next()) {
    		numFollowing++;
    	}
    	userSNSResult.first();
    	
    	this.numFollowing = numFollowing;
    }
    
    public int getBirthYear() {
    	return birthyear;
    }
    
    public int getGender() {
    	return gender;
    }
    
    public int getNumTweets() {
    	return numTweets;
    }
    
    public int getUserID() {
    	return userID;
    }
    
    public HashMap<Integer, Integer> getNumComments() {
    	return numComments;
    }
    
    public HashMap<Integer, Integer> getNumAtActions() {
    	return numAtActions;
    }
    
    public HashMap<Integer, Integer> getNumReTweets() {
    	return numReTweets;
    }
}
