package social.media;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterHandler {
	
	private Twitter twitter;
	
	public TwitterHandler() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb = cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("2gJDILWIqFBLmxihpior6DZWM");
		cb.setOAuthConsumerSecret("ve8t0VJho2deTrmqIMXMe56Wfm8b4eLhIBM5KYDQ9NCAu8VIWb");
		cb.setOAuthAccessToken("151249874-ZiOKvzIckZh97jfgnJIdvezYLY0Tp7LomNdh3e6T");
		cb.setOAuthAccessTokenSecret("8EnyhLe7CSJdnZNNc0dJoyz0dk7edC841FxGWlSx6Cl3E");
		TwitterFactory tf = new TwitterFactory(cb.build());
		this.twitter = tf.getInstance();
	}
	
	public void searchTweets (String query) throws TwitterException {
		Query q = new Query("https://www.youtube.com/watch?v=PkkjLV0eiOw");
		q.setCount(100);
		QueryResult result = twitter.search(q);
	}
}	
