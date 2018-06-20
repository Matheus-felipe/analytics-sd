package storm;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.storm.shade.org.apache.curator.framework.listen.ListenerContainer;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.event.map.MapEventBean;

import social.media.YoutubeBean;
import social.media.TwitterBean;


public class CEPBolt implements IRichBolt{
	
	private EPRuntime cepRT;
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
	
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		// Cria uma configuração
					Configuration cepConfig = new Configuration();

					// Registra os objetos que o engenho CEP vai tratar
					cepConfig.addEventType("twitter", TwitterBean.class.getName());
					cepConfig.addEventType("youtube", YoutubeBean.class.getName());

					// Configura o engine com seu config
					EPServiceProvider cep = EPServiceProviderManager.getProvider("myCEPEngine", cepConfig);

					// Registra a consulta via CEP Administrator
					EPAdministrator cepAdm = cep.getEPAdministrator();
					EPStatement cepStatement = cepAdm.createEPL("select a.tweet, b.comment from pattern [every a=twitter -> b=youtube ((a.match='positivo') or (b.match='positivo'))]");

					//Adiciona o listener para receber eventos do engenho
					cepStatement.addListener(new UpdateListener() {
						
						public void update(EventBean[] data1, EventBean[] data2) {

							try {
								
								if(data1 == null) {
									return;
								}
								
								//System.out.println("Match - data1 = " + data1.length);
							
								for (int i = 0; i < data1.length; i++) {
									
									Object o = data1[i].getUnderlying();
									
									if(o instanceof TwitterBean) {
										//Log.log(Metrics.verboseCEP, "New taxi...");
										
										TwitterBean tb = (TwitterBean)o;
										System.out.println("Esse é Twitter");
										System.out.println(tb.toString());
										
										
									} else if(o instanceof YoutubeBean) {
										YoutubeBean yb = (YoutubeBean)o;
										System.out.println("Esse é 2");
										System.out.println(yb.toString());
										
									}else if(o instanceof Map) {
										MapEventBean map = (MapEventBean) data1[i];
										Map<String, Object> events = map.getProperties();
										
										Set<String> keys = events.keySet();
										Iterator<String> iKeys = keys.iterator();
										
										while(iKeys.hasNext()) {
											String key = iKeys.next();
											Object aux = events.get(key);
											System.out.println("Match - " + key + "," + aux.toString());
										}
							
										//System.out.println(events.get("count(*)"));
										//System.out.println(events.get("QTD"));
										
									 } else {
										 System.out.println(o.toString());
									 }
									
								}
								

							} catch (Throwable e) {
								System.err.println(e.getMessage());
							}
						}
					});

					// Pega a instancia do engenho/core para associar ao Stream e injetar os dados
					 this.cepRT = cep.getEPRuntime();
		
	}

	public void execute(Tuple tuple) {
		this.cepRT.sendEvent(tuple.getValue(0)); /*Enviando evento para o Listener*/
		
	}

	

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
