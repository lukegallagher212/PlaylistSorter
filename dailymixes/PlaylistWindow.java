package dailymixes;

import cs2.*;
import cs2.Button;
import cs2.Shape;
import cs2.Window;
import list.AList;

import java.awt.*;

/**
 * The playlist GUI window that displays the songs in the queue and
 * the songs in the playlist. The user can accept or reject (>:(<) a song.
 *
 * @author Michael Peters (mpeters01)
 * @version 2023.03.12
 */
public class PlaylistWindow {

    private Window window;
    private PlaylistCalculator playlistCalculator;
    private Button accept;
    private Button reject;

    private static final double DISPLAY_FACTOR = 1.5;

    private static final int MARGIN = 12;
    private static final int PLAYLIST_PADDING = 50;
    private static final int PLAYLIST_X = PLAYLIST_PADDING;
    private static final int PLAYLIST_Y = 280;
    private static final int PLAYLIST_SIZE = ( (int) (500 * DISPLAY_FACTOR) - (4 * PLAYLIST_PADDING) ) / 3;

    private AList<Shape[]> songRectangles;
    private static final int MUSIC_NOTE_SIZE = 31;
    private static final int QUEUE_STARTX = MARGIN + 30;
    private static final int QUEUE_STARTY = 20;
    private static final int SONG_SHAPE_HEIGHT = 75;

    private Shape[] playlistShapes = new Shape[3];
    private CircleShape[] playlistCircles = new CircleShape[3];
    private static Color[] PLAYLIST_COLORS = new Color[8];


    /**
     * Creates a new playlist window
     * @param playlistCalculator a playlist calculator instance to pull queue and playlists from
     */
    public PlaylistWindow(PlaylistCalculator playlistCalculator) {
        window = new Window("Playlist Algorithm Generator");
        window.setSize((int) (500 * DISPLAY_FACTOR), (int) (500 * DISPLAY_FACTOR));

        this.playlistCalculator = playlistCalculator;

        accept = new Button("Add to Playlist");
        window.addButton(accept, WindowSide.SOUTH);
        accept.onClick(this, "clickedAccept");

        reject = new Button("Reject from Playlist");
        window.addButton(reject, WindowSide.SOUTH);
        reject.onClick(this, "clickedReject");

        this.songRectangles = new AList<Shape[]>();

        PLAYLIST_COLORS[0] = new Color(51, 92, 103); //blue
        PLAYLIST_COLORS[1] = new Color(224, 159, 62); //yellow
        PLAYLIST_COLORS[2] = new Color(158, 42, 43); //red
        PLAYLIST_COLORS[3] = new Color(17, 17, 17); //black
        PLAYLIST_COLORS[4] = new Color(38, 68, 77); //dark blue
        PLAYLIST_COLORS[5] = new Color(97, 69, 27); //dark yellow
        PLAYLIST_COLORS[6] = new Color(71, 19, 19); //dark red
        PLAYLIST_COLORS[7] = new Color(255, 255, 255); //white

        initButtons();
        drawPlaylists();
        drawShapes();
        updateText();
        updateButtons();

    }

    /**
     * This method dequeues the pending song at the front of the
     * queue, adding it to the playlist (possibly), and updates the window to represent the current status
     *
     * @param button
     *            accept button (the one that was pressed)
     */
    public void clickedAccept(Button button) {
        if (!playlistCalculator.getQueue().isEmpty()) {

            if (playlistCalculator.addSongToPlaylist()) {
                update();
            }
            else {
                accept.disable();
            }
        }
        else {
            accept.disable();
            reject.disable();
            endSimulation();
        }
    }

    /**
     * Rejects adding the current song to the playlist.
     *
     * @param button the reject button
     */
    public void clickedReject(Button button) {
        playlistCalculator.reject();
        accept.enable();
        update();
    }

    /**
     * Update everything. Wipes the screen and resets.
     */
    private void update() {
        if (playlistCalculator.getQueue().isEmpty()) {
            endSimulation();
        }
        else {
            window.removeAllShapes();
            updateQueue();
            drawPlaylists();
            updateText();
            updateButtons();

        }
    }

    /**
     * This method updates whether the accept button is enabled based on the
     * qualifications of the upcoming song in the queue
     */
    private void updateButtons() {
        reject.enable();
        if (playlistCalculator.getPlaylistForSong(playlistCalculator.getQueue()
                .getFront()) != null)
            accept.enable();
        else
            accept.disable();
    }


    /**
     * When the window is displayed the first time buttons are disabled if queue
     * is empty, otherwise enable
     */
    private void initButtons() {
        if (playlistCalculator.getQueue().isEmpty()) {
            accept.disable();
            reject.disable();
        }
        else {
            accept.enable();
            reject.enable();
        }
    }

