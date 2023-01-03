package automation.spotify;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Api {
	public String token = "Bearer BQA1Ol9jSzqba62OBISSceatdCgUC9aZCRYUsqvOKp6SHi5fJc_tgvNO_fUznCEyFUcvm7wd2t4fAUAoMuOpLjSdyYvRhk3ATi0cCKBTctM2IeUqZx1Im7iys7FQH2tDhGR1y-uuq73tF9tmqBMbg9VN3B6gzk8dT3FCCMMR-5kWQ4UXtjNIL-Ic1Yz-wgTYujrrESMkCm_wLobd6TGAQkRa4WD9b593Wx09AtktSPy-bXjZFxugKJFXBHM9hBHPdGAOL-jE4vk";
	public String userID="31qs7ymf3zbq46f4zv5nrzhec7mu";
	public String albumId="4aawyAB9vmqN3uQ7FjRGTy";
	public String playlistId="4Zb6mQCUzILVBiOX2Eobtr";
	public String artistId="0TnOYISbd1XYRBk9myaseg";
	public String trackId="11dFghVXANMlKmJXsNCbNl";
	public String episodeId="512ojhOuo1ktJprKbVcKyQ";
	public String showId="38bS44xjbVVZ3No3ByF1dJ";
	
//	*********************************************************************************
	//1. User-profile
	// get-user-profile:
	@Test
	public void getUserProfile() {
		Response response = given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me");

		response.prettyPrint();
		userID = response.path("id");

		System.out.println("--------------------------------------------------------");
		System.out.println("user id :" + userID);
		response.then().statusCode(200);
		
		System.out.println("getContentType :" + response.getContentType());

	}
	
	// get-particular profile:
	@Test
	public void getParticularProfile() {
		Response response = given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/userID");

		response.prettyPrint();
		String userId = response.path("id");

		System.out.println("--------------------------------------------------------");
		System.out.println("user id :" + userId);
		response.then().statusCode(200);
	}
	
//	****************************************************************************************
	//2. Playlists
	// 	Get Current User's Playlists:
	
	@Test
	public void getCurrentUsersPlaylists() {
		Response response = given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.pathParam("limit", 10)
				.pathParam("offset", 5)
				.when()
				.get("https://api.spotify.com/v1/me/playlists?limit={limit}&offset={offset}");

		response.prettyPrint();
		System.out.println("--------------------------------------------------------");
		response.then().statusCode(200);
	}
	
	// Get particular Playlist:
	@Test
	public void getParticularPlaylist() {

		Response response = given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				//.pathParam("market", "ES")
				.queryParam("market", "ES")
				.when()
				.get("https://api.spotify.com/v1/playlists/playlistId");

		response.prettyPrint();
		System.out.println("--------------------------------------------------------");
		response.then().statusCode(200);
	}

	// Get User's Playlists:
	@Test
	
	public void getUsersPlaylists() {

		Response response = given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.queryParam("limit", 10)
				.queryParam("offset", 5)
				.when()
				.get("https://api.spotify.com/v1/users/userID/playlists");

		response.prettyPrint();
		System.out.println("--------------------------------------------------------");
		response.then().statusCode(200);
	}
	
	// Create Playlist:
	@Test
	public void createPlaylists() {

		Response response = given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.body("{\"name\":\"latests Bollywoods song\",\"description\":\"New playlists bollywood\",\"public\":false}")
				.when()
				.post("https://api.spotify.com/v1/users/userID/playlists");

		response.prettyPrint();
		System.out.println("--------------------------------------------------------");
		response.then().statusCode(201);
		System.out.println("display-name"+response.path("owner"));
		 String body = response.path("owner.display_name");
		 System.out.println(body);
		 
	}

	// Add Items to Playlist
		@Test
		public void AddItemstoPlaylist() {
			String requestBody = "{\"uris\":[\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\",\"spotify:track:1301WleyT98MSxVHPZCA6M\",\"spotify:episode:episodeId\"]}";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)
					.pathParam("uris", "spotify%3Atrack%3A4iV5W9uYEdYUVa79Axb7Rh%2Cspotify%3Atrack%3A1301WleyT98MSxVHPZCA6M")
					.body(requestBody)					
					.when()
					.post("https://api.spotify.com/v1/playlists/7tuNgHzuwVQ0y2y6yRrheL/tracks?uris={uris}");
			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(201);
		}
		
	// 	Change Playlist Details
				@Test
		public void ChangePlaylistDetails() {
					String requestBody = "{\"name\":\"playlist bollywood\",\"description\":\"Updated playlist description\",\"public\":false}";
					Response response = given()
							.header("Accept", "application/json")
							.header("Content-Type", "application/json")
							.header("Authorization", token)		
//							.body("{\"name\":\"New playlist bollywood\",\"description\":\"Updated playlist description\",\"public\":false}")					
							.body(requestBody)
							.when()
							.put("https://api.spotify.com/v1/playlists/63VaE3yQKgmz5N9Ca774v8");
					response.prettyPrint();
					System.out.println("--------------------------------------------------------");
					response.then().statusCode(200);
				}
		// Get Playlist Items
		@Test
		public void GetPlaylistItems() {

			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)
					.queryParam("market", "ES")
					.queryParam("fields", "")
					.queryParam("limit", 10)
					.queryParam("offset", 5)
					.when()
					.get("https://api.spotify.com/v1/playlists/playlistId/tracks?market=ES&fields=items(added_by.id%2Ctrack(name%2Chref%2Calbum(name%2Chref)))&limit=10&offset=5");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Library
