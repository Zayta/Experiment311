package exp.zayta.lorale.engine.movement.position_tracker;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.engine.entities.EntityType;
import exp.zayta.lorale.util.BiMap;


public class PositionTracker{
    private int mapDimension;
    private BiMap<Integer, Entity> globalTracker;
    public static int n;
    private BiMap<EntityType,BiMap<Integer, Entity>> entityTracker;


    public PositionTracker(int mapDimension){
        globalTracker = new BiMap<Integer, Entity>();
        entityTracker = new BiMap<EntityType, BiMap<Integer, Entity>>();
        EntityType types[] = EntityType.values();
        ////System.out.println("Contents of the enum are: ");
        //Iterating enum using the for loop
        for(EntityType type: types) {
            entityTracker.put(type,new BiMap<Integer, Entity>());
        }
        this.mapDimension = mapDimension;
    }
    public void init(int mapDimension){
        clear();
        this.mapDimension = mapDimension;
    }
    public int generateKey(float x, float y)
    {
        n =(int)( (mapDimension+1)/GameConfig.ENTITY_WIDTH);
        int i = (int)(y/GameConfig.ENTITY_HEIGHT),j = (int)(x/GameConfig.ENTITY_WIDTH)/*, n= mapWidth/maxObjWidth*/;
        return i*n+j;
    }
    public void removeEntity(Entity e){
        globalTracker.removeKey(e);
        for(EntityType entityType:EntityType.values()){
            entityTracker.get(entityType).removeKey(e);
        }
    }

    public void updateGlobalTracker(Entity entity, float x, float y) {

        globalTracker.removeKey(entity);
        int key=generateKey(x,y);

        globalTracker.put(key,entity);
    }
    public void updateEntityTracker(Entity entity, EntityType entityType, float x, float y) {

        entityTracker.get(entityType).removeKey(entity);
        int key=generateKey(x,y);

        entityTracker.get(entityType).put(key,entity);
    }


    public Entity getEntityAtPos(float x, float y){
        return globalTracker.get(generateKey(x,y));
    }
    public Entity getEntity(int key){
        return globalTracker.get(key);
    }
    public ArrayList<Entity> getEntitiesAtPos(float x, float y){
        ArrayList<Entity> ret = new ArrayList<Entity>();
        for(EntityType entityType: EntityType.values()) {
            Entity entity = entityTracker.get(entityType).get(generateKey(x, y));
            if(entity!=null)
                ret.add(entity);
        }
//        ////System.out.println("Entities at position "+x+","+y+" are "+ Arrays.toString(ret.toArray()));
        return ret;
    }
    public ArrayList<Entity> getSolidEntitiesAtPos(float x, float y){
        ArrayList<Entity> ret = new ArrayList<Entity>();
        for(EntityType entityType: EntityType.values()) {
            if(entityType!=EntityType.GOAL&&entityType!=EntityType.HOLE) {
                Entity entity = entityTracker.get(entityType).get(generateKey(x, y));
                if (entity != null)
                    ret.add(entity);
            }
        }
//        ////System.out.println("Entities at position "+x+","+y+" are "+ Arrays.toString(ret.toArray()));
        return ret;
    }

    public Entity getEntityAtPos(EntityType entityType,float x, float y){
        return entityTracker.get(entityType).get(generateKey(x,y));
    }

    public boolean isGoalPos(float x, float y){
        return entityTracker.get(EntityType.GOAL).get(generateKey(x,y))!=null;
    }

    public int getKeyForEntity(Entity entityTemplate){
        Integer key = globalTracker.getKey(entityTemplate);
        if(key==null)
            return -1;
        return key.intValue();
    }

    public Entity getEntityAtPos(Vector2 pos){
        return globalTracker.get(generateKey(pos.x,pos.y));
    }

    private void clear(){
        globalTracker.clear();
        for(EntityType entityType: entityTracker.keySet()){
            entityTracker.get(entityType).clear();
        }

    }
    public void reset(){
        clear();
    }
    @Override
    public String toString(){
        return globalTracker.toString();
    }


}

