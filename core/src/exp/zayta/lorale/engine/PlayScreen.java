package exp.zayta.lorale.engine;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import exp.zayta.lorale.Game;
import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.assets.AssetDescriptors;
import exp.zayta.lorale.engine.EntityFactory;
import exp.zayta.lorale.engine.hud.Hud;
import exp.zayta.lorale.engine.hud.HudSystem;
import exp.zayta.lorale.engine.input.KeyboardController;
import exp.zayta.lorale.util.GdxUtils;
import exp.zayta.lorale.util.ViewportUtils;

public class PlayScreen extends ScreenAdapter {
    private Game game;
    private final AssetManager assetManager;
    // == attributes ==
    //render
    private Viewport viewport;
    private Viewport hudViewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    //input
    private Hud hud;
    private InputMultiplexer inputMultiplexer;
    //story
//    private DialogueFileParser dialogueFileParser;
    //game elements
    private PooledEngine engine;
    private EntityFactory entityFactory;

    public PlayScreen(Game game){
        //gameplay data
        this.game = game;
        this.assetManager = game.getAssetManager();
        this.batch = game.getBatch();
        engine = new PooledEngine();


        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.VIRTUAL_WIDTH, GameConfig.VIRTUAL_HEIGHT, camera);
        hudViewport = new ExtendViewport(GameConfig.HUD_WIDTH,GameConfig.HUD_HEIGHT);
        //game elements
        TextureAtlas textureAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);

        this.entityFactory = new EntityFactory(engine,textureAtlas);

        setInput();

    }
    private void setInput(){
        inputMultiplexer = new InputMultiplexer();


        inputMultiplexer.addProcessor(new KeyboardController());
        this.hud = new Hud(game,hudViewport);
        inputMultiplexer.addProcessor(hud);
//        inputMultiplexer.addProcessor(new GestureDetector(new SwipeController(controller)));

        Gdx.input.setInputProcessor(inputMultiplexer);
    }




    @Override
    public void show() {
        init();
    }
    public void init(){
//        hud.setShowSettings(false);
//        engine.update(0);
        engine.removeAllEntities();//need this call!
        Gdx.input.setInputProcessor(inputMultiplexer);

        addEntities();//entitiies sb added b4 systems

        addSystems();
    }
    private void addEntities(){

    }

    /**
     * Notes: when adding systems, specify priority.
     * Rmb order of entities processed may also matter so sort entities in positional order before processing their movements
     */
    private void addSystems(){
        engine.addSystem(new HudSystem(1000,hud));
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

//        hud.act(delta); //act the Hud
        engine.update(delta);

    }

    @Override
    public void resize(int width, int height) {
        GameConfig.configScreenOrientation(width,height);
        viewport.setWorldSize(GameConfig.VIRTUAL_WIDTH,GameConfig.VIRTUAL_HEIGHT);
        viewport.update(width, height,true);


        hudViewport.setWorldSize(GameConfig.HUD_WIDTH,GameConfig.HUD_HEIGHT);
        hudViewport.update(width, height,true);
        hud.resize(width,height);

        ViewportUtils.debugPixelsPerUnit(viewport);
        ViewportUtils.debugPixelsPerUnit(hud.getViewport());
    }

    @Override
    public void hide() {
//        dispose();
//        controller.hide();
    }

    @Override
    public void dispose() {
        hud.dispose();
//        renderer.dispose();
        batch.dispose();
    }


    /****For screen transition*****/
    public void progress(){
        reset();
//        game.complete(curLvl);

    }
    public void fail(){
        restart();
    }
    public void restart(){
        reset();
        init();
    }


    private void reset(){
        engine.removeAllEntities();

    }

}
