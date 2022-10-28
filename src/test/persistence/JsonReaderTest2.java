package persistence;

import model.Song;
import model.MusicList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest2 extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader2 reader = new JsonReader2("./data/noSuchFile.json");
        try {
            MusicList ml = reader.readFav();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMusicList() {
        JsonReader2 reader = new JsonReader2("./data/testReaderEmptyMusicList2.json");
        try {
            MusicList ml = reader.readFav();
            assertEquals("New Playlist", ml.getName());
            assertEquals(0, ml.getNumSongs());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader2 reader = new JsonReader2("./data/testReaderGeneralMusicList2.json");
        try {
            MusicList ml = reader.readFav();
            assertEquals("New Playlist", ml.getName());
            List<Song> songs = ml.getSongs();
            assertEquals(2, songs.size());
            checkSong("hi", "2000", "Kanye", "hi", songs.get(0));
            checkSong("bye", "1999", "West", "bye", songs.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}