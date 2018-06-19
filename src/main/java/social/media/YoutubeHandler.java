package social.media;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentListResponse;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.api.services.youtube.model.CommentThreadReplies;
import com.google.common.collect.Lists;

import auth.Auth;


public class YoutubeHandler {
	private static int counter = 0;
	private static YouTube youtube;
	
	public YoutubeHandler() throws IOException {
		List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.force-ssl");
	    Credential credential = Auth.authorize(scopes, "commentthreads");
	    youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).build();
	}
	
	
    //String videoId = "BCJHkrYQ6s4";

    public List<Comment> ReadAllComments(String videoId) throws IOException, Exception {
    	List<Comment> l = Lists.newArrayList();
    	CommentThreadListResponse videoCommentsListResponse;
    	
    	try {
    		while(true) {
    			videoCommentsListResponse = youtube.commentThreads()
    					.list("snippet")
    					.setVideoId(videoId)
    					.setTextFormat("plainText")
    					.setMaxResults(50l)
    					.execute();
    			
    			//CommentThreadListResponse commentsPage = prepareListRequest(videoId).execute();
        	 	
                //handleCommentsThreads(videoCommentsListResponse.getItems(), l);
                for (CommentThread commentThread : videoCommentsListResponse.getItems()) {
                    l.add(commentThread.getSnippet().getTopLevelComment());
                    
                 }
    			
    			String nextPageToken = videoCommentsListResponse.getNextPageToken();
                if (nextPageToken == null)
                    break;

                // Get next page of video comments threads
                videoCommentsListResponse = youtube.commentThreads().list("snippet").setPageToken(nextPageToken).execute();
    		}
    		
             
		} catch (Exception e) {
			// TODO: handle exception
		}
    
    	return l;
         
    }
    
    /*private static void handleCommentsThreads(List<CommentThread> commentThreads, List<Comment> l) {

        for (CommentThread commentThread : commentThreads) {
           l.add(commentThread.getSnippet().getTopLevelComment());

        }
    }*/
    
    /*private static YouTube.CommentThreads.List prepareListRequest(String videoId) throws Exception {

        return youtube.commentThreads()
                      .list("snippet,replies")
                      .setVideoId(videoId)
                      .setMaxResults(100L)
                      .setModerationStatus("published")
                      .setTextFormat("plainText");
    }*/
}
/*	
	private static void handleCommentsThreads(List<CommentThread> commentThreads) {

	    for (CommentThread commentThread : commentThreads) {
	        List<Comment> comments = Lists.newArrayList();
	        comments.add(commentThread.getSnippet().getTopLevelComment());

	        CommentThreadReplies replies = commentThread.getReplies();
	        if (replies != null)
	            comments.addAll(replies.getComments());

	        System.out.println("Found " + comments.size() + " comments.");

	        // Do your comments logic here
	        counter += comments.size();
	    }
	}
	*/	
    