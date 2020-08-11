package exp.zayta.lorale.engine.map.tiled_map.tile_collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import exp.zayta.lorale.Game;
import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.engine.movement.Direction;

public class TiledMapCollisionDetector {

    private static final Logger log = new Logger(TiledMapCollisionDetector.class.getName(), Logger.DEBUG);

    private TiledMapTileLayer collisionLayer;
    private float increment,scale= GameConfig.TILE_SIZE;
    public TiledMapCollisionDetector(TiledMapTileLayer collisionLayer){
        this.collisionLayer = collisionLayer;
        log.debug("Collision layer is "+collisionLayer);
        log.debug("Collisition layer tile height is "+collisionLayer.getTileHeight()+"< width is "+collisionLayer.getTileWidth());
    }
    

    public TiledMapTile getCollidedTile(float xf, float yf, float widthf, float heightf, Direction direction){
                float x = xf* scale,
                y=yf*scale,
                width=widthf*scale,
                height=heightf*scale;//scale all coordinates to tile coordinates
        increment = collisionLayer.getTileHeight();
        increment = height < increment ? height / 2 : increment / 2;


        TiledMapTile tiledMapTile = null;
        switch (direction){
            case up:
                // calculate the increment for step in #collidesBottom() and #collidesTop()
//                increment = collisionLayer.getTileHeight();
//                increment = height < increment ? height / 2 : increment / 2;

                tiledMapTile= collidesTop(x,y,width,height);
                break;
            case down:
                // calculate the increment for step in #collidesBottom() and #collidesTop()
//                increment = collisionLayer.getTileHeight();
//                increment = height < increment ? height / 2 : increment / 2;

                tiledMapTile= collidesBottom(x,y,width,height);
                break;
            case left:
                // calculate the increment for step in #collidesLeft() and #collidesRight()
//                increment = collisionLayer.getTileWidth();
//                increment = width < increment ? width / 2 : increment / 2;
                tiledMapTile= collidesLeft(x,y,width,height);
                break;
            case right:
                // calculate the increment for step in #collidesLeft() and #collidesRight()
//                increment = collisionLayer.getTileWidth();
//                increment = width < increment ? width / 2 : increment / 2;
                tiledMapTile= collidesRight(x,y,width,height);
                break;
        }
        return tiledMapTile;
    }
    private TiledMapTile collidesRight(float x, float y, float width, float height) {
        for(float step = 0; step <= height; step += increment){
            TiledMapTile tiledMapTile = isCellBlocked(x + width, y + step);
            if(tiledMapTile!=null)
                return tiledMapTile;
        }
        return null;
    }

    private TiledMapTile collidesLeft(float x, float y, float width, float height) {

        for(float step = 0; step <= height; step += increment){
            TiledMapTile tiledMapTile = isCellBlocked(x, y + step);
            if(tiledMapTile!=null)
                return tiledMapTile;
        }
        return null;
    }

    private TiledMapTile collidesTop(float x, float y, float width, float height) {
        for(float step = 0; step <= width; step += increment){

            TiledMapTile tiledMapTile = isCellBlocked(x+step, y + height);
            if(tiledMapTile!=null)
                return tiledMapTile;
        }
        return null;

    }

    private TiledMapTile collidesBottom(float x, float y, float width, float height) {
        for(float step = 0; step <= width; step += increment){

            TiledMapTile tiledMapTile = isCellBlocked(x+step, y);
            if(tiledMapTile!=null)
                return tiledMapTile;
        }
        return null;
    }

    private TiledMapTile isCellBlocked(float x, float y){
        int cellX = (int) (x / collisionLayer.getTileWidth());
        int cellY=(int) (y / collisionLayer.getTileHeight());
        TiledMapTileLayer.Cell cell = collisionLayer.getCell(cellX, cellY);
        if(cell==null){
            return null;
        }

        TiledMapTile tiledMapTile = cell.getTile();
        if(tiledMapTile==null)
            return null;
        MapObjects objects = tiledMapTile.getObjects();
        if(objects.getCount()==0)
            return tiledMapTile;
        //rectangular bounds collision
        //translate position to tile coordinates
        float xi = x-cellX*collisionLayer.getTileWidth(),
                yi = y-cellY*collisionLayer.getTileHeight();

        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            log.debug("Object rectangle is "+rectangle);
            log.debug("Player pos is "+xi+","+yi);
            if (rectangle.contains(xi,yi)){
                // collision happened
                log.debug("Collision w obj when walking dir ");
                return tiledMapTile;
            }
        }

        return null;
    }

}
