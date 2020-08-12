package exp.zayta.lorale.engine.render;

import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

import exp.zayta.lorale.common.Mappers;
import exp.zayta.lorale.engine.movement.movement_components.Position;

//sorts entities so that the top-most entities are drawn first
public class TopDownComparator implements Comparator<Entity> {
    public static final TopDownComparator topDownComparator = new TopDownComparator();

    @Override
    public int compare(Entity e1, Entity e2) {
        Position apos = (Mappers.POSITION.get(e1));
        Position bpos = Mappers.POSITION.get(e2);
        if(apos!=null&&bpos!=null){
            return (int)Math.signum(bpos.getY()-apos.getY());
        }
        return 0;
    }
}
