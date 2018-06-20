package storm;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.Video;

import social.media.YoutubeBean;



public class YoutubeBolt implements IRichBolt {
	private static final long serialVersionUID = 1L;
	private String[] goodKeywords = {"like", "love", "nice", "great", "gostei", "curti", "maravilha", "amei", "foda", "perfeito", "awesome", "omg"};
	private String[] badKeywords = {"lixo", "odiei", "hate", "garbage","bad", "worst","sucks", "don't like", "bosta", "merda", "fraco", "horrível"};
	private OutputCollector collector;
	private int counter = 0;
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		this.collector = arg2;
	}	

	public void execute(Tuple input) {
		Comment c = (Comment)input.getValue(0);
		//Video v = (Video)input.getValue(0);
		String filteredComment = "";
		/*System.out.println("Quantidade de comentários do vídeo: " + v.getStatistics().getCommentCount());
		System.out.println("Quantidade de likes do vídeo: " + v.getStatistics().getLikeCount());
		System.out.println("Quantidade de dislikes do vídeo: " + v.getStatistics().getDislikeCount());
		System.out.println("Quantidade de visualizações do vídeo: " + v.getStatistics().getViewCount());
		 */

		// Filtrando caracteres especiais
		filteredComment = Normalizer
				.normalize(c.getSnippet().getTextOriginal(), Normalizer.Form.NFD)
				.replaceAll("[^a-zA-Z0-9\\s+]", "");
		
		// lowercasing video comment
		filteredComment.toLowerCase();
		
		YoutubeBean yb = new YoutubeBean(filteredComment);

		for(int i = 0; i < this.goodKeywords.length; i++) {
			if(filteredComment.contains(this.goodKeywords[i])) {
				yb.setMatch("positivo");
				System.out.println("FOI POSITIVOAAAAAAAAAAAAAAAAAA");	
				break;
			}else if(filteredComment.contains(this.badKeywords[i])) {
				yb.setMatch("negativo");
				System.out.println("FOI NEGATIVOAIJNFRIJFRIFS");
				break;
			}else {
				yb.setMatch("");
			}
		}
															/*, v.getStatistics().getViewCount(),
															  v.getStatistics().getLikeCount(),
															  v.getStatistics().getDislikeCount(),
															  v.getStatistics().getCommentCount());*/
		for(String videoComment : yb.getComments()) {
			System.out.println("\n<--------------- ----YOUTUBE------------------------------>");
			System.out.println("Novo comentário sobre o vídeo: " + videoComment);
			System.out.println("<--------------------------------------------------------->");
		}
		/*System.out.println("Quantidade de comentários do vídeo: " + yb.getVideoNComments());
			System.out.println("Quantidade de likes do vídeo: " + yb.getVideoLikes());
			System.out.println("Quantidade de dislikes do vídeo: " + yb.getVideoDislikes());
			System.out.println("Quantidade de visualizações do vídeo: " + yb.getVideoViews());

			System.out.println("<--------------------------------------------------------->");*/
		//}

		this.collector.emit(new Values(yb));

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("youtube2"));

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
