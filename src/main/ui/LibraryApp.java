package ui;

import model.MusicList;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.JsonReader2;
import persistence.JsonWriter2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LibraryApp {

    Scanner userInput = new Scanner(System.in);
    Scanner userScreenNewName = new Scanner(System.in);
    Scanner userScreenNewSongName = new Scanner(System.in);
    Scanner userScreenNewDate = new Scanner(System.in);
    Scanner userScreenNewArtist = new Scanner(System.in);
    Scanner userScreenNewInfo = new Scanner(System.in);
    Scanner userScreenFavSongAdd = new Scanner(System.in);
    Scanner userScreenFavSongRemove = new Scanner(System.in);
    Boolean stayOnMenu = true;
    MusicList musicList = new MusicList("New Playlist");
    MusicList favList = new MusicList("Favourite List");

    private static final String JSON_STORE = "./data/musiclist.json";
    private static final String JSON_STORE2 = "./data/favmusiclist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonWriter2 jsonWriter2;
    private JsonReader2 jsonReader2;


    //EFFECTS: Constructs a library
    public LibraryApp() {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter2 = new JsonWriter2(JSON_STORE2);
        jsonReader2 = new JsonReader2(JSON_STORE2);

        while (stayOnMenu) {

            theMainMenu();

            String userChoice = userInput.nextLine().toLowerCase();

            if (userChoice.equals("a")) {

                renameMusicListMenu();

            } else if (userChoice.equals("b")) {

                addSongMenu();

            } else if (userChoice.equals("c")) {

                removeSongMenu();

            } else if (userChoice.equals("d")) {

                getSongsInfoMenu();

            } else if (userChoice.equals("e")) {

                shuffleSongsMenu();

            } else if (userChoice.equals("f")) {

                addSongToFavListMenu();

            } else if (userChoice.equals("g")) {

                removeSongFromFavListMenu();

            } else if (userChoice.equals("q")) {

                quitApp();

            } else if (userChoice.equals("s")) {

                saveMusicList();
                saveFavMusicList();

            } else if (userChoice.equals("l")) {

                loadMusicList();
                loadFavMusicList();

            } else {
                invalidKeyMain();
            }
        }
    }


    //EFFECTS: Prints out a screen for the main menu of the program
    public void theMainMenu() {

        System.out.println("Welcome to " + musicList.getName() + "!");
        System.out.println("The songs in the list are: " + musicList.getSongsNames());
        System.out.println("Your favourite songs: " + favList.getSongsNames());

        System.out.println("a -> rename the playlist");

        System.out.println("b -> add a song to the playlist!");

        System.out.println("c -> remove a song from the playlist!");

        System.out.println("d -> to learn more about a song!");

        System.out.println("e -> to shuffle the list!");

        System.out.println("f -> add a song from the main list to the favourite list!");

        System.out.println("g -> remove a song from the favourite list!");

        System.out.println("s -> save the playlist!");

        System.out.println("l -> load the music list!");

        System.out.println("q -> quit the application!");
    }


    //MODIFIES: This
    //EFFECTS: Renames the playlist as well as prints out a menu for it
    public void renameMusicListMenu() {

        System.out.println("What would you like to name the music list?");
        String newName = userScreenNewName.nextLine();
        musicList.setName(newName);
        System.out.println("The music list has been renamed!");
        System.out.println('\n');
    }

    //MODIFIES: This
    //EFFECTS: Constructs a song menu after adding a new song
    public void addSongMenu() {
        System.out.println("What song would you like to add to the list?");
        System.out.println("Enter the name of the song, the date, the artist, and info you wish to add.");
        String newSongName = userScreenNewSongName.nextLine();
        String newSongDate = userScreenNewDate.nextLine();
        String newSongArtist = userScreenNewArtist.nextLine();
        String newSongInfo = userScreenNewInfo.nextLine();
        Song newSong = new Song(newSongName, newSongDate, newSongArtist, newSongInfo);
        musicList.addSong(newSong);
        System.out.println("Song has been added!");
        System.out.println('\n');
    }

    //EFFECTS: Prints out a menu for hitting an invalid key
    public void invalidKeyMain() {
        System.out.println("Sorry that is not a valid option!");
        System.out.println('\n');
    }

    //MODIFIES: This
    //EFFECTS: Prints out a screen for removing a song and removes a song from the list
    public void removeSongMenu() {
        System.out.println("Please type in the name of the song you would like removed");
        Scanner userRemoveSongChoice = new Scanner(System.in);
        String songBeingRemoved = userRemoveSongChoice.nextLine();
        musicList.removeSong(songBeingRemoved);
        System.out.println("The song has been removed");
        System.out.println('\n');
    }

    //EFFECTS: Outputs a screen that shows the information about a song
    public void getSongsInfoMenu() {
        System.out.println("Which song would you like to know more about?");
        Scanner userGetInfoAboutASong = new Scanner(System.in);
        String getInfo = userGetInfoAboutASong.nextLine();
        System.out.println("Here is the information about the song");
        System.out.println(musicList.getSongsInfo(getInfo));
        System.out.println('\n');
    }

    //MODIFIES: This
    //EFFECTS: Outputs a screen after shuffling the song list
    public void shuffleSongsMenu() {
        musicList.shuffleSongs();
        System.out.println("The list has been shuffled!");
        System.out.println('\n');

    }

    //MODIFIES: This
    //EFFECTS: Adds a song to the favourite list as well as print out a screen for it
    public void addSongToFavListMenu() {
        System.out.println("Which song from the main playlist would you like to add to the favourite list?");
        String favSong = userScreenFavSongAdd.nextLine();
        for (Song s : musicList.getSongs()) {
            if (s.getSongName().equals(favSong)) {
                favList.addSong(s);
                //toString
            }
        }
        System.out.println(favList);
        System.out.println('\n');
    }

    //MODIFIES: This
    //EFFECTS: Removes a song from the favourite list and prints out a screen for it
    public void removeSongFromFavListMenu() {
        System.out.println("Which song would you like to remove from the favourite list?");
        String removeFavSong = userScreenFavSongRemove.nextLine();
        favList.removeSong(removeFavSong);
        System.out.println('\n');
    }

    //MODIFIES: This
    //EFFECTS: Ends the while loop and prints out a screen that say "See you next time!"
    public void quitApp() {
        System.out.println("See you next time!");
        stayOnMenu = false;
    }

    private void saveMusicList() {
        try {
            jsonWriter.open();
            jsonWriter.write(musicList);
            jsonWriter.close();
            System.out.println("Saved " + musicList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadMusicList() {
        try {
            musicList = jsonReader.read();
            System.out.println("Loaded " + musicList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void saveFavMusicList() {
        try {
            jsonWriter2.openFav();
            jsonWriter2.writeFav(favList);
            jsonWriter2.closeFav();
            System.out.println("Saved " + favList.getName() + " to " + JSON_STORE2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE2);
        }
    }

    private void loadFavMusicList() {
        try {
            favList = jsonReader2.readFav();
            System.out.println("Loaded " + favList.getName() + " from " + JSON_STORE2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE2);
        }
    }
}
