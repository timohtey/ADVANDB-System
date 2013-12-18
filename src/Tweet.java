
public class Tweet {
	private String username;
	private String timestamp;
	private String location;
	private String tweet;
	private String tweet_classification;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getTweet_classification() {
		return tweet_classification;
	}

	public void setTweet_classification(String tweet_classification) {
		this.tweet_classification = tweet_classification;
	}
}
