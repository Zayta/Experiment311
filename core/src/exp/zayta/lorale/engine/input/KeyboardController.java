package exp.zayta.lorale.engine.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Logger;

import exp.zayta.lorale.engine.PlayerController;
import exp.zayta.lorale.engine.movement.Direction;


public class KeyboardController extends InputAdapter {//controls keyboard input controls in the gameplay
    private static final Logger log = new Logger(KeyboardController.class.getName(), Logger.DEBUG);

    private exp.zayta.lorale.engine.PlayerController playerController;
    public KeyboardController(PlayerController playerController){
        this.playerController = playerController;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        log.debug("Key is pressed :"+keycode);
        switch (keycode){
            case Input.Keys.UP:
                playerController.movePlayer(Direction.up);
                break;
            case Input.Keys.DOWN:
                playerController.movePlayer(Direction.down);
                break;
            case Input.Keys.LEFT:
                playerController.movePlayer(Direction.left);
                break;
            case Input.Keys.RIGHT:
                playerController.movePlayer(Direction.right);
                break;

        }
        return true;
    }




}