//		Get User's Saved Albums
		@Test
		public void getUsersSavedAlbums() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)				
					.queryParam("limit", 10)
					.queryParam("offset", 5)
					.queryParam("market", "ES")
					.when()
					.get("https://api.spotify.com/v1/me/albums");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
		
//		Get User's Saved Tracks
		@Test
		public void getUsersSavedTracks() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("market", "ES")
					.queryParam("limit", 10)
					.queryParam("offset", 5)				
					.when()
					.get("https://api.spotify.com/v1/me/tracks");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Get User's Saved Episodes
		@Test
		public void getUsersSavedEpisodes() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("market", "ES")
					.queryParam("limit", 10)
					.queryParam("offset", 5)				
					.when()
					.get("https://api.spotify.com/v1/me/episodes");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Get User's Saved Shows
		@Test
		public void getUsersSavedShows() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("limit", 10)
					.queryParam("offset", 5)				
					.when()
					.get("https://api.spotify.com/v1/me/shows");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
		
//		Check User's Saved Albums
		@Test
		public void checkUsersSavedAlbums() {
			String query ="382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("ids", query)								
					.when()
					.get("https://api.spotify.com/v1/me/albums/contains");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}

//		Remove Users' Saved Albums
		@Test
		public void removeUsersSavedAlbums() {
			String bodyContent= "[\"4iV5W9uYEdYUVa79Axb7Rh\", \"1301WleyT98MSxVHPZCA6M\"]";
			String albumIds= "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("ids",albumIds )								
					.body(bodyContent)
					.when()
					.delete("https://api.spotify.com/v1/me/albums");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Save Albums for Current User
		
		@Test
		public void updateAlbumsForCurrentUser() {
			String bodyContent= "[\"4iV5W9uYEdYUVa79Axb7Rh\", \"1301WleyT98MSxVHPZCA6M\"]";
			String albumIds= "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("ids",albumIds)								
					.body(bodyContent)
					.when()
					.put("https://api.spotify.com/v1/me/albums");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Save Tracks for Current User
		
		@Test
		public void SaveTracksForCurrentUser() {
			String bodyContent= "[\"4iV5W9uYEdYUVa79Axb7Rh\", \"1301WleyT98MSxVHPZCA6M\"]";
			String trackIds= "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("ids", trackIds)								
					.body(bodyContent)
					.when()
					.put("https://api.spotify.com/v1/me/tracks");
			
			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		******************************************************************
//		Follow
//		Get Followed Artists
		@Test
		public void getFollowedArtists() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("type", "artist")								
					.queryParam("after", "0I2XqVXqHScXjHhk6AYYRe")
					.queryParam("limit", 10)
					.when()
					.get("https://api.spotify.com/v1/me/following");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Check if Users Follow Playlist
		@Test
		public void checkIfUsersFollowPlaylist() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("ids", "jmperezperez,thelinmichael,wizzler")								
					.when()
					.get("https://api.spotify.com/v1/playlists/playlistId/followers/contains");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
		
//		Follow Artists or Users(Update)
		@Test
		public void followArtistsORUsers() {
			String bodyContent= "{ids:[\"74ASZWbe4lXaubB36ztrGX\", \"08td7MxkoHQkXnWAYD8d6Q\"]}";
			String ids= "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("type", "artist")
					.queryParam("ids",ids )								
					.body(bodyContent)
					.when()
					.put("https://api.spotify.com/v1/me/following");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
//		******************************************************************
		// SEARCH
		// Search for Item:
		
		@Test
		public void SearchForItem() {
			String query = "remaster%20track:Doxy%20artist:Miles%20Davis"; 
			String type = "track,artist";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)
					.queryParam("q", query)
					.queryParam("type", type)
					.queryParam("market", "ES")
					.queryParam("limit", 10)
					.queryParam("offset", 5)
					.when()
					.get("https://api.spotify.com/v1/search");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		**********************************************************************
//		Personalization
//		Get User's Top Items
		@Test
		public void getUsersTopItems() {		
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
					.queryParam("type", "artists")
					.queryParam("time_range", "medium_term")
					.queryParam("limit", 10)
					.queryParam("offset", 5)
					.when()
					.get("https://api.spotify.com/v1/me/top/artists");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Artists
//		Get Several Artists
		
		@Test
		public void getSeveralArtists() {
			String artistIds= "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
					.queryParam("ids",artistIds)
					.when()
					.get("https://api.spotify.com/v1/artists");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Get Artist
		@Test
		public void getArtist() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
//					.queryParam("id", "artistId")
					.when()
					.get("https://api.spotify.com/v1/artists/artistId");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);	
		}
		
//		Get Artist's Albums
		@Test
		public void getArtistsAlbums() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
//					.queryParam("id", "artistId")
					.queryParam("include_groups", "single,appears_on")
					.queryParam("market", "ES")
					.queryParam("limit", 10)
					.queryParam("offset", 5)
					.when()
					.get("https://api.spotify.com/v1/artists/artistId");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);	
		}
		
//		*********************************************************************
//		Albums
//		Get Several Albums
		@Test
		public void getSeveralAlbums() {
			String ids = "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
					.queryParam("ids", ids)
					.queryParam("market", "ES")			
					.when()
					.get("https://api.spotify.com/v1/albums");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);	
		}
//		Get Album
		@Test
		public void getAlbum() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
//					.queryParam("id", "albumId")
					.queryParam("market", "ES")			
					.when()
					.get("https://api.spotify.com/v1/albums/albumId");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);	
		}
			
