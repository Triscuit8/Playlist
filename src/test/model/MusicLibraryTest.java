package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void TestAddToList() {
        Song song2 = new Song("O'Canada", "1990", "Justin Bieber", "Cool Song");
        Song song3 = new Song("What does the Fox Say?", "203", "Pewdiepie", "Very Nice!");
        this.musicList.addSong(song2);
        this.musicList.addSong(song3);
        assertEquals(3, musicList.getSongs().size());
    }

    @Test
    public void TestRemoveFromList() {
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
    public void TestSongConstructor() {
        assertEquals("Happy Birthday", song1.getSongName());
        assertEquals("2000", song1.getSongDate());
        assertEquals("Jay-Z", song1.getSongArtist());
        assertEquals("Listen on birthday!", song1.getSongInfo());
    }
}