package storm;

import java.util.ArrayList;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentThread;


import social.media.YoutubeBean;



public class YoutubeBolt implements IRichBolt {
	private static final long serialVersionUID = 1L;

	private OutputCollector collector;
	private int counter = 0;
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		this.collector = arg2;
	}

	public void execute(Tuple input) {
		Comment c = (Comment)input.getValue(0);
		YoutubeBean tb = new YoutubeBean(c.getSnippet().getTextOriginal());
				
		for(String videoComment : tb.getComments()) {
			System.out.println("Novo comentário sobre o vídeo: " + videoComment);
			++counter;
			System.out.println("Quantidade de comentários: " + counter);
			System.out.println("<--------------------------------------------------------->");
		}

		//this.collector.emit(new Values(tb));

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
