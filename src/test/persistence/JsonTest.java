package persistence;

import model.Song;

import model.MusicList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkSong(String name, String date, String artist, String info, Song song) {
        assertEquals(name, song.getSongName());
        assertEquals(date, song.getSongDate());
        assertEquals(artist, song.getSongArtist());
        assertEquals(info, song.getSongInfo());
    }

}
