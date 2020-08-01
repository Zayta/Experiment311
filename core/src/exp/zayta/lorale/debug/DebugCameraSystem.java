package exp.zayta.lorale.debug;


import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Logger;

public class DebugCameraSystem extends EntitySystem {

    private static final Logger log = new Logger(DebugCameraSystem.class.getName(), Logger.DEBUG);

    private static final DebugCameraController DEBUG_CAMERA_CONTROLLER = new DebugCameraController();

    //attributes
    private final OrthographicCamera orthographicCamera;

    //constructors
    public  DebugCameraSystem(int priority, OrthographicCamera orthographicCamera, float startX, float startY)
    {
        super(priority);
        this.orthographicCamera = orthographicCamera;
        DEBUG_CAMERA_CONTROLLER.setStartPosition(startX,startY);
    }

    @Override
    public void update(float deltaTime) {
        DEBUG_CAMERA_CONTROLLER.handleDebugInput(deltaTime);
        DEBUG_CAMERA_CONTROLLER.applyTo(orthographicCamera);
    }

}
