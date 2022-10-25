package persistence;

import model.MusicList;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter2 {
    private static final int TAB = 4;
    private PrintWriter writer2;
    private String destination2;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter2(String destination) {
        this.destination2 = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void openFav() throws FileNotFoundException {
        writer2 = new PrintWriter(new File(destination2));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void writeFav(MusicList ml) {
        JSONObject json2 = ml.toJson();
        saveToFavFile2(json2.toString(TAB));
    }


    // MODIFIES: this
    // EFFECTS: closes writer
    public void closeFav() {
        writer2.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFavFile2(String json) {
        writer2.print(json);
    }
}

