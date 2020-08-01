package exp.zayta.lorale.engine.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;


public class KeyboardController extends InputAdapter {//controls keyboard input controls in the gameplay

    public KeyboardController(){
    }
    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        return true;
    }



}
