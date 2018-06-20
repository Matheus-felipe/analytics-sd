package storm;

import java.util.ArrayList;
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
	 
	private String[] goodKeywords = {"i like", "liked", "love", "nice", "great", "gostei", "curti", "maravilha", "amei", "foda", "perfeito", "awesome", "omg"};
	private String[] badKeywords = {"lixo", "odiei", "hate","i hated", "garbage","bad", "worst","sucks", "don't like", "bosta", "merda", "fraco", "horrível"};
	private OutputCollector collector;
	private int counter = 0;
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		this.collector = arg2;
	}
	

	public void execute(Tuple tuple) {

		Status s = (Status)tuple.getValue(0);
		TwitterBean tb = new TwitterBean(s.getUser().getScreenName(), s.getText().toLowerCase());

		for(int i = 0; i < this.goodKeywords.length; i++) {
			if(tb.getTweet().contains(this.goodKeywords[i])) {
				tb.setMatch("positivo");
				System.out.println("FOI POSITIVO");
				break;
			}else if(tb.getTweet().contains(this.badKeywords[i])) {
				tb.setMatch("negativo");
				System.out.println("FOI NEGATIVO");
				break;
			}else {
				tb.setMatch("");
				
			}
		}
		
		System.out.println("Perfil: " + tb.getName());
		System.out.println("<--------------------------------------------------------->");

		++counter;
		System.out.println(counter);
		this.collector.emit(new Values(tb));

		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("tweet2"));

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
