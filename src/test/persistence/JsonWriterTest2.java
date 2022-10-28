package persistence;

import model.MusicList;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest2 extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile2() {
        try {
            MusicList ml = new MusicList("New Playlist");
            JsonWriter2 writer = new JsonWriter2("./data/my\0illegal:fileName.json");
            writer.openFav();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMusicList2() {
        try {
            MusicList ml = new MusicList("New Playlist");
            JsonWriter2 writer = new JsonWriter2("./data/testWriterEmptyMusicList.json");
            writer.openFav();
            writer.writeFav(ml);
            writer.closeFav();

            JsonReader2 reader = new JsonReader2("./data/testWriterEmptyMusicList.json");
            ml = reader.readFav();
            assertEquals("New Playlist", ml.getName());
            assertEquals(0, ml.getNumSongs());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMusicList2() {
        try {
            MusicList ml = new MusicList("New Playlist");
            ml.addSong(new Song("hi", "2000", "Kanye", "hi"));
            ml.addSong(new Song("bye", "1999", "West", "bye"));
            JsonWriter2 writer = new JsonWriter2("./data/testWriterGeneralMusicList.json");
            writer.openFav();
            writer.writeFav(ml);
            writer.closeFav();

            JsonReader2 reader = new JsonReader2("./data/testWriterGeneralMusicList.json");
            ml = reader.readFav();
            assertEquals("New Playlist", ml.getName());
            List<Song> songs = ml.getSongs();
            assertEquals(2, songs.size());
            checkSong("hi", "2000", "Kanye", "hi", songs.get(0));
            checkSong("bye", "1999", "West", "bye", songs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}