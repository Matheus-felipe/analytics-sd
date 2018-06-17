package storm;

import java.util.List;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import twitter4j.Status;

public class TwitterSpout implements IRichSpout {


	private static final long serialVersionUID = 1L;
	private int index = 0;
	List <Status> sl;

	private SpoutOutputCollector collector;
	

	public TwitterSpout(List <Status> pSl) {
		this.sl = pSl;
	}

	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		this.collector = arg2;

	}

	/*Storm chama constantemente*/
	public void nextTuple() {
		if(index == this.sl.size()) {
			System.out.println("Acabou os tweets");
		}else {
			this.collector.emit(new Values(sl.get(index++)));
		}
	}

	/*Campos que o spout envia*/
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("tweet"));

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
