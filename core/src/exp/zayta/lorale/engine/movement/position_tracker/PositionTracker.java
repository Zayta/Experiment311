package exp.zayta.lorale.engine.movement.position_tracker;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.engine.entities.EntityType;
import exp.zayta.lorale.util.BiMap;


public class PositionTracker{
    private int mapWidth;
    //key: integer position area, value: list of entities in that position area
    private BiMap<Integer, List<Entity>> globalTracker;
    private BiMap<Entity,Integer> entityTracker;//key: entity, value: its position area
    public static int n;


    public PositionTracker(int mapWidth){
        globalTracker = new BiMap<Integer, List<Entity>>();
        entityTracker = new BiMap<>();
        this.mapWidth = mapWidth;
    }
    public void init(int mapWidth){
        clear();
        this.mapWidth = mapWidth;
    }
    public int generateKey(float x, float y)
    {

        n =(int)( (mapWidth+1)/GameConfig.ENTITY_WIDTH);
        int i = (int)(y/GameConfig.ENTITY_HEIGHT),
                j = (int)(x/GameConfig.ENTITY_WIDTH)/*, n= mapWidth/maxObjWidth*/;
        return i*n+j;
    }


    public void updateEntityTracker(Entity entity, float x, float y){
        entityTracker.put(entity,generateKey(x,y));
    }
    public void updateGlobalTracker(Entity entity, float x, float y) {
        removeEntity(entity);
        int key=generateKey(x,y);
        insertEntity(key,entity);
    }
    private void removeEntity(Entity entity){
        int key = getKeyForEntity(entity);
        List<Entity> entities = globalTracker.get(key);
        if(entities!=null) {
            entities.remove(entity);//remove entity from list
            if (entities.isEmpty()) {
                globalTracker.remove(key);
            }
        }

    }
    private void insertEntity(int key,Entity entity){

        List<Entity> entities = globalTracker.get(key);
        if(entities==null){
            globalTracker.put(key,new ArrayList<Entity>());
            entities = globalTracker.get(key);
        }
        entities.add(entity);

    }
    public List<Entity> getEntitiesAtPos(float x, float y){

        return globalTracker.get(generateKey(x,y));
    }
    public List<Entity> getEntity(int key){
        return globalTracker.get(key);
    }

    public int getKeyForEntity(Entity entity){

        Integer key = entityTracker.get(entity);
        if(key==null)
            return -1;
        return key.intValue();
    }

    public List<Entity> getEntitiesAtPos(Vector2 pos){
        return globalTracker.get(generateKey(pos.x,pos.y));
    }

    private void clear(){
        globalTracker.clear();
        entityTracker.clear();
    }
    public void reset(){
        clear();
    }
    @Override
    public String toString(){
        return globalTracker.toString();
    }


}

