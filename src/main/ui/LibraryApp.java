package ui;

import model.MusicList;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;
//import persistence.JsonReader2;
//import persistence.JsonWriter2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class LibraryApp extends JFrame implements ActionListener {


    JFrame frame;
    JTextField fieldN;
    JTextField fieldD;
    JTextField fieldA;
    JTextField fieldI;

    JButton buttonAdd;
    JButton buttonDelete;
    JButton buttonSave;
    JButton buttonLoad;

    JLabel label1;
    JLabel label2;

    JPanel panel1;

    Icon image1;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;


    Scanner userInput = new Scanner(System.in);
    Scanner userScreenNewName = new Scanner(System.in);
    Scanner userScreenNewSongName = new Scanner(System.in);
    Scanner userScreenNewDate = new Scanner(System.in);
    Scanner userScreenNewArtist = new Scanner(System.in);
    Scanner userScreenNewInfo = new Scanner(System.in);
    Scanner userScreenFavSongAdd = new Scanner(System.in);
    Scanner userScreenFavSongRemove = new Scanner(System.in);
    Boolean stayOnMenu = true;
    MusicList musicList = new MusicList("New Playlist");
    MusicList favList = new MusicList("Favourite List");

    private static final String JSON_STORE = "./data/musiclist.json";
    private static final String JSON_STORE2 = "./data/favmusiclist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader2;

    Scanner uiInput = new Scanner(System.in);
    String userChoice;

    //EFFECTS: Constructs a library
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public LibraryApp() {


        initializeGraphics();
        theMainMenu();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);


