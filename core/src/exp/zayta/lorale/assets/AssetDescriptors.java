package exp.zayta.lorale.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class AssetDescriptors {
    public static final AssetDescriptor<TextureAtlas> GAMEPLAY =
            new AssetDescriptor<TextureAtlas>("gameplay/textures/gameplay.atlas", TextureAtlas.class);



    //==UI==//
//    public static final AssetDescriptor<TextureAtlas> UI_SILVER_BTNS =
//            new AssetDescriptor<TextureAtlas>("ui/silver-btns/btns.atlas", TextureAtlas.class);


        /*Neon*/
    public static final AssetDescriptor<BitmapFont> NEON_FONT =
            new AssetDescriptor<BitmapFont>("ui/neon_skin/fonts/font-export.fnt", BitmapFont.class);

    public static final AssetDescriptor<BitmapFont> NEON_FONT_OVER =
            new AssetDescriptor<BitmapFont>("ui/neon_skin/fonts/font-over-export.fnt", BitmapFont.class);
//
    public static final AssetDescriptor<BitmapFont> NEON_FONT_PRESSED =
            new AssetDescriptor<BitmapFont>("ui/neon_skin/fonts/font-pressed-export.fnt", BitmapFont.class);

    public static final AssetDescriptor<Skin> NEON_SKIN =
            new AssetDescriptor<Skin>("ui/neon_skin/neon-ui.json", Skin.class);

    public static final AssetDescriptor<BitmapFont> FONT = NEON_FONT;
    public static final AssetDescriptor<Skin> UI_SKIN = NEON_SKIN;


}