    /**
     * Draw the phone objects for the playlists seen on screen
     */
    private void drawPlaylists() {
        drawPhone(PLAYLIST_X, PLAYLIST_Y, 0);
        drawPhone(PLAYLIST_X + PLAYLIST_SIZE + PLAYLIST_PADDING, PLAYLIST_Y, 1);
        drawPhone(PLAYLIST_X + (2 * PLAYLIST_SIZE) + (2 * PLAYLIST_PADDING), PLAYLIST_Y, 2);
    }

    /**
     * Draws a phone object at the given coordinates for a given playlist index
     * @param x x coordinate
     * @param y y coordinate
     * @param playlistIndex index of the playlist
     */
    private void drawPhone(int x, int y, int playlistIndex) {
        Shape phone = new Shape(x, y, PLAYLIST_SIZE, (int) (PLAYLIST_SIZE * 1.25), PLAYLIST_COLORS[3]);
        playlistShapes[playlistIndex] = phone;
        Shape innerPhone = new Shape(x + 5, y + 5, PLAYLIST_SIZE - 10, (int) (PLAYLIST_SIZE * 1.25) - 10, Color.darkGray);
        window.addShape(phone);
        window.addShape(innerPhone);
        window.moveToFront(innerPhone);
        int textY = innerPhone.getY() + 5;
        int textX = innerPhone.getX() + 5;
        Playlist playlist = playlistCalculator.getPlaylists()[playlistIndex];
        String message = playlist.getName();
        TextShape playlistName = new TextShape(textX, textY, message, Color.WHITE, 20);
        playlistName.setBackgroundColor(innerPhone.getBackgroundColor());
        playlistName.setX(innerPhone.getX() + (innerPhone.getWidth() - playlistName.getWidth()) / 2);
        window.addShape(playlistName);
        window.moveToFront(playlistName);
        //populate the playlist with rectangles representing the songs in the playlist
        int songStartY = playlistName.getY() + playlistName.getHeight() + 5;
        for( int i = 0; i < Math.min(playlist.getNumberOfSongs(), 7); i++) {
            Shape song = new Shape(innerPhone.getX() + 5, songStartY + (i * 25), innerPhone.getWidth() - 10, 20, PLAYLIST_COLORS[playlistIndex]);
            window.addShape(song);
            window.moveToFront(song);
        }
    }

    /**
     * Draws the notes on the screen in accordance to the queued songs
     */
    private void drawShapes() {
        int shapeX = QUEUE_STARTX;
        int shapeY = QUEUE_STARTY;
        Object[] queuedSongs = {};
        if (playlistCalculator.getQueue() != null) {
            queuedSongs = playlistCalculator.getQueue().toArray();
        }
        for (int i = 0; i < queuedSongs.length; i++) {
            int shapeWidth = MUSIC_NOTE_SIZE;
            int playlistPreference = playlistCalculator.getPlaylistIndex(
                    ((Song)queuedSongs[i]).getPlaylistName());

            if(playlistPreference == -1) {
                playlistPreference = 3;
            }
            Color color = PLAYLIST_COLORS[playlistPreference];

            Shape[] songRectangle = drawNote(shapeX, shapeY, color);
            songRectangles.add(songRectangle);
            shapeX += shapeWidth + PLAYLIST_PADDING;
        }

    }

    /**
     * Draws a note at the given coordinates with the given color (using a rectangle and a circle)
     * @param x x coordinate of the note
     * @param y y coordinate of the note
     * @param color color of the note
     * @return an array of the shapes that make up the note
     */
    private Shape[] drawNote(int x, int y, Color color) {
        Shape[] note = new Shape[2];
        Shape bar = new Shape(x, y, 6, SONG_SHAPE_HEIGHT, color);
        Shape circle = new CircleShape(x - MUSIC_NOTE_SIZE + bar.getWidth() + 1, y + bar.getHeight() - (MUSIC_NOTE_SIZE / 2), MUSIC_NOTE_SIZE, color);
        note[0] = bar;
        note[1] = circle;
        window.addShape(bar);
        window.addShape(circle);
        return note;
    }

    /**
     * This method removes the song at the front of the queue and in turn
     * calls updateNotes to redraw the remaining songs (notes) in the queue
     */
    private void updateQueue() {
        if (!songRectangles.isEmpty())
            songRectangles.remove(0);
        updateNotes();

    }

    /**
     * This method redraws the position of the notes on the window.
     * This is invoked whenever the accept or reject button is clicked
     */
    private void updateNotes() {
        int shapeX = QUEUE_STARTX;
        for (int i = 0; i < songRectangles.getLength(); i++) {
            Shape[] arr = songRectangles.getEntry(i);
            arr[0].setX(shapeX);
            arr[1].setX(shapeX - MUSIC_NOTE_SIZE + arr[0].getWidth() + 1);
            window.addShape(arr[0]);
            window.addShape(arr[1]);
            shapeX = shapeX + arr[1].getWidth() + PLAYLIST_PADDING;

        }
    }

