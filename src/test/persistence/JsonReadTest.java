package persistence;

import model.Song;
import model.MusicList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MusicList ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMusicList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMusicList.json");
        try {
            MusicList ml = reader.read();
            assertEquals("New Playlist", ml.getName());
            assertEquals(0, ml.getNumSongs());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMusicList.json");
        try {
            MusicList ml = reader.read();
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