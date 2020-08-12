package exp.zayta.lorale.engine;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import exp.zayta.lorale.Game;
import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.debug.DebugPlayerBoundsSystem;
import exp.zayta.lorale.assets.AssetDescriptors;
import exp.zayta.lorale.debug.DebugCameraSystem;
import exp.zayta.lorale.engine.entities.Characters;
import exp.zayta.lorale.engine.game_systems.character.CharacterBlockSystem;
import exp.zayta.lorale.engine.hud.Hud;
import exp.zayta.lorale.engine.hud.HudSystem;
import exp.zayta.lorale.engine.input.InputSystem;
import exp.zayta.lorale.engine.map.MapMaker;
import exp.zayta.lorale.engine.map.tiled_map.TiledMapCollisionSystem;
import exp.zayta.lorale.engine.map.tiled_map.TiledMapRenderSystem;
import exp.zayta.lorale.engine.movement.Direction;
import exp.zayta.lorale.engine.movement.MovementSystem;
import exp.zayta.lorale.engine.movement.PositionsComparatorSystem;
import exp.zayta.lorale.engine.movement.position_tracker.DebugPositionTrackerSystem;
import exp.zayta.lorale.engine.movement.position_tracker.PositionTracker;
import exp.zayta.lorale.engine.movement.position_tracker.PositionTrackerSystem;
import exp.zayta.lorale.engine.movement.world_wrap.WorldWrapPauseSystem;
import exp.zayta.lorale.engine.render.CameraUpdateSystem;
import exp.zayta.lorale.engine.render.RenderSystem;
import exp.zayta.lorale.engine.render.animation.AnimationSystem;
import exp.zayta.lorale.util.GdxUtils;
import exp.zayta.lorale.util.ViewportUtils;

public class PlayScreen extends ScreenAdapter {
    private static final Logger log = new Logger(PlayScreen.class.getName(), Logger.DEBUG);

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
    private PlayerController playerController;
    //story
//    private DialogueFileParser dialogueFileParser;

    //game elements
    private PooledEngine engine;
    private EntityFactory entityFactory;
    public static final String PLAYER_SPAWN = "player_spawn";
    //map
    private MapMaker mapMaker;
    //position
    private PositionTracker positionTracker;

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
        this.mapMaker = new MapMaker(assetManager);

        positionTracker = new PositionTracker(Math.max(GameConfig.VIRTUAL_WIDTH,GameConfig.VIRTUAL_HEIGHT));

        this.playerController = new PlayerController(engine,this,positionTracker);
        this.hud = new Hud(game,hudViewport);
    }





    @Override
    public void show() {
        init();
    }
    public void init(){
//        hud.setShowSettings(false);
//        engine.update(0);
        engine.removeAllEntities();//need this call!

        mapMaker.init(MapMaker.MapName.memLab);
        entityFactory.init(mapMaker.getMapWidth(),mapMaker.getMapHeight());
//        positionTracker.init(Math.max(Math.max((int)Math.ceil(mapMaker.getMapWidth()),(int)Math.ceil(mapMaker.getMapHeight())),
//                Math.max(GameConfig.VIRTUAL_WIDTH, GameConfig.VIRTUAL_HEIGHT)));
        positionTracker.init((int)mapMaker.getMapWidth());

        addEntities();//entitiies sb added b4 systems

        addSystems();
    }
    private void addEntities(){
        MapLayer mapLayer = mapMaker.getPositionLayer();
        if(mapLayer!=null) {
            MapObjects objects = mapLayer.getObjects();
            for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
                if(rectangleObject.isVisible()) {//if point is visible in the tiled map, entity should be added

                    Rectangle rectangle = rectangleObject.getRectangle();
                    if (PLAYER_SPAWN.equals(rectangleObject.getName())) {
                        entityFactory.addPlayer(Characters.CharacterName.LORALE, GameConfig.tiledToUnitCoord(rectangle.x), GameConfig.tiledToUnitCoord(rectangle.y));
                    }
                    for (Characters.CharacterName characterName : Characters.CharacterName.values()) {
                        if (characterName.toString().equalsIgnoreCase(rectangleObject.getName())) {
                            entityFactory.addNighter(characterName, GameConfig.tiledToUnitCoord(rectangle.x), GameConfig.tiledToUnitCoord(rectangle.y));
                        }
                    }
                }
            }
        }
        else {
            entityFactory.addPlayer(Characters.CharacterName.LORALE,0,0);
        }
    }

    /**
     * Notes: when adding systems, specify priority.
     * Rmb order of entities processed may also matter so sort entities in positional order before processing their movements
     */
    private void addSystems(){
        engine.addSystem(new InputSystem(3,hud,playerController));
        engine.addSystem(new PositionsComparatorSystem(2));
        engine.addSystem(new PositionTrackerSystem(10,positionTracker));//updates the tracker
        engine.addSystem(new MovementSystem(70));//moves entity to target position n set movement to none. should be last
        engine.addSystem(new WorldWrapPauseSystem(30));



        engine.addSystem(new CharacterBlockSystem(40,positionTracker));


        engine.addSystem(new CameraUpdateSystem(10,viewport));

        engine.addSystem(new RenderSystem(120,viewport,batch));

        engine.addSystem(new HudSystem(1000,hud));

        addTiledMapSystems();
        addAnimationSystems();
        addDebugSystems();
    }
    private void addTiledMapSystems(){
        engine.addSystem(new TiledMapCollisionSystem(30,(TiledMapTileLayer) mapMaker.getCollisionLayer()));
//        engine.addSystem(new MapBlockPauseSystem(31,(TiledMapTileLayer) tiledMap.getLayers().get(1)));

        engine.addSystem(new TiledMapRenderSystem(100,mapMaker.getTiledMap(),viewport));
    }
    private void addAnimationSystems(){
        engine.addSystem(new AnimationSystem(15));
    }

    private void addDebugSystems(){
//        engine.addSystem(new DebugPlayerBoundsSystem(200,viewport));
        engine.addSystem(new DebugCameraSystem(200,camera, GameConfig.VIRTUAL_CENTER_X, GameConfig.VIRTUAL_CENTER_Y));
        engine.addSystem(new DebugPositionTrackerSystem(100,positionTracker,viewport,batch));
    }
    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//            //log.debug("LEFT KEY PRESSED");
//            playerController.movePlayer(Direction.left);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//            //log.debug("RIGHT KEY PRESSED");
//            playerController.movePlayer(Direction.right);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
//            //log.debug("LEFT KEY PRESSED");
//            playerController.movePlayer(Direction.up);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//            //log.debug("RIGHT KEY PRESSED");
//            playerController.movePlayer(Direction.down);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.P)){
//            playerController.debugPlayerPosition();
//        }
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
