package ui;

import model.Event;
import model.EventLog;
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
    JTextField fieldC;

    JButton buttonAdd;
    JButton buttonDelete;
    JButton buttonSave;
    JButton buttonLoad;
    JButton buttonShuffle;
    JButton buttonChangeName;
    JButton buttonQuit;

    JLabel label1;
    JLabel label2;

    JPanel panel1;

    Icon image1;

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
    public LibraryApp() {
        initializeUI();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);
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

    //EFFECTS: Saves the state of the music list into JSon date
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

    //EFFECTS: reloads the last saved state of the music list from saved JSon date
    private void loadMusicList() {
        try {
            musicList = jsonReader.read();
            System.out.println("Loaded " + musicList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: reloads the last saved state of the favourite music list from saved JSon date
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

    //EFFECTS: reloads the last saved state of the favourite music list from saved JSon date
    private void loadFavMusicList() {
        try {
            favList = jsonReader2.read();
            System.out.println("Loaded " + favList.getName() + " from " + JSON_STORE2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE2);
        }
    }

    //EFFECTS: Creates the UI for the application
    private void initializeUI() {
        createLabels();
        createPanel();
        createButtonAdd();
        createButtonDelete();
        createButtonSave();
        createButtonLoad();
        createButtonShuffle();
        createButtonNewListName();
        createButtonQuit();
        createFields();
        createFrame();
        addComponents();
    }

    //EFFECTS: Assigns each button a function when triggered
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            buttonAddFunction();
        }
        if (e.getSource() == buttonDelete) {
            buttonDeleteFunction();
        }
        if (e.getSource() == buttonSave) {
            buttonSaveFunction();
        }
        if (e.getSource() == buttonLoad) {
            buttonLoadFunction();
        }
        if (e.getSource() == buttonShuffle) {
            buttonShuffleFunction();
        }
        if (e.getSource() == buttonChangeName) {
            buttonChangeNameFunction();
        }
        if (e.getSource() == buttonQuit) {
            buttonQuitFunction();
        }
    }

    //EFFECTS: Turns the list of music names into a string
    public String songNamesUI() {
        return musicList.getSongsNames().toString();
    }

    //EFFECTS: Creates the panel for the application
    public void createPanel() {
        panel1 = new JPanel();
        panel1.setBackground(Color.LIGHT_GRAY);
        panel1.setBounds(0, 0, 800, 200);
        panel1.setLayout(new BorderLayout());
    }

    //EFFECTS: Creates the labels for the application
    public void createLabels() {
        Border border = BorderFactory.createLineBorder(Color.GREEN);
        image1 = new ImageIcon(getClass().getResource("mu.jpeg"));
        label1 = new JLabel();
        label1.setText("Welcome to " + musicList.getName() + "! Your songs are " + songNamesUI());
        label1.setVisible(true);
        label1.setBorder(border);
        label1.setOpaque(true);
        label1.setVerticalAlignment(JLabel.CENTER);
        label1.setHorizontalAlignment(JLabel.CENTER);

        label2 = new JLabel(image1);
        label2.setBounds(0, 200, 800, 400);
    }

    //EFFECTS: Creates the add button for the application
    public void createButtonAdd() {
        buttonAdd = new JButton();
        buttonAdd.setBounds(50, 250, 120, 60);
        buttonAdd.addActionListener(this);
        buttonAdd.setText("Add");
        buttonAdd.setFocusable(false);
        buttonAdd.setBackground(Color.LIGHT_GRAY);
        buttonAdd.setOpaque(true);
        buttonAdd.setFont(new Font("Comic Sans", Font.BOLD, 12));
    }

    //EFFECTS: Creates the delete button for the application
    public void createButtonDelete() {
        buttonDelete = new JButton();
        buttonDelete.setBounds(200, 250, 120, 60);
        buttonDelete.addActionListener(this);
        buttonDelete.setText("Remove");
        buttonDelete.setFocusable(false);
        buttonDelete.setBackground(Color.LIGHT_GRAY);
        buttonDelete.setOpaque(true);
        buttonDelete.setFont(new Font("Comic Sans", Font.BOLD, 12));
    }

    //EFFECTS: Creates the save button for the application
    public void createButtonSave() {
        buttonSave = new JButton();
        buttonSave.setBounds(50, 350, 120, 60);
        buttonSave.addActionListener(this);
        buttonSave.setText("Save");
        buttonSave.setFocusable(false);
        buttonSave.setBackground(Color.LIGHT_GRAY);
        buttonSave.setOpaque(true);
        buttonSave.setFont(new Font("Comic Sans", Font.BOLD, 12));
    }

    //EFFECTS: Creates the load button for the application
    public void createButtonLoad() {
        buttonLoad = new JButton();
        buttonLoad.setBounds(200, 350, 120, 60);
        buttonLoad.addActionListener(this);
        buttonLoad.setText("Load");
        buttonLoad.setFocusable(false);
        buttonLoad.setBackground(Color.LIGHT_GRAY);
        buttonLoad.setOpaque(true);
        buttonLoad.setFont(new Font("Comic Sans", Font.BOLD, 12));
    }

    //EFFECTS: Creates the shuffle button for the application
    public void createButtonShuffle() {
        buttonShuffle = new JButton();
        buttonShuffle.setBounds(50, 450, 120, 60);
        buttonShuffle.addActionListener(this);
        buttonShuffle.setText("Shuffle");
        buttonShuffle.setFocusable(false);
        buttonShuffle.setBackground(Color.LIGHT_GRAY);
        buttonShuffle.setOpaque(true);
        buttonShuffle.setFont(new Font("Comic Sans", Font.BOLD, 12));
    }

    //EFFECTS: Creates the change list name button for the application
    public void createButtonNewListName() {
        buttonChangeName = new JButton();
        buttonChangeName.setBounds(200, 450, 120, 60);
        buttonChangeName.addActionListener(this);
        buttonChangeName.setText("New List Name");
        buttonChangeName.setFocusable(false);
        buttonChangeName.setBackground(Color.LIGHT_GRAY);
        buttonChangeName.setOpaque(true);
        buttonChangeName.setFont(new Font("Comic Sans", Font.BOLD, 12));
    }

    public void createButtonQuit() {
        buttonQuit = new JButton();
        buttonQuit.setBounds(735, 525, 40, 40);
        buttonQuit.addActionListener(this);
        buttonQuit.setText("Quit");
        buttonQuit.setFocusable(false);
        buttonQuit.setBackground(Color.LIGHT_GRAY);
        buttonQuit.setOpaque(true);
        buttonQuit.setFont(new Font("Comic Sans", Font.BOLD, 12));
    }

    //EFFECTS: Creates the text fields for the application
    public void createFields() {
        fieldC = new JTextField();
        fieldC.setPreferredSize(new Dimension(250, 40));
        fieldC.setBounds(400, 250, 250, 40);
        fieldC.setText("Enter new list name...");

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
    }

    //EFFECTS: Creates the main frame for the application
    public void createFrame() {
        frame = new JFrame();
        frame.setTitle("Music Playlist");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
    }

    //EFFECTS: Adding all the components together for the application
    public void addComponents() {
        panel1.add(label1);
        frame.add(panel1);
        frame.add(buttonAdd);
        frame.add(buttonDelete);
        frame.add(buttonSave);
        frame.add(buttonLoad);
        frame.add(buttonShuffle);
        frame.add(buttonChangeName);
        frame.add(buttonQuit);
        frame.add(fieldC);
        frame.add(fieldN);
        frame.add(fieldD);
        frame.add(fieldA);
        frame.add(fieldI);
        frame.add(label2);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
    }

    //EFFECTS: Assigning a function for the add button
    public void buttonAddFunction() {
        Song newSong = new Song(fieldN.getText(), fieldD.getText(), fieldA.getText(), fieldI.getText());
        musicList.addSong(newSong);
        EventLog.getInstance().logEvent(new Event("Added song."));
        label1.setText("Welcome to " + musicList.getName() + "! Your songs are " + songNamesUI());
        fieldC.setText("Enter new list name...");
        fieldN.setText("Enter song name...");
        fieldD.setText("Enter song date...");
        fieldA.setText("Enter artist name...");
        fieldI.setText("Enter song info...");
    }

    //EFFECTS: Assigning a function for the delete button
    public void buttonDeleteFunction() {
        musicList.removeSong(fieldN.getText());
        EventLog.getInstance().logEvent(new Event("Deleted song."));
        label1.setText("Welcome to " + musicList.getName() + "! Your songs are " + songNamesUI());
        fieldC.setText("Enter new list name...");
        fieldN.setText("Enter song name...");
        fieldD.setText("Enter song date...");
        fieldA.setText("Enter artist name...");
        fieldI.setText("Enter song info...");
    }

    //EFFECTS: Assigning a function for the save button
    public void buttonSaveFunction() {
        saveMusicList();
        label1.setText("Welcome to " + musicList.getName() + "! Your songs are " + songNamesUI());
        EventLog.getInstance().logEvent(new Event("Saved list."));
        fieldC.setText("Enter new list name...");
        fieldN.setText("Enter song name...");
        fieldD.setText("Enter song date...");
        fieldA.setText("Enter artist name...");
        fieldI.setText("Enter song info...");
    }

    //EFFECTS: Assigning a function for the load button
    public void buttonLoadFunction() {
        loadMusicList();
        EventLog.getInstance().logEvent(new Event("Loaded list."));
        label1.setText("Welcome back to " + musicList.getName() + "! Your songs are " + songNamesUI());
        fieldC.setText("Enter new list name...");
        fieldN.setText("Enter song name...");
        fieldD.setText("Enter song date...");
        fieldA.setText("Enter artist name...");
        fieldI.setText("Enter song info...");
    }

    //EFFECTS: Assigning a function for the shuffle button
    public void buttonShuffleFunction() {
        musicList.shuffleSongs();
        EventLog.getInstance().logEvent(new Event("Shuffled list."));
        label1.setText("Welcome to " + musicList.getName() + "! Your songs are " + songNamesUI());
        fieldC.setText("Enter new list name...");
        fieldN.setText("Enter song name...");
        fieldD.setText("Enter song date...");
        fieldA.setText("Enter artist name...");
        fieldI.setText("Enter song info...");
    }

    //EFFECTS: Assigning a function for the change list name button
    public void buttonChangeNameFunction() {
        musicList.setName(fieldC.getText());
        EventLog.getInstance().logEvent(new Event("Changed list name."));
        label1.setText("Welcome to " + musicList.getName() + "! Your songs are " + songNamesUI());
        fieldC.setText("Enter new list name...");
        fieldN.setText("Enter song name...");
        fieldD.setText("Enter song date...");
        fieldA.setText("Enter artist name...");
        fieldI.setText("Enter song info...");
    }

    public void buttonQuitFunction() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString());
        }
        frame.dispose();
    }
}
