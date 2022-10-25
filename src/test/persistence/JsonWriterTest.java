package persistence;

import model.MusicList;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            MusicList ml = new MusicList("New Playlist");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMusicList() {
        try {
            MusicList ml = new MusicList("New Playlist");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMusicList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMusicList.json");
            ml = reader.read();
            assertEquals("New Playlist", ml.getName());
            assertEquals(0, ml.getNumSongs());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMusicList() {
        try {
            MusicList ml = new MusicList("New Playlist");
            ml.addSong(new Song("hi", "2000", "Kanye", "hi"));
            ml.addSong(new Song("bye", "1999", "West", "bye"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMusicList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMusicList.json");
            ml = reader.read();
            assertEquals("New Playlist", ml.getName());
            List<Song> songs = ml.getSongs();
            assertEquals(2, ml.getSize());
            checkSong("hi", songs.get(0));
            checkSong("bye", songs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}