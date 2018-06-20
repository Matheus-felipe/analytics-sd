package social.media;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentListResponse;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.api.services.youtube.model.CommentThreadReplies;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoGetRatingResponse;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.services.youtube.model.VideoRating;
import com.google.common.collect.Lists;

import auth.Auth;


public class YoutubeHandler {
	private static int counter = 0;
	private static YouTube youtube;

	public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	
	public static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	public YoutubeHandler() throws IOException {
		//List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.force-ssl");
		//Credential credential = Auth.authorize(scopes, "commentthreads");
		youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
			public void initialize(HttpRequest request) throws IOException {
				}
			}).build();
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
						.setKey("AIzaSyAdxB0oY6sVcjW32T1-EEVPkkpPCnhivss")
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
				videoCommentsListResponse = youtube.commentThreads().list("snippet").setPageToken(nextPageToken).setKey("AIzaSyAdxB0oY6sVcjW32T1-EEVPkkpPCnhivss").execute();
			}


		} catch (Exception e) {
			// TODO: handle exception
		}

		return l;

	}

	public Video GetVideoRating(String videoId) {
		Video vr = new Video();

		try {
			//VideoGetRatingResponse videoRatingListResponse;

			YouTube.Videos.List listVideosRequest = youtube.videos().list("statistics");
			listVideosRequest.setId(videoId); // add list of video IDs here
			//listVideosRequest.setKey(apiKey);
			VideoListResponse listResponse = listVideosRequest.execute();

			vr = listResponse.getItems().get(0);

			/*BigInteger viewCount = video.getStatistics().getViewCount();
			BigInteger Likes = video.getStatistics().getLikeCount();
			BigInteger DisLikes = video.getStatistics().getDislikeCount();
			BigInteger Comments = video.getStatistics().getCommentCount();
			System.out.println("[View Count] " + viewCount);
			System.out.println("[Likes] " + Likes);
			System.out.println("[Dislikes] " + DisLikes);
			System.out.println("[Comments] " + Comments);*/


		}catch (Exception e) {
			// TODO: handle exception
		}	
		return vr;
	} 
	
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
