package storm;

import java.util.List;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;

import social.media.TwitterHandler;
import twitter4j.Status;
import twitter4j.Twitter;

public class TwitterSpout implements IRichSpout {
	

	private static final long serialVersionUID = 1L;

	
	private SpoutOutputCollector collector;
	private TwitterHandler t = new TwitterHandler();
	
	
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		this.collector = arg2;
		
	}
	
	/*Storm chama constantemente*/
	public void nextTuple() {
		List <Status> sl = this.t.searchTweets("brasileirinhas");
		
		for (Status s : sl) {
			
		}
	}

	public void ack(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	public void activate() {
		// TODO Auto-generated method stub
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	public void fail(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
