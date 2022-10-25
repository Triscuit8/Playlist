package persistence;

import model.MusicList;
import model.Song;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public MusicList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMusicList(jsonObject);
    }


    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private MusicList parseMusicList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MusicList ml = new MusicList(name);
        addSongs(ml, jsonObject);
        return ml;
    }

    private void addSongs(MusicList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(ml, nextSong);
        }
    }

    private void addSong(MusicList ml, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("date");
        String artist = jsonObject.getString("artist");
        String info = jsonObject.getString("info");
        Song song = new Song(name, date, artist, info);
        ml.addSong(song);
    }





}



