package exp.zayta.lorale.engine.entities.tags;

import com.badlogic.ashley.core.Component;

import exp.zayta.lorale.engine.entities.Characters;


public class CharacterTag implements Component {

    private Characters.CharacterName characterName = Characters.CharacterName.ANONYMOUS;

    public void setCharacterName(Characters.CharacterName characterName) {
        this.characterName = characterName;
    }

    public Characters.CharacterName getCharacterName() {
        return characterName;
    }

}
