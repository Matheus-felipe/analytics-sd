package storm;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import social.media.TwitterBean;
import twitter4j.Status;

public class TwitterBolt implements IRichBolt {

	private static final long serialVersionUID = 1L;

	
	private OutputCollector collector;
	private int counter = 0;
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		this.collector = arg2;
	}
	
	public void execute(Tuple tuple) {
		Status s = (Status)tuple.getValue(0);
		TwitterBean tb = new TwitterBean(s.getUser().getScreenName(), s.isRetweet(), s.getRetweetCount());
		System.out.println("Perfil: " + tb.getName());
		if(tb.isRetweeted()) {
			System.out.println("Retweets: " + tb.getRetweets());
		}
		System.out.println("<--------------------------------------------------------->");
		
		++counter;
		System.out.println(counter);
		//this.collector.emit(new Values(tb));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	//	declarer.declare(new Fields("tweet2"));
		
	}
	
	public void cleanup(Tuple input) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
	
}
