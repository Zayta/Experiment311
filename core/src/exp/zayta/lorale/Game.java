package exp.zayta.lorale;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;

import exp.zayta.lorale.engine.PlayScreen;


public class Game extends com.badlogic.gdx.Game {

	private static final Logger log = new Logger(Game.class.getName(),Logger.DEBUG);
	private UserData userData;
	private AssetManager assetManager;//dont make static
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;


	private MainScreen mainScreen;
	private PlayScreen playScreen;


	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_NONE);
		userData = UserData.getInstance();

		assetManager = new AssetManager();
		assetManager.getLogger().setLevel(Logger.DEBUG);

		LoadingScreen loadingScreen = new LoadingScreen(this);

		setScreen(loadingScreen);


		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		//creates screens after assets have been loaded
		this.playScreen = new PlayScreen(this);
		this.mainScreen = new MainScreen(this);

	}


	@Override
	public void dispose() {
		assetManager.dispose();
		batch.dispose();
		shapeRenderer.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	public void setPlayScreen(){
		setScreen(playScreen);
	}

	public void setMainScreen(){
		setScreen(mainScreen);
	}

	public UserData getUserData(){
		return userData;
	}


}

