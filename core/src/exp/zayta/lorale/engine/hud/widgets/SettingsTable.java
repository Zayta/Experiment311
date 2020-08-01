package exp.zayta.lorale.engine.hud.widgets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import exp.zayta.lorale.Game;
import exp.zayta.lorale.assets.AssetDescriptors;
import exp.zayta.lorale.assets.RegionNames;


public class SettingsTable extends Table {
    private Skin skin;
    private Game game;
//    private Table settingsTable;
    private boolean showSettings;

//    private Stack controlsStack;
    private Table settingsTable;

    private TextureAtlas textureAtlas;

    public SettingsTable(Game game, Skin skin){
        this.game = game;
        this.skin = skin;
        this.textureAtlas = game.getAssetManager().get(AssetDescriptors.GAMEPLAY);;



//        controlsStack = new Stack();
        this.settingsTable = settingsTable();
        this.add(settingsTable);
        setShowSettings(false);

//        init();
    }
    public Table getTrigger(){

        Table settingsBtnWrapper = new Table();
        settingsBtnWrapper.add(settingsBtns(100)).expand().left().top();
        return settingsBtnWrapper;
    }
//    private void init(){
//        this.controlsStack.clearChildren();
//
//        controlsStack.add(settingsBtnWrapper);
//        controlsStack.add(settingsTable);
////        this.setShowSettings(false);
//        this.add(controlsStack).expand().fillY().width(500).pad(10);
////        setShowSettings(false);
//    }

    private Table settingsTable(){
        int btnSize=600;
        Table table = new Table(skin);

        table.add(new Label("Settings",skin)).expandY().top().row();
        table.setBackground(RegionNames.WINDOW);

        table.add(exitBtn()).minHeight(100).width(btnSize).expandY().center().row();
        ImageTextButton cancelBtn = new ImageTextButton("Cancel", skin);

        cancelBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showSettings = false;
                settingsTable.setVisible(showSettings);

                System.out.println("Cancel was clicked");
            }
        });
        table.add(cancelBtn).minHeight(100).width(btnSize).expandY().bottom();
        return table;
    }
    private Table settingsBtns(int smallBtnHeight){
        Table verticalGroup = new Table();
        verticalGroup.add(settingsBtn()).left().top().size(smallBtnHeight).row();

        return verticalGroup;
    }

    private Button exitBtn(){
        ImageTextButton exitButton = new ImageTextButton("Exit",skin);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setMainScreen();
            }
        });
        return exitButton;
    }
    public void setShowSettings(boolean b) {
        this.showSettings = b;
        settingsTable.setVisible(showSettings);
//        this.add(controlsStack).expand().fill();
//        this.pack();
    }
    private Button settingsBtn(){

        TextButton settingsButton = new TextButton("Settings",skin);
//        TextButton restartButton = new TextButton("Restart", skin);
        settingsButton.align(Align.left);

        settingsButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setShowSettings(!showSettings);
            }
        });
        return settingsButton;
    }

}
