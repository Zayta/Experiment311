package exp.zayta.lorale.common;

import com.badlogic.ashley.core.ComponentMapper;

import exp.zayta.lorale.engine.entities.tags.PlayerTag;
import exp.zayta.lorale.engine.game_systems.character.follower.FollowerTag;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.movement.movement_components.Position;
import exp.zayta.lorale.engine.movement.position_tracker.PositionTrackerComponent;
import exp.zayta.lorale.engine.movement.world_wrap.WorldWrapComponent;
import exp.zayta.lorale.engine.render.TextureComponent;
import exp.zayta.lorale.engine.render.animation.AnimationComponent;


public class Mappers {
    //fundamentals
    public static final ComponentMapper<PlayerTag> PLAYER = ComponentMapper.getFor(PlayerTag.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT = ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<AnimationComponent> ANIMATION = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<Position> POSITION = ComponentMapper.getFor(Position.class);
    public static final ComponentMapper<TextureComponent> TEXTURE = ComponentMapper.getFor(TextureComponent.class);

    //other movements
    public static final ComponentMapper<PositionTrackerComponent> POSITION_TRACKER = ComponentMapper.getFor(PositionTrackerComponent.class);
    public static final ComponentMapper<WorldWrapComponent> WORLD_WRAP = ComponentMapper.getFor(WorldWrapComponent.class);

    //additionals
    public static final ComponentMapper<FollowerTag> FOLLOWER = ComponentMapper.getFor(FollowerTag.class);

    private Mappers(){}
}
