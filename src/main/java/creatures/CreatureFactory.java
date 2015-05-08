package creatures;

import ai.FungusAi;
import ai.PlayerAi;
import asciiPanel.AsciiPanel;
import sight.FieldOfView;
import world.World;

import java.util.List;

/**
 * Created by buymeanicecream on 4/5/2015.
 */
public class CreatureFactory {
    private World world;

    public CreatureFactory(World world){
        this.world = world;
    }

    public Creature newPlayer(List<String> messages, FieldOfView fov){
        Creature player = new Creature(world, '@', AsciiPanel.brightWhite, 100, 20, 5);
        world.addAtEmptyLocation(player, 0);
        new PlayerAi(player, messages, fov);
        return player;

    }

    public Creature newFungus(int depth){
        Creature fungus = new Creature(world, 'f', AsciiPanel.green, 20, 0, 3);
        world.addAtEmptyLocation(fungus, depth);
        new FungusAi(fungus, this);
        return fungus;
    }

}
