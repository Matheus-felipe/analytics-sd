package storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class Topology {
	public static void main(String[] args) {
		Config conf = new Config();
		
		TopologyBuilder builder = new TopologyBuilder();
		
		builder.setSpout("twitterSpout", new TwitterSpout());
		
		LocalCluster local = new LocalCluster();
		local.submitTopology("analytics-sd", conf, builder.createTopology());
		
	}
}
