package model;

import java.util.ArrayList;
import java.util.Collections;


public class MusicList {

    String name;
    ArrayList<String> songsNames;
    private ArrayList<Song> songs;


    //EFFECTS: Constructs a MusicList with 3 fields: name, list of songs, and the names of the songs
    public MusicList(String name) {
        this.name = name;
        songs = new ArrayList<>();
        songsNames = new ArrayList<>();
    }

    //EFFECTS: returns the name of the playlist
    public String getName() {
        return this.name;
    }


    //EFFECTS: Returns songs
    public ArrayList<Song> getSongs() {
        return this.songs;
    }

    //REQUIRES: String
    //MODIFIES: this
    //EFFECTS: sets the name of the class
    public void setName(String name) {
        this.name = name;
    }

    //REQUIRES: Song
    //MODIFIES: this
    //EFFECTS: adds a song to songs
    public void addSong(Song song) {
        this.songs.add(song);
    }


    //EFFECTS: Returns a list of names for songs
    public ArrayList<String> getSongsNames() {
        ArrayList<String> outputSongs = new ArrayList<>();
        for (Song s : this.songs) {
            outputSongs.add(s.getSongName());
        }
        return outputSongs;
    }

    //REQUIRES: String
    //EFFECTS: Outputs a list information for song with the given name
    public ArrayList<String> getSongsInfo(String name) {
        ArrayList<String> outputInfo = new ArrayList<>();
        for (Song s : this.songs) {
            if (s.getSongName().equals(name)) {
                outputInfo.add("Name: " + s.getSongName());
                outputInfo.add("Date: " + s.getSongDate());
                outputInfo.add("Artist: " + s.getSongArtist());
                outputInfo.add("Info: " + s.getSongInfo());
                break;
            }
        }
        return outputInfo;
    }

    //REQUIRES: String
    //MODIFIES: this
    //EFFECTS: Removes song with given name from songs
    public void removeSong(String name) {
        for (Song s : this.songs) {
            if (s.getSongName().equals(name)) {
                this.songs.remove(s);
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: shuffles the order of songs
    public void shuffleSongs() {
        Collections.shuffle(this.songs);
    }


}
