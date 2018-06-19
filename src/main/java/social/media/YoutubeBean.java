package social.media;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.youtube.model.Comment;

public class YoutubeBean {
	private static final long serialVersionUID = 1L;
	//private String videoName;
	//private int likes;
	//private int dislikes;
	private ArrayList<String> comments = new ArrayList();
	
	public YoutubeBean(/*String pVideoName, */String pComment){
		//this.videoName = pVideoName;
		
		this.comments.add(pComment);
		
	}
	
	/*public String getVideoName(){
		return videoName;
	}*/
	
	public ArrayList <String> getComments(){
		
		return comments;
	}
	
}
