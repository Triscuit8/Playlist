package model;

import org.json.JSONObject;
import persistence.Writable;


public class Song implements Writable {
    private String name;
    private String date;
    private String artist;
    private String info;


    //EFFECTS: Constructs Song with 4 fields: name, date, artist, and info
    public Song(String name, String date, String artist, String info) {
        this.name = name;
        this.date = date;
        this.artist = artist;
        this.info = info;
    }

    //EFFECTS: Returns the name of the song
    public String getSongName() {
        return this.name;
    }

    //EFFECTS: Returns the date of the song
    public String getSongDate() {
        return this.date;
    }

    //EFFECTS: Returns the artist of the song
    public String getSongArtist() {
        return this.artist;
    }

    //EFFECTS: Returns the info about the song
    public String getSongInfo() {
        return this.info;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("artist", artist);
        json.put("info", info);
        return json;
    }
}
