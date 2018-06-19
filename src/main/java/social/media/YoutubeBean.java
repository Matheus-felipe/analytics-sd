package social.media;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.Video;

public class YoutubeBean {
	private static final long serialVersionUID = 1L;
	//private String videoName;
	//private int likes;
	//private int dislikes;
	private Video videoRating = new Video();
	private ArrayList<String> comments = new ArrayList();
	private BigInteger views;
	private BigInteger likes;
	private BigInteger dislikes;
	private BigInteger nComments;
	
	public YoutubeBean(String pComment) {/*, BigInteger pViews, BigInteger pLikes, BigInteger pDislikes, BigInteger pNComments){*/
	//this.videoName = pVideoName;
		/*this.views = pViews;
		this.likes = pLikes;
		this.dislikes = pDislikes;
		this.nComments = pNComments;
		//this.comments.add(pComment);*/
		this.comments.add(pComment);
	}
	
	public BigInteger getVideoViews(){
		return views;
	}
	
	public BigInteger getVideoLikes(){
		return likes;
	}
	
	public BigInteger getVideoDislikes(){
		return dislikes;
	}
	
	public BigInteger getVideoNComments(){
		return nComments;
	}
	
	public ArrayList <String> getComments(){
		
		return comments;
	}
	
}