//        while (stayOnMenu) {
//
//            theMainMenu();
//
//            userChoice = userInput.nextLine().toLowerCase();
//
//            //String userChoice = uiInput.nextLine().toLowerCase();
//
//            if (userChoice.equals("a")) {
//
//                renameMusicListMenu();
//
//            } else if (userChoice.equals("b")) {
//
//                addSongMenu();
//
//            } else if (userChoice.equals("c")) {
//
//                removeSongMenu();
//
//            } else if (userChoice.equals("d")) {
//
//                getSongsInfoMenu();
//
//            } else if (userChoice.equals("e")) {
//
//                shuffleSongsMenu();
//
//            } else if (userChoice.equals("f")) {
//
//                addSongToFavListMenu();
//
//            } else if (userChoice.equals("g")) {
//
//                removeSongFromFavListMenu();
//
//            } else if (userChoice.equals("q")) {
//
//                quitApp();
//
//            } else if (userChoice.equals("s")) {
//
//                saveMusicList();
//                saveFavMusicList();
//
//            } else if (userChoice.equals("l")) {
//
//                loadMusicList();
//                loadFavMusicList();
//
//            } else {
//                invalidKeyMain();
//            }
//        }
}


    //EFFECTS: Prints out a screen for the main menu of the program
    public void theMainMenu() {

        System.out.println("Welcome to " + musicList.getName() + "!");
        System.out.println("The songs in the list are: " + musicList.getSongsNames());
        System.out.println("Your favourite songs: " + favList.getSongsNames());

        System.out.println("a -> rename the playlist");

        System.out.println("b -> add a song to the playlist!");

        System.out.println("c -> remove a song from the playlist!");

        System.out.println("d -> to learn more about a song!");

        System.out.println("e -> to shuffle the list!");

        System.out.println("f -> add a song from the main list to the favourite list!");

        System.out.println("g -> remove a song from the favourite list!");

        System.out.println("s -> save the playlist!");

        System.out.println("l -> load the music list!");

        System.out.println("q -> quit the application!");
    }


    //MODIFIES: This
    //EFFECTS: Renames the playlist as well as prints out a menu for it
    public void renameMusicListMenu() {

        System.out.println("What would you like to name the music list?");
        String newName = userScreenNewName.nextLine();
        musicList.setName(newName);
        System.out.println("The music list has been renamed!");
        System.out.println('\n');
    }

    //MODIFIES: This
    //EFFECTS: Constructs a song menu after adding a new song
    public void addSongMenu() {
        System.out.println("What song would you like to add to the list?");
        System.out.println("Enter the name of the song, the date, the artist, and info you wish to add.");
        String newSongName = userScreenNewSongName.nextLine();
        String newSongDate = userScreenNewDate.nextLine();
        String newSongArtist = userScreenNewArtist.nextLine();
        String newSongInfo = userScreenNewInfo.nextLine();
        Song newSong = new Song(newSongName, newSongDate, newSongArtist, newSongInfo);
        musicList.addSong(newSong);
        System.out.println("Song has been added!");
        System.out.println('\n');
    }

    public void addSongMenu(Song song) {
        musicList.addSong(song);

    }

    //EFFECTS: Prints out a menu for hitting an invalid key
    public void invalidKeyMain() {
        System.out.println("Sorry that is not a valid option!");
        System.out.println('\n');
    }

    //MODIFIES: This
    //EFFECTS: Prints out a screen for removing a song and removes a song from the list
    public void removeSongMenu() {
        System.out.println("Please type in the name of the song you would like removed");
        Scanner userRemoveSongChoice = new Scanner(System.in);
        String songBeingRemoved = userRemoveSongChoice.nextLine();
        musicList.removeSong(songBeingRemoved);
        System.out.println("The song has been removed");
        System.out.println('\n');
    }

    //EFFECTS: Outputs a screen that shows the information about a song
    public void getSongsInfoMenu() {
        System.out.println("Which song would you like to know more about?");
        Scanner userGetInfoAboutASong = new Scanner(System.in);
        String getInfo = userGetInfoAboutASong.nextLine();
        System.out.println("Here is the information about the song");
        System.out.println(musicList.getSongsInfo(getInfo));
        System.out.println('\n');
    }

    //MODIFIES: This
    //EFFECTS: Outputs a screen after shuffling the song list
    public void shuffleSongsMenu() {
        musicList.shuffleSongs();
        System.out.println("The list has been shuffled!");
        System.out.println('\n');

    }

    //MODIFIES: This
    //EFFECTS: Adds a song to the favourite list as well as print out a screen for it
    public void addSongToFavListMenu() {
        System.out.println("Which song from the main playlist would you like to add to the favourite list?");
        String favSong = userScreenFavSongAdd.nextLine();
        for (Song s : musicList.getSongs()) {
            if (s.getSongName().equals(favSong)) {
                favList.addSong(s);
                //toString
            }
        }
        System.out.println(favList);
        System.out.println('\n');
    }

    //MODIFIES: This
    //EFFECTS: Removes a song from the favourite list and prints out a screen for it
    public void removeSongFromFavListMenu() {
        System.out.println("Which song would you like to remove from the favourite list?");
        String removeFavSong = userScreenFavSongRemove.nextLine();
        favList.removeSong(removeFavSong);
        System.out.println('\n');
    }

    //MODIFIES: This
    //EFFECTS: Ends the while loop and prints out a screen that say "See you next time!"
    public void quitApp() {
        System.out.println("See you next time!");
        stayOnMenu = false;
    }

    private void saveMusicList() {
        try {
            jsonWriter.open();
            jsonWriter.write(musicList);
            jsonWriter.close();
            System.out.println("Saved " + musicList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadMusicList() {
        try {
            musicList = jsonReader.read();
            System.out.println("Loaded " + musicList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void saveFavMusicList() {
        try {
            jsonWriter2.open();
            jsonWriter2.write(favList);
            jsonWriter2.close();
            System.out.println("Saved " + favList.getName() + " to " + JSON_STORE2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE2);
        }
    }

    private void loadFavMusicList() {
        try {
            favList = jsonReader2.read();
            System.out.println("Loaded " + favList.getName() + " from " + JSON_STORE2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE2);
        }
    }



    private void initializeGraphics() {

        Border border = BorderFactory.createLineBorder(Color.GREEN);

        image1 = new ImageIcon(getClass().getResource("mu.jpeg"));

        label1 = new JLabel();
        label1.setText("Welcome! Your songs are " + songNamesUI());
        label1.setVisible(true);
        label1.setBorder(border);
        label1.setOpaque(true);
        label1.setVerticalAlignment(JLabel.CENTER);
        label1.setHorizontalAlignment(JLabel.CENTER);

        label2 = new JLabel(image1);
        label2.setBounds(0, 250, 800, 350);


        panel1 = new JPanel();
        panel1.setBackground(Color.LIGHT_GRAY);
        panel1.setBounds(0, 0, 800, 250);
        panel1.setLayout(new BorderLayout());


        buttonAdd = new JButton();
        buttonAdd.setBounds(50, 300, 80, 60);
        buttonAdd.addActionListener(this);
        buttonAdd.setText("Add");
        buttonAdd.setFocusable(false);
        buttonAdd.setBackground(Color.LIGHT_GRAY);
        buttonAdd.setOpaque(true);
        buttonAdd.setFont(new Font ("Comic Sans", Font.BOLD, 12));

        buttonDelete = new JButton();
        buttonDelete.setBounds(200, 300, 80, 60);
        buttonDelete.addActionListener(this);
        buttonDelete.setText("Remove");
        buttonDelete.setFocusable(false);
        buttonDelete.setBackground(Color.LIGHT_GRAY);
        buttonDelete.setOpaque(true);
        buttonDelete.setFont(new Font ("Comic Sans", Font.BOLD, 12));

        buttonSave = new JButton();
        buttonSave.setBounds(50, 400, 80, 60);
        buttonSave.addActionListener(this);
        buttonSave.setText("Save");
        buttonSave.setFocusable(false);
        buttonSave.setBackground(Color.LIGHT_GRAY);
        buttonSave.setOpaque(true);
        buttonSave.setFont(new Font ("Comic Sans", Font.BOLD, 12));

        buttonLoad = new JButton();
        buttonLoad.setBounds(200, 400, 80, 60);
        buttonLoad.addActionListener(this);
        buttonLoad.setText("Load");
        buttonLoad.setFocusable(false);
        buttonLoad.setBackground(Color.LIGHT_GRAY);
        buttonLoad.setOpaque(true);
        buttonLoad.setFont(new Font ("Comic Sans", Font.BOLD, 12));

        fieldN = new JTextField();
        fieldN.setPreferredSize(new Dimension(250, 40));
        fieldN.setBounds(400, 300, 250, 40);
        fieldN.setText("Enter song name...");

        fieldD = new JTextField();
        fieldD.setPreferredSize(new Dimension(250, 40));
        fieldD.setBounds(400, 350, 250, 40);
        fieldD.setText("Enter song date...");

        fieldA = new JTextField();
        fieldA.setPreferredSize(new Dimension(250, 40));
        fieldA.setBounds(400, 400, 250, 40);
        fieldA.setText("Enter artist name...");

        fieldI = new JTextField();
        fieldI.setPreferredSize(new Dimension(250, 40));
        fieldI.setBounds(400, 450, 250, 40);
        fieldI.setText("Enter song info...");

        frame = new JFrame();
        frame.setTitle("Music Playlist");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);

        panel1.add(label1);


        frame.add(panel1);




        frame.add(buttonAdd);
        frame.add(buttonDelete);
        frame.add(buttonSave);
        frame.add(buttonLoad);

        frame.add(fieldN);
        frame.add(fieldD);
        frame.add(fieldA);
        frame.add(fieldI);
        frame.add(label2);

        frame.setLayout(null);

        frame.getContentPane().setBackground(Color.WHITE);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            Song newSong = new Song(fieldN.getText(), fieldD.getText(), fieldA.getText(), fieldI.getText());
            musicList.addSong(newSong);
            label1.setText("Welcome! Your songs are " + songNamesUI());
        }
        if (e.getSource() == buttonDelete) {
            musicList.removeSong(fieldN.getText());
            label1.setText("Welcome! Your songs are " + songNamesUI());
        }
        if (e.getSource() == buttonSave) {
            saveMusicList();
            label1.setText("Welcome! Your songs are " + songNamesUI());
        }
        if (e.getSource() == buttonLoad) {
            loadMusicList();
            label1.setText("Welcome! Your songs are " + songNamesUI());
        }
    }

    public String addSongScreen() {
        return "Please enter the Song's name, date, artist and info.";

    }

    public String removeSongScreen() {
        return "Please enter the name of the song you wish to remove.";
    }

    public String saveListScreen() {
        return "The list has been saved";
    }


    public String songNamesUI() {
        return musicList.getSongsNames().toString();
    }


}
