package social.media;

public class TwitterBean {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String tweet;
	private String match;
	
	public TwitterBean(String pName,String pTweet) {
		this.name = pName;
		this.tweet = pTweet;
	}

	public String getName() {
		return name;
	}
	
	public String getTweet() {
		return this.tweet;
	}
	
	public void setMatch(String pMatch) {
		this.match = pMatch;
	}
	
	public String getMatch() {
		return this.match;
	} 
}
