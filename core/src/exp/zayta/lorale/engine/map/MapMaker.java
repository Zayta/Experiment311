package exp.zayta.lorale.engine.map;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Logger;

import java.util.Hashtable;

import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.assets.AssetDescriptors;

public class MapMaker {
    private Rectangle mapBounds;
    private static final Logger log = new Logger(MapMaker.class.getName(),Logger.DEBUG);

    public static final String initPositionsLayer = "Positions";
    public static final String collisionLayer = "Collision";

    private AssetManager assetManager;
    private Hashtable<MapName,TiledMap> tiledMaps;
    private TiledMap tiledMap;
    public enum MapName{
        memLab
    }
    
    public MapMaker(AssetManager assetManager){
        this.assetManager = assetManager;
        tiledMaps = new Hashtable<MapName, TiledMap>();
        mapBounds=new Rectangle();
        tiledMaps.put(MapName.memLab,assetManager.get(AssetDescriptors.MAP_LAB));
    }

    public TiledMap init(MapName mapName) {
        this.tiledMap = tiledMaps.get(mapName);
        setMapBounds(tiledMap);
        return tiledMap;
    }

    //layers
    public MapLayer getCollisionLayer(){
        return tiledMap.getLayers().get(collisionLayer);
    }
    public MapLayer getPositionLayer(){
        return tiledMap.getLayers().get(initPositionsLayer);
    }
    public TiledMap getTiledMap(){
        return tiledMap;
    }


    //==== map bounds ==//



    private void initMapBounds(float left, float bottom, float width, float height) {
        mapBounds.set(left,bottom,width,height);
    }


    private void setMapBounds(TiledMap tiledMap){
        MapProperties mapProperties = tiledMap.getProperties();
        mapBounds.width=(mapProperties.get("width", Integer.class))-GameConfig.ENTITY_WIDTH;
        mapBounds.height=(mapProperties.get("height", Integer.class))-GameConfig.ENTITY_HEIGHT;
    }

    public float getMapWidth() {
        return mapBounds.width;
    }

    public float getMapHeight() {
        return mapBounds.height;
    }
    public Rectangle getMapBounds(){
        return mapBounds;
    }



}
