package exp.zayta.lorale.engine.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.Logger;

import exp.zayta.lorale.engine.PlayerController;
import exp.zayta.lorale.engine.hud.Hud;


public class InputSystem extends EntitySystem {
    private static final Logger log = new Logger(InputSystem.class.getName(),Logger.DEBUG);
    private InputMultiplexer inputMultiplexer;
    private Hud hud;
    public InputSystem(int priority, Hud hud, PlayerController playerController){
        super(priority);
        inputMultiplexer = new InputMultiplexer();


        inputMultiplexer.addProcessor(new KeyboardController(playerController));
        this.hud = hud;
        inputMultiplexer.addProcessor(hud);
//        inputMultiplexer.addProcessor(new GestureDetector(new SwipeController(controller)));

        Gdx.input.setInputProcessor(inputMultiplexer);
    }



    @Override
    public void update(float deltaTime) {

        if(hud!=null) {
            hud.act(deltaTime);
            hud.getViewport().apply();
            hud.draw();
        }
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
        if(hud!=null)
            hud.dispose();
    }




}

