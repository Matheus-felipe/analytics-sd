package storm;


import java.io.IOException;
import java.util.List;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

import com.google.api.services.youtube.model.Comment;

import social.media.TwitterBean;
import social.media.TwitterHandler;
import social.media.YoutubeBean;
import social.media.YoutubeHandler;
import twitter4j.Status;

public class Topology {
	public static void main(String[] args) throws Exception {
		Config conf = new Config();
		TopologyBuilder builder = new TopologyBuilder();
		conf.registerSerialization(Status.class);
		conf.registerSerialization(TwitterBean.class);
		conf.registerSerialization(Comment.class);
		conf.registerSerialization(YoutubeBean.class);
		
		TwitterHandler th = new TwitterHandler();
		YoutubeHandler yh = new YoutubeHandler();
		List <Status> sl = th.searchTweets("feijocomacento");
		List <Comment> cl = yh.ReadAllComments("BCJHkrYQ6s4"); 
		builder.setSpout("twitter-spout", new TwitterSpout(sl));
		builder.setBolt("twitter-bolt", new TwitterBolt()).shuffleGrouping("twitter-spout");
		builder.setSpout("youtube-spout", new YoutubeSpout(cl));
		builder.setBolt("youtube-bolt", new YoutubeBolt()).shuffleGrouping("youtube-spout");

		/*Test*/
		LocalCluster local = new LocalCluster();
		local.submitTopology("analytics-sd2", conf, builder.createTopology());
		
	}
}
