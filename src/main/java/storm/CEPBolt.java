package storm;

import java.util.Map;

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

import upe.CEPListener;
import upe.Produto;
import upe.ThreadEnviarEventos;

public class CEPBolt implements IRichBolt{
	
	private EPRuntime cepRT;
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
	
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		// Cria uma configuração
					Configuration cepConfig = new Configuration();

					// Registra os objetos que o engenho CEP vai tratar
					cepConfig.addEventType("produto", Produto.class.getName());

					// Configura o engine com seu config
					EPServiceProvider cep = EPServiceProviderManager.getProvider("myCEPEngine", cepConfig);

					// Registra a consulta via CEP Administrator
					EPAdministrator cepAdm = cep.getEPAdministrator();
					EPStatement cepStatement = cepAdm.createEPL("select * from produto");

					//Adiciona o listener para receber eventos do engenho
					cepStatement.addListener(new CEPListener());

					// Pega a instancia do engenho/core para associar ao Stream e injetar os dados
					 = cep.getEPRuntime();
		
	}

	public void execute(Tuple arg0) {
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
