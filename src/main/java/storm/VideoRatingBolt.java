package storm;

import java.text.Normalizer;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import com.google.api.services.youtube.model.Comment;

import social.media.YoutubeBean;

public class VideoRatingBolt implements IRichBolt {
	private static final long serialVersionUID = 1L;

	private OutputCollector collector;
	private int counter = 0;
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		this.collector = arg2;
	}

	public void execute(Tuple input) {
		String r = (String)input.getValue(0);
		
		
		System.out.println("RATING PAPAI " + r);
		++counter;
		/*System.out.println("Quantidade de comentários: " + counter);
		System.out.println("<--------------------------------------------------------->");*/
		

		//this.collector.emit(new Values(yb));

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
