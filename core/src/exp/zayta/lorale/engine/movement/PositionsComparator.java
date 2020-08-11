package exp.zayta.lorale.engine.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

import exp.zayta.lorale.common.Mappers;


public class PositionsComparator implements Comparator<Entity>{
    public static PositionsComparator positionsComparator=new PositionsComparator();
//    //todo entities must have movment component.
//    private static HorizontalPositionComparator horizontalPositionComparator = new HorizontalPositionComparator();
//    private static class HorizontalPositionComparator implements Comparator<Entity> {
//        @Override
//        public int compare(Entity entityA, Entity entityB) {
//
//            float aPosX = Mappers.MOVEMENT.get(entityA).getTargetPosition().x;
//            float bPosX = Mappers.MOVEMENT.get(entityB).getTargetPosition().x;
//            Direction dir = Direction.none;
//            if(Mappers.MOVEMENT.get(entityA).getDirection()!=Direction.none)
//                dir = Mappers.MOVEMENT.get(entityA).getDirection();
//            else
//                dir=Mappers.MOVEMENT.get(entityB).getDirection();
//            if(dir== Direction.left)
//                return (int)(aPosX-bPosX);
//            else
//                return (int)(bPosX-aPosX);
//        }
//    }
//    public static VerticalPositionComparator verticalPositionComparator = new VerticalPositionComparator();
//    private static class VerticalPositionComparator implements Comparator<Entity> {
//        @Override
//        public int compare(Entity entityA, Entity entityB) {
//
//            float aPosY = Mappers.MOVEMENT.get(entityA).getTargetPosition().y;
//            float bPosY = Mappers.MOVEMENT.get(entityB).getTargetPosition().y;
//            Direction dir = Direction.none;
//            if(Mappers.MOVEMENT.get(entityA).getDirection()!=Direction.none)
//                dir = Mappers.MOVEMENT.get(entityA).getDirection();
//            else
//                dir=Mappers.MOVEMENT.get(entityB).getDirection();
//            if(dir== Direction.up)
//                return (int)(aPosY-bPosY);
//            else
//                return (int)(bPosY-aPosY);
//        }
//    }

    //todo optimize it so position comparator is flagged whenever there is change in direction

//    private class PositionComparator implements Comparator<Entity> {
//        private boolean changedDirection;
//
//    public void setChangedDirection(boolean changedDirection) {
//        this.changedDirection = changedDirection;
//    }
//
//    public boolean isChangedDirection() {
//        return changedDirection;
//    }

    private Direction direction = Direction.none;
        public void setDirection(Direction direction){
            this.direction = direction;
        }
        @Override
        public int compare(Entity entityA, Entity entityB) {
//            MovementComponent aMovement = Mappers.MOVEMENT.get(entityA);
//            MovementComponent bMovement = Mappers.MOVEMENT.get(entityB);
//              Vector2 aPos = aMovement.getTargetPosition();
//            Vector2 bPos = bMovement.getTargetPosition();
            Vector2 aPos = Mappers.POSITION.get(entityA).getPosition();
            Vector2 bPos = Mappers.POSITION.get(entityB).getPosition();
//
//            System.out.println("Direction "+direction);
            float ret = 0;
            switch (direction){
                case none:
                break;
                case up:
                    ret= (bPos.y-aPos.y);
                    break;
                case down:
                    ret= (aPos.y-bPos.y);
                    break;
                case left:
                    ret= (aPos.x-bPos.x);
                    break;
                case right:

                    ret= (bPos.x-aPos.x);
                    break;
            }
            return -1*(int)Math.signum(ret);//-1 cuz I accidentally switched up bPos and aPos when comparing
        }
//    }

}
