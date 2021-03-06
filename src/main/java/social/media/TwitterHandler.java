package social.media;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterHandler {
	
	private static final long serialVersionUID = 1L;
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

	public List <Status> searchTweets (String query) {
		Query q = new Query(query);
		q.setCount(100);
		QueryResult result;
		List <Status> s = new ArrayList<Status>();

		try {
			result = this.twitter.search(q);
			
			while(true) {	
				for(Status status : result.getTweets()) {
					if(!status.isRetweet()) {
						s.add(status);
					}
				}
				if(result.hasNext()) {//there is more pages to load
					q = result.nextQuery();
					result = twitter.search(q);
				}else {
					break;
				}
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return s;
	}
}	
