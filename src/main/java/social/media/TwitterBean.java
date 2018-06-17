package social.media;

public class TwitterBean {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private boolean isRetweeted;
	private int retweets;
	
	public TwitterBean(String pName, boolean pIsRetweeted, int pRetweets) {
		this.name = pName;
		this.isRetweeted = pIsRetweeted;
		this.retweets = pRetweets;
	}

	public String getName() {
		return name;
	}

	public boolean isRetweeted() {
		return isRetweeted;
	}

	public int getRetweets() {
		return retweets;
	}
}
