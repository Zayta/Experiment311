package exp.zayta.lorale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;


public class GameConfig {
    public static float WIDTH = Gdx.graphics.getWidth()> Gdx.graphics.getHeight()?1024f:720f; // pixels
    public static float HEIGHT = Gdx.graphics.getWidth()> Gdx.graphics.getHeight()?720f:1024f; // pixels




    //for game world
    private static final int VIRTUAL_LONG = 5;
    private static final int VIRTUAL_SHORT =5;
    public static int VIRTUAL_WIDTH=VIRTUAL_LONG;
    public static int VIRTUAL_HEIGHT=VIRTUAL_SHORT;
    public static float VIRTUAL_CENTER_X = VIRTUAL_WIDTH/2f;
    public static float VIRTUAL_CENTER_Y = VIRTUAL_HEIGHT/2f;

    //tiled map
    public static final float TILE_SIZE = 16f;
    //entity config
    public static final float MOVING_SPEED = 0.09f;
    public static final float ENTITY_HEIGHT = 0.8f;
    public static final float ENTITY_WIDTH = 0.7f*ENTITY_HEIGHT;


    //hud
    public static float HUD_WIDTH = WIDTH; // world units
    public static float HUD_HEIGHT = HEIGHT; // world units
    public static final float PADDING = 15f;
    public static final int BTN_SIZE = 128;
    public static final int BTN_SMALL_SIZE = 88;

    public static final float JOYSTICK_RADIUS = 25;
    public static final Color DARK_TINT = new Color(0.7f,0.7f,0.7f,0.9f);



    public static void configScreenOrientation(int screenWidth,int screenHeight){

        if(screenWidth>screenHeight)
            configLandScape();
        else
            configPortrait();

    }
    private static void configLandScape(){
        //////log.debug("configLandscape");
        WIDTH = 1024f; // pixels
        HEIGHT = 720f; // pixels

        HUD_WIDTH = WIDTH; // world units
        HUD_HEIGHT = HEIGHT; // world units

//        VIRTUAL_WIDTH = VIRTUAL_LONG; // world units
//        VIRTUAL_HEIGHT = VIRTUAL_SHORT; // world units

//        VIRTUAL_CENTER_X = VIRTUAL_WIDTH / 2f; // world units
//        VIRTUAL_CENTER_Y = VIRTUAL_HEIGHT / 2f; // world units

    }


    private static void configPortrait(){
        //////log.debug("configPortrait");
        WIDTH = 720f; // pixels
        HEIGHT = 1024f; // pixels

        HUD_WIDTH = WIDTH; // world units
        HUD_HEIGHT = HEIGHT; // world units

//        VIRTUAL_HEIGHT = VIRTUAL_LONG; // world units
//        VIRTUAL_WIDTH = VIRTUAL_SHORT; // world units
//
//        VIRTUAL_CENTER_X = VIRTUAL_WIDTH / 2f; // world units
//        VIRTUAL_CENTER_Y = VIRTUAL_HEIGHT / 2f; // world units

     }



}