    /**
     * This updates the text objects across the entire window
     */
    private void updateText() {
        updatePlaylistText();
        updateSongText();
    }

    /**
     * This method updates the text objects that are associated with the queued song
     */
    private void updateSongText() {
        Song song = playlistCalculator.getQueue().getFront();
        int x = PLAYLIST_PADDING;
        int y = QUEUE_STARTY + SONG_SHAPE_HEIGHT + PLAYLIST_PADDING;
        int ogY = y;
        int maxX = x;
        String message = song.getName();
        TextShape songText = addTextShape(message, x, y);
        y += songText.getHeight();
        maxX = Math.max(maxX, x + songText.getWidth());
        message = "Percent Pop: " + song.getGenreSet().getPop() + "%";
        songText = addTextShape(message, x, y);
        y += songText.getHeight();
        maxX = Math.max(maxX, x + songText.getWidth());
        message = "Percent Rock: " + song.getGenreSet().getRock() + "%";
        songText = addTextShape(message, x, y);
        y += songText.getHeight();
        maxX = Math.max(maxX, x + songText.getWidth());
        message = "Percent Country: " + song.getGenreSet().getCountry() + "%";
        songText = addTextShape(message, x, y);
        y += songText.getHeight();
        maxX = Math.max(maxX, x + songText.getWidth());
        message = "Suggested Playlist: " + (song.getPlaylistName().equals("") ? "No-Playlist" : song.getPlaylistName());
        songText = addTextShape(message, x, y);
        maxX = Math.max(maxX, x + songText.getWidth());
        drawNote(maxX + MARGIN + MUSIC_NOTE_SIZE, ogY, Color.BLACK);
    }

    /**
     * This method updates the text underneath the playlists on the window.
     */
    private void updatePlaylistText() {
        for (int i = 0; i < playlistShapes.length; i++) {
            final int textMargin = 3;
            Shape playlistShape = playlistShapes[i];
            int x = playlistShape.getX();
            int y = playlistShape.getY() + playlistShape.getHeight() + textMargin;
            Playlist playlist = playlistCalculator.getPlaylists()[i];
            String message = "Pop: " + playlist.getMinGenreSet().getPop() + "% - " + playlist.getMaxGenreSet().getPop() + "%";
            TextShape popText = addTextShape(message, x, y);
            popText.setX(x + (playlistShape.getWidth() / 2) - (popText.getWidth() / 2));
            y += popText.getHeight() + textMargin;
            message = "Rock: " + playlist.getMinGenreSet().getRock() + "% - " + playlist.getMaxGenreSet().getRock() + "%";
            TextShape rockText = addTextShape(message, x, y);
            rockText.setX(x + (playlistShape.getWidth() / 2) - (rockText.getWidth() / 2));
            y += rockText.getHeight() + textMargin;
            message = "County: " + playlist.getMinGenreSet().getCountry() + "% - " + playlist.getMaxGenreSet().getCountry() + "%";
            TextShape countryText = addTextShape(message, x, y);
            countryText.setX(x + (playlistShape.getWidth() / 2) - (countryText.getWidth() / 2));
            y += countryText.getHeight() + textMargin;
            message = "Songs in Mix: " + playlist.getNumberOfSongs() + "/" + playlist.getCapacity();
            TextShape songsInMixText = addTextShape(message, x, y);
            songsInMixText.setX(x + (playlistShape.getWidth() / 2) - (songsInMixText.getWidth() / 2));
        }
    }

    /**
     * Helper method to add a TextShape to the window (less overall code)
     */
    private TextShape addTextShape(String message, int x, int y) {
        if (message != null) {
            TextShape shape = new TextShape(x, y, message, Color.black);
            shape.setBackgroundColor(Color.white);
            window.addShape(shape);
            return shape;
        }
        return null;
    }


    /**
     * This method disables the buttons and is called when the queue is empty
     */
    private void endSimulation() {
        accept.disable();
        reject.disable();
        window.removeAllShapes();

        // Message
        TextShape simulationStatus = new TextShape(0, 0,
                "All Songs Processed!");
        int x = (window.getGraphPanelWidth() / 2) - (simulationStatus.getWidth()
                / 2);
        int y = (window.getGraphPanelHeight() / 2);
        simulationStatus.moveTo(x, y);
        simulationStatus.setBackgroundColor(Color.white);
        simulationStatus.setForegroundColor(Color.black);
        window.addShape(simulationStatus);
    }

}
