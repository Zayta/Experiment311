package exp.zayta.lorale.engine.hud;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;

import exp.zayta.lorale.Game;
import exp.zayta.lorale.UserData;
import exp.zayta.lorale.assets.AssetDescriptors;
import exp.zayta.lorale.engine.hud.widgets.SettingsTable;


public class Hud extends Stage{
    private static final Logger log = new Logger(Hud.class.getName(), Logger.DEBUG);
    

    BitmapFont font;
    Skin skin;
    Table table;
    TextureAtlas textureAtlas;

    final Game game;
    final UserData userData;


    /**
     * Basic stage with settings button
     * @param game
     * @param viewport
     */

    public Hud(Game game, Viewport viewport) {
        super(viewport, game.getBatch());
        this.game = game; this.userData = game.getUserData();
        AssetManager assetManager = game.getAssetManager();
        this.textureAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);
        //get font for table
        this.font = assetManager.get(AssetDescriptors.FONT);
        font.setColor(Color.BLACK);

        this.skin = assetManager.get(AssetDescriptors.UI_SKIN);


        table = new Table(skin);
        table.setFillParent(true);
        this.addActor(table);
//        table.setDebug(true);
        init();
    }
    /**
     * Initializes hud
     */
    public void init(){
        table.clearChildren();
        Stack stack = new Stack();
        stack.add(addSettings());
        table.add(stack).expand().fill();
        table.pack();
    }



    public void resize(int width, int height) {
//        table.clearChildren();
//        table.invalidateHierarchy();
        //System.out.println("Hud is resized");
//        init(width,height);
    }

    private Actor addSettings(){
        Stack controlsStack = new Stack();

        SettingsTable settingsTable = new SettingsTable(game,game.getAssetManager().get(AssetDescriptors.UI_SKIN));
        controlsStack.add(settingsTable.getTrigger());
        controlsStack.add(settingsTable);

//        table.add(controlsStack).fillY().width(500).row();
        return controlsStack;
    }


}