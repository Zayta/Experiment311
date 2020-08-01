package exp.zayta.lorale;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Logger;

import exp.zayta.lorale.assets.AssetDescriptors;


class MainScreen extends ScreenBase {

    private final Logger log = new Logger(MainScreen.class.getName(), Logger.DEBUG);

    private Table table;
    private UserData userData;

    MainScreen(Game game) {
        super(game);
        this.userData = game.getUserData();
        table = new Table();
    }


    @Override
    protected Actor createUi() {
        table.clearChildren();
        TextureAtlas textureAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);

        setBackground(textureAtlas);

        Label label = new Label("Experiment "+userData.getChapter(),
                new Label.LabelStyle(assetManager.get(AssetDescriptors.FONT),Color.WHITE));
        label.setFontScale(2);

        table.add(label).top().left();
        table.row();

        table.add(scene(textureAtlas));
        table.row();
        table.pad(20);
        table.add(buttonTable());
        table.center();
        table.setFillParent(true);
        table.pack();

        return table;
    }

    private void setBackground(TextureAtlas textureAtlas){

    }

    private ImageButton scene(TextureAtlas textureAtlas){
//        TextureRegion scene = textureAtlas.findRegion(RegionNames.UI_TOUCHPAD_BCKGRND);
//        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(scene);
//        ImageButton imageButton = new ImageButton(textureRegionDrawable){};
//        imageButton.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                play();
//            }
//        });
//        imageButton.top();
//        return imageButton;
        return null;
    }


    private Table buttonTable(){
        Skin uiskin = assetManager.get(AssetDescriptors.UI_SKIN);

        // play button
        TextButton playButton = new TextButton("PLAY", uiskin);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play();
            }
        });
//
        // story button
//        TextButton storyButton = new TextButton("STORY", uiskin);
//        storyButton.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                story();
//            }
//        });

        // quit button
        TextButton quitButton = new TextButton("QUIT", uiskin);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quit();
            }
        });

        // setup table
        Table buttonTable = new Table(uiskin);
        int numButtons = 4;

        buttonTable.add(playButton).width(GameConfig.WIDTH/numButtons);
//        buttonTable.add(storyButton).width(GameConfig.WIDTH/numButtons);
        buttonTable.add(quitButton).width(GameConfig.WIDTH/numButtons);


        buttonTable.bottom();
        return buttonTable;
    }

    private void play() {
        game.setPlayScreen();
    }



    private void quit() {
        Gdx.app.exit();
    }



}