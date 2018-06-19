package storm;

import java.util.List;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentThread;

public class YoutubeSpout implements IRichSpout {
	private static final long serialVersionUID = 1L;
	private int index = 0;
	private static List <Comment> cl;

	private SpoutOutputCollector collector;
	

	public YoutubeSpout(List <Comment> pCl) {
		this.cl = pCl;
	}

	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		this.collector = arg2;
	}

	public void nextTuple() {
		try {
			if(index == this.cl.size()) {
				System.out.println("No more comments.");
			}else {
				this.collector.emit(new Values(cl.get(index++)));
			}
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*Campos que o spout envia*/
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("comment"));

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

	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
