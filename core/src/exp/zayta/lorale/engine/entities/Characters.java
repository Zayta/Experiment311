package exp.zayta.lorale.engine.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import exp.zayta.lorale.assets.RegionNames;
import exp.zayta.lorale.util.BiMap;

//todo modify this file when adding new characters
public class Characters {


    public enum CharacterName {
        LORALE,TENYU,LETRA,TARIA,JOLTAN,XIF,



        ANONYMOUS;

        //    @Override
        //    public String toString() {
        //        return super.toString().toLowerCase();
        //    }
    }
    private TextureAtlas textureAtlas;
    public Characters(TextureAtlas textureAtlas){
        this.textureAtlas = textureAtlas;
        initCharacterTextures();
//        initCharacterColors();
        initCharacterAbbrev();

    }
    private BiMap<CharacterName, Array<TextureAtlas.AtlasRegion>> characterTextures;

//    private BiMap<Characters.CharacterName, YanseColor> characterColors;
    private BiMap<CharacterName,String> characterAbbrevs = new BiMap<CharacterName, String>();

    private void initCharacterTextures(){
        characterTextures = new BiMap<CharacterName, Array<TextureAtlas.AtlasRegion>>();
        characterTextures.put(CharacterName.LORALE,textureAtlas.findRegions(RegionNames.LORALE));
        characterTextures.put(CharacterName.TENYU,textureAtlas.findRegions(RegionNames.TENYU));
        characterTextures.put(CharacterName.TARIA,textureAtlas.findRegions(RegionNames.TARIA));
        characterTextures.put(CharacterName.LETRA,textureAtlas.findRegions(RegionNames.LETRA));
        characterTextures.put(CharacterName.XIF,textureAtlas.findRegions(RegionNames.XIF));

        characterTextures.put(CharacterName.JOLTAN,textureAtlas.findRegions(RegionNames.JOLTAN));

    }
//    private void initCharacterColors(){
//
//        characterColors = new BiMap<CharacterName, YanseColor>();
//        characterColors.put(CharacterName.LORALE, YanseColor.ORANGE);
//        characterColors.put(CharacterName.TENYU, YanseColor.GRAY);
//        characterColors.put(CharacterName.TARIA, YanseColor.LAVENDER);
//        characterColors.put(CharacterName.LETRA, YanseColor.RED);
//        characterColors.put(CharacterName.XIF, YanseColor.WHITE);
//
//        characterColors.put(CharacterName.JOLTAN, YanseColor.WHITE);
//
//
//    }
    private void initCharacterAbbrev(){
        characterAbbrevs.put(CharacterName.LORALE,"ll");
        characterAbbrevs.put(CharacterName.TENYU,"tyu");
        characterAbbrevs.put(CharacterName.LETRA,"lt");
        characterAbbrevs.put(CharacterName.TARIA,"ta");
        characterAbbrevs.put(CharacterName.JOLTAN,"jt");
    }
    //todo update getCharacter table when u add new entry to charactername
    public Characters.CharacterName getCharacterName(String abbrev){
        abbrev = abbrev.trim().toLowerCase();
//        System.out.println("characterAbbrevs was "+characterAbbrevs.toString());
        if(characterAbbrevs.containsValue(abbrev)){
            System.out.println("Key contained");
            return characterAbbrevs.getKey(abbrev);
        }


        return Characters.CharacterName.ANONYMOUS;
    }
    public String getCharacterAbbrev(CharacterName characterName){
        if(characterAbbrevs.containsKey(characterName)){
            return characterAbbrevs.get(characterName);
        }


        return "an.*";
    }


    public Array<TextureAtlas.AtlasRegion> getTexture(CharacterName characterName){
        return characterTextures.get(characterName);
    }
//    public Color getColor(CharacterName characterName){
//        YanseColor yanseColor=characterColors.get(characterName);
//        if(yanseColor!=null)
//            return yanseColor.color;
//        return Color.WHITE;
//    }

//    private void initAnon(){
//        for(int i = 0; i<AnonymousIterator.anonymousCharacters.length;i++){
//            CharacterName anon = AnonymousIterator.anonymousCharacters[i];
//            characterTextures.put(anon,textureAtlas.findRegions(RegionNames.ANONYMOUS));
//            characterAbbrevs.put(anon,"an"+(i+1));
//        }
//
//
//    }

}
