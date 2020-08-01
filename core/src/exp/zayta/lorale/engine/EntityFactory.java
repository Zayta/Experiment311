package exp.zayta.lorale.engine;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;


public class EntityFactory {
    private PooledEngine engine; private TextureAtlas textureAtlas;
    private float mapWidth,mapHeight;
    public EntityFactory(PooledEngine engine, TextureAtlas textureAtlas){
        this.engine = engine;
        this.textureAtlas = textureAtlas;
    }
    public void init(float mapWidth, float mapHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void addPlayer(float x, float y){

    }

    public void addNighter(){

    }


    private void addMovementComponents(){


    }




}
