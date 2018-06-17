package storm;


import java.util.List;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

import social.media.TwitterBean;
import social.media.TwitterHandler;
import twitter4j.Status;

public class Topology {
	public static void main(String[] args) {
		Config conf = new Config();
		
		conf.registerSerialization(Status.class);
		conf.registerSerialization(TwitterBean.class);
		TopologyBuilder builder = new TopologyBuilder();
		TwitterHandler th = new TwitterHandler();
		List <Status> sl = th.searchTweets("feijocomacento");
		builder.setSpout("twitter-spout", new TwitterSpout(sl));
		builder.setBolt("twitter-bolt", new TwitterBolt()).shuffleGrouping("twitter-spout");

		/*Test*/
		LocalCluster local = new LocalCluster();
		local.submitTopology("analytics-sd2", conf, builder.createTopology());
		
	}
}
