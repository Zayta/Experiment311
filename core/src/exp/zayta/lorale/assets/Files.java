package exp.zayta.lorale.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;


public class Files {
    public static FileHandle story = Gdx.files.internal("story/story.txt");
    public static FileHandle inkStory= Gdx.files.internal("experiments/MyFirstInkStory.json");;
    private static String puzzlePath = "gameplay/";
    public static FileHandle[] puzzles = new FileHandle[]
            {Gdx.files.internal(puzzlePath+"AC_Easy.txt"),
                    Gdx.files.internal(puzzlePath+"simply_sokoban_lvls.txt"),
             Gdx.files.internal(puzzlePath+"AC_Selected Sokoban lvls.txt"),
            Gdx.files.internal(puzzlePath+"Yanse.txt")};
}
