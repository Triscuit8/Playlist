package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


//EFFECTS: Constructs a music library
class MusicLibraryTest {
    private MusicList musicList;
    private Song song1;


    @BeforeEach
    public void setup() {
        this.musicList = new MusicList("Christine's list");
        this.song1 = new Song("Happy Birthday", "2000", "Jay-Z", "Listen on birthday!");
        this.musicList.addSong(song1);
    }

    @Test
    public void TestListConstructor() {
        assertEquals("Christine's list", musicList.getName());
        assertEquals(1, musicList.getSongs().size());
    }

    @Test
    public void TestAddSong() {
        Song song2 = new Song("O'Canada", "1990", "Justin Bieber", "Cool Song");
        Song song3 = new Song("What does the Fox Say?", "203", "Pewdiepie", "Very Nice!");
        this.musicList.addSong(song2);
        this.musicList.addSong(song3);
        assertEquals(3, musicList.getSongs().size());
    }

    @Test
    public void TestRemoveSong() {
        Song song2 = new Song("O'Canada", "1990", "Justin Bieber", "Cool Song");
        Song song3 = new Song("What does the Fox Say?", "203", "Pewdiepie", "Very Nice!");
        this.musicList.addSong(song2);
        this.musicList.addSong(song3);
        this.musicList.removeSong("O'Canada");
        this.musicList.removeSong("What does the Fox Say?");
        assertEquals(1, musicList.getSongs().size());
        assertEquals("Happy Birthday", musicList.getSongs().get(0).getSongName());
    }

    @Test
    public void TestGetSongName() {
        assertEquals("Happy Birthday", song1.getSongName());
    }

    @Test
    public void GetSongDate() {
        assertEquals("2000", song1.getSongDate());
    }

    @Test
    public void GetSongArtist() {
        assertEquals("Jay-Z", song1.getSongArtist());
    }

    @Test
    public void GetSongInfo() {
        assertEquals("Listen on birthday!", song1.getSongInfo());
    }

    @Test
    public void TestGetPlayListName() {
        assertEquals("Christine's list", musicList.getName());
    }

    @Test
    public void TestGetSongs() {
        assertEquals(1, musicList.getSongs().size());
    }

    @Test
    public void TestSetName() {
        musicList.setName("New Name");
        assertEquals("New Name", musicList.getName());
    }

    @Test
    public void TestGetSongsInfo() {
        Song song2 = new Song("O'Canada", "1990", "Justin Bieber", "Cool Song");
        Song song3 = new Song("What does the Fox Say?", "203", "Pewdiepie", "Very Nice!");
        this.musicList.addSong(song2);
        this.musicList.addSong(song3);
        ArrayList<String> testing = new ArrayList<>();
        testing.add("Name: Happy Birthday");
        testing.add("Date: 2000");
        testing.add("Artist: Jay-Z");
        testing.add("Info: Listen on birthday!");
        assertTrue(testing.equals(musicList.getSongsInfo("Happy Birthday")));
    }


    @Test
    public void TestGetSongsNames() {
        Song song2 = new Song("O'Canada", "1990", "Justin Bieber", "Cool Song");
        Song song3 = new Song("What does the Fox Say?", "203", "Pewdiepie", "Very Nice!");
        this.musicList.addSong(song2);
        this.musicList.addSong(song3);
        ArrayList<String> testArray = new ArrayList<>();
        testArray.add("Happy Birthday");
        testArray.add("O'Canada");
        testArray.add("What does the Fox Say?");
        assertTrue(testArray.equals(musicList.getSongsNames()));
    }




}