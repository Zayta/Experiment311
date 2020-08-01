package exp.zayta.lorale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Logger;


public class UserData { /*monitors how much hp a player has left in the gameplay
 and stores levels unlocked and total points earned.*/
private static final Logger log = new Logger(UserData.class.getName(), Logger.DEBUG);

    private final String chapter = "chapter"; //current chapter. all previous chapters are completed

    private Preferences preferences;

    private static UserData userData = new UserData();
    public static UserData getInstance(){
        return userData;
    }
    private UserData() {
        preferences = Gdx.app.getPreferences("Preference");
        
    }
    
    public void complete(int chapterNum){
        //if the completed lvl has not been previously completed
        if(chapterNum>=preferences.getInteger(chapter)){
            unlockNxtChapter();
        }
    }
    
    private void unlockNxtChapter(){
//        if(chapter<total) if there is next lvl
        setChapter(preferences.getInteger(chapter)+1);
    }

    public int getChapter() {
        return preferences.getInteger(chapter);
    }

    public void setChapter(int chapter) {
        preferences.putInteger(this.chapter,chapter);

        preferences.flush();
    }
    
}
