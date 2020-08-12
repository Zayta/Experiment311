package exp.zayta.lorale.engine;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import exp.zayta.lorale.GameConfig;
import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.entities.Characters;
import exp.zayta.lorale.engine.entities.EntityType;
import exp.zayta.lorale.engine.entities.tags.CharacterTag;
import exp.zayta.lorale.engine.entities.tags.PlayerTag;
import exp.zayta.lorale.engine.movement.Direction;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.movement.movement_components.Position;
import exp.zayta.lorale.engine.movement.position_tracker.PositionTrackerComponent;
import exp.zayta.lorale.engine.movement.world_wrap.WorldWrapComponent;
import exp.zayta.lorale.engine.render.TextureComponent;
import exp.zayta.lorale.engine.render.animation.AnimationComponent;


public class EntityFactory {
    private PooledEngine engine; private TextureAtlas textureAtlas;
    private float mapWidth,mapHeight;
    private Characters characters;

    public EntityFactory(PooledEngine engine, TextureAtlas textureAtlas){
        this.engine = engine;
        this.textureAtlas = textureAtlas;
        this.characters = new Characters(textureAtlas);
    }
    public void init(float mapWidth, float mapHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void addPlayer(Characters.CharacterName characterName, float x, float y){
        Entity player = engine.createEntity();
        PlayerTag playerTag = engine.createComponent(PlayerTag.class);
        player.add(playerTag);

        CharacterTag characterTag = engine.createComponent(CharacterTag.class);
        characterTag.setCharacterName(characterName);
        player.add(characterTag);

        addMovementComponents(player,EntityType.CHARACTER, x,y);



        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        textureComponent.init(characters.getTexture(characterName).get(0));
        player.add(textureComponent);


        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        animationComponent.init(characters.getTexture(characterName));
        player.add(animationComponent);

        ////System.out.println("Player is at position "+Mappers.POSITION.get(player).getPosition().toString());
        engine.addEntity(player);
    }

    public void addNighter(Characters.CharacterName characterName, float x, float y){
        Entity entity = engine.createEntity();

        CharacterTag characterTag = engine.createComponent(CharacterTag.class);
        characterTag.setCharacterName(characterName);
        entity.add(characterTag);

        addMovementComponents(entity,EntityType.CHARACTER, x,y);
        Mappers.MOVEMENT.get(entity).setDirection(Direction.down);



        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        textureComponent.init(characters.getTexture(characterName).get(0));
        entity.add(textureComponent);


        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        animationComponent.init(characters.getTexture(characterName));
        entity.add(animationComponent);

        ////System.out.println("Player is at position "+Mappers.POSITION.get(entity).getPosition().toString());
        engine.addEntity(entity);
    }



    private void addMovementComponents(Entity entity,EntityType entityType, float x, float y){

        Position position = engine.createComponent(Position.class);
        position.init(x,y);
        entity.add(position);

        PositionTrackerComponent positionTrackerComponent = engine.createComponent(PositionTrackerComponent.class);
        positionTrackerComponent.init(entityType);
        entity.add(positionTrackerComponent);

        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
        movementComponent.init(new Vector2(position.getX(),position.getY()));
        movementComponent.setDirection(Direction.none);
        entity.add(movementComponent);

        WorldWrapComponent worldWrapComponent = engine.createComponent(WorldWrapComponent.class);
        worldWrapComponent.setBoundsOfMovement(0,0,mapWidth,mapHeight);
        entity.add(worldWrapComponent);

    }




}
