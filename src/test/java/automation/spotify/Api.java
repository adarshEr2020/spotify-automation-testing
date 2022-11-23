package automation.spotify;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Api {
	public String token = "Bearer BQDboVcx-qxqXs0lRiVHizJXahLllcDMpfcar9x_lDTpj-gVNHjqiMolQlCxKSy1vkQx2ZvYUgZlnpbhP2wOMPxV9RSRyr69DFgd3MlH1ys9QwlRkjEA_l7cUI7o-xLokjbscDskPt2Ggt22upxf4yEJpvQn_dzbkpf-57m456FsphJPbKLFAKQi297ySOyeXzCH4_HUwfu87tj6u_n7dZb4mRxmUvCysvm_DNA9NMI3owtzia3_LZsw747nL-GfT29vabOxbmZ6";
	public String userID = "31qs7ymf3zbq46f4zv5nrzhec7mu";
	
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
		String userId = response.path("id");

		System.out.println("--------------------------------------------------------");
		System.out.println("user id :" + userId);
		response.then().statusCode(200);

	}
	
	// get-particular profile:
	@Test
	public void getParticularProfile() {
		Response response = given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/31qs7ymf3zbq46f4zv5nrzhec7mu");

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
				.get("https://api.spotify.com/v1/playlists/4Zb6mQCUzILVBiOX2Eobtr");

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
				.get("https://api.spotify.com/v1/users/31qs7ymf3zbq46f4zv5nrzhec7mu/playlists");

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
				.post("https://api.spotify.com/v1/users/31qs7ymf3zbq46f4zv5nrzhec7mu/playlists");

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
			String requestBody = "{\"uris\":[\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\",\"spotify:track:1301WleyT98MSxVHPZCA6M\",\"spotify:episode:512ojhOuo1ktJprKbVcKyQ\"]}";
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
					.get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/tracks?market=ES&fields=items(added_by.id%2Ctrack(name%2Chref%2Calbum(name%2Chref)))&limit=10&offset=5");

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
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("ids", "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc")								
					.when()
					.get("https://api.spotify.com/v1/me/albums/contains");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}

//		Remove Users' Saved Albums
		@Test
		public void removeUsersSavedAlbums() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("ids", "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc")								
					.body("[\"4iV5W9uYEdYUVa79Axb7Rh\", \"1301WleyT98MSxVHPZCA6M\"]")
					.when()
					.delete("https://api.spotify.com/v1/me/albums");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Save Albums for Current User
		
		@Test
		public void updateAlbumsForCurrentUser() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("ids", "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc")								
					.body("[\"4iV5W9uYEdYUVa79Axb7Rh\", \"1301WleyT98MSxVHPZCA6M\"]")
					.when()
					.put("https://api.spotify.com/v1/me/albums");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
//		Save Tracks for Current User
		
		@Test
		public void SaveTracksForCurrentUser() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("ids", "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B")								
					.body("[\"4iV5W9uYEdYUVa79Axb7Rh\", \"1301WleyT98MSxVHPZCA6M\"]")
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
					.get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers/contains");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
		
//		Follow Artists or Users(Update)
		@Test
		public void followArtistsORUsers() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)	
					.queryParam("type", "artist")
					.queryParam("ids", "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6")								
					.body("{ids:[\"74ASZWbe4lXaubB36ztrGX\", \"08td7MxkoHQkXnWAYD8d6Q\"]}")
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
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
					.queryParam("ids", "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6")
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
//					.queryParam("id", "0TnOYISbd1XYRBk9myaseg")
					.when()
					.get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");

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
//					.queryParam("id", "0TnOYISbd1XYRBk9myaseg")
					.queryParam("include_groups", "single,appears_on")
					.queryParam("market", "ES")
					.queryParam("limit", 10)
					.queryParam("offset", 5)
					.when()
					.get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);	
		}
		
//		*********************************************************************
//		Albums
//		Get Several Albums
		@Test
		public void getSeveralAlbums() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
					.queryParam("ids", "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc")
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
//					.queryParam("id", "4aawyAB9vmqN3uQ7FjRGTy")
					.queryParam("market", "ES")			
					.when()
					.get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");

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
//					.queryParam("id", "4aawyAB9vmqN3uQ7FjRGTy")
					.queryParam("market", "ES")	
					.queryParam("limit", 10)
					.queryParam("offset", 5)
					.when()
					.get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy/tracks");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);	
		}
		
//		***********************************************************************
//		Get Several Chapters
		@Test
		public void getSeveralChapters() {
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
					.queryParam("ids", "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B")
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
//					.queryParam("id", "38bS44xjbVVZ3No3ByF1dJ")
					.queryParam("market", "ES")	
					.when()
					.get("https://api.spotify.com/v1/chapters/38bS44xjbVVZ3No3ByF1dJ");

			response.prettyPrint();
			System.out.println("--------------------------------------------------------");
			response.then().statusCode(200);
		}
		
		
//		*************************************************************************
//		Episodes
//		Get Several Episodes
		@Test
		public void getSeveralEpisodes()
		{
			Response response = given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token)					
					.queryParam("ids", "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf")
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
//					.queryParam("id", "512ojhOuo1ktJprKbVcKyQ")
					.queryParam("market", "ES")	
					.when()
					.get("https://api.spotify.com/v1/episodes/512ojhOuo1ktJprKbVcKyQ");

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
			Response response = given()
					.header("Accept","application/json")
					.header("Content-Type","application/json")
					.header("Authorization",token)
					.pathParam("q","remaster%20track:Doxy%20artist:Miles%20Davis")
					.when()
					.get("https://api.spotify.com/v1/search/?q={q}");
			response.prettyPrint();
		}
}
