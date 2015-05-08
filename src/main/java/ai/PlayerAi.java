package ai;

import creatures.Creature;
import sight.FieldOfView;
import world.Tile;

import java.util.List;

/**
 * Created by buymeanicecream on 4/5/2015.
 */
public class PlayerAi extends CreatureAi {

    private FieldOfView fov;
    private List<String> messages;
    public PlayerAi(Creature creature, List<String> messages, FieldOfView fov) {
        super(creature);
        this.messages = messages;
        this.fov = fov;
    }

    //If your world has doors then you can make the player automatically open them by walking into them with
    // code very similar to this.
    @Override
    public void onEnter(int x, int y, int z, Tile tile) {
        if(tile.isGround()){
            creature.x = x;
            creature.y = y;
            creature.z = z;
        } else if(tile.isDigable()){
            creature.dig(x, y, z);
        }
    }

    @Override
    public boolean canSee(int wx, int wy, int wz){
        return fov.isVisible(wx, wy, wz);
    }
    @Override
    public void onNotify(String message){
        messages.add(message);
    }
}