//		Get Album Tracks
		@Test
		public void getAlbumTracks() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
//					.queryParam("id", "albumId")
					.queryParam("market", "ES")	
					.queryParam("limit", 10)
					.queryParam("offset", 5)
					.when()
					.get("https://api.spotify.com/v1/albums/albumId/tracks");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);	
		}
		
//		***********************************************************************
//		Get Several Chapters
		@Test
		public void getSeveralChapters() {
			String ids ="7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
					.queryParam("ids", ids)
					.queryParam("market", "ES")	
					.when()
					.get("https://api.spotify.com/v1/chapters");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Get a Chapter
		@Test
		public void getChapter() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
//					.queryParam("id", "showId")
					.queryParam("market", "ES")	
					.when()
					.get("https://api.spotify.com/v1/chapters/showId");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
		
//		*************************************************************************
//		Episodes
//		Get Several Episodes
		@Test
		public void getSeveralEpisodes(){
			String episodeIds = "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf";
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
					.queryParam("ids", episodeIds)
					.queryParam("market", "ES")	
					.when()
					.get("https://api.spotify.com/v1/episodes");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Get Episode
		@Test
		public void getEpisode() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
//					.queryParam("id", "episodeId")
					.queryParam("market", "ES")	
					.when()
					.get("https://api.spotify.com/v1/episodes/episodeId");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		***********************************************************************
//		Markets
//		Get Available Markets
		@Test
		public void getAvailableMarkets() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
					.when()
					.get("https://api.spotify.com/v1/markets");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}	
		
		
		
		// Search 
		@Test
		public void search() {
			String query = "remaster%20track:Doxy%20artist:Miles%20Davis";
			Response response = given()
					.header("Accept","application/json")
					.header("Content-Type","application/json")
					.header("Authorization",token)
					.pathParam("q",query)
					.when()
					.get("https://api.spotify.com/v1/search/?q={q}");
			response.prettyPrint();
			response.then().statusCode(200);
		}
}
