package dailymixes;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class ProjectRunner
{
    // ----------------------------------------------------------
    /**
     * Create a new ProjectRunner object.
     */
    public ProjectRunner()
    {
    }


    // ----------------------------------------------------------
    /**
     * runs the project
     * 
     * @param args
     *            files being input
     * @throws FileNotFoundException
     *             if there is no file
     * @throws ParseException
     *             if it parses incorrectly
     * @throws DailyMixDataException
     *             data does not fit contraints
     */
    public static void main(String[] args)
        throws FileNotFoundException,
        ParseException,
        DailyMixDataException
    {
        if (args.length == 2)
        {
            PlaylistReader pr = new PlaylistReader(args[0], args[1]);
        }
        else
        {
            PlaylistReader pr =
                new PlaylistReader("input.txt", "playlists.txt");
        }
    }
}
