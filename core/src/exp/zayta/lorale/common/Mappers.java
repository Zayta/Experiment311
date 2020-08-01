package exp.zayta.lorale.common;

import com.badlogic.ashley.core.ComponentMapper;

import exp.zayta.lorale.engine.entities.tags.PlayerTag;
import exp.zayta.lorale.engine.movement.movement_components.MovementComponent;
import exp.zayta.lorale.engine.movement.movement_components.Position;
import exp.zayta.lorale.engine.render.TextureComponent;
import exp.zayta.lorale.engine.render.animation.AnimationComponent;


public class Mappers {
    public static final ComponentMapper<PlayerTag> PLAYER = ComponentMapper.getFor(PlayerTag.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT = ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<AnimationComponent> ANIMATION = ComponentMapper.getFor(AnimationComponent.class);

    public static final ComponentMapper<Position> POSITION = ComponentMapper.getFor(Position.class);

    public static final ComponentMapper<TextureComponent> TEXTURE = ComponentMapper.getFor(TextureComponent.class);
    private Mappers(){}
}
