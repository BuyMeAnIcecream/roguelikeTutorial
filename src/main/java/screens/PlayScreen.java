package screens;


import asciiPanel.AsciiPanel;
import creatures.Creature;
import creatures.CreatureFactory;
import sight.FieldOfView;
import world.World;
import world.WorldBuilder;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by buymeanicecream on 02/04/2015.
 */
public class PlayScreen implements Screen {
    private Creature player;
    private World world;
    private int screenWidth;
    private int screenHeight;
    private List<String> messages;
    private FieldOfView fov;


    public PlayScreen(){
        screenHeight = 21;
        screenWidth = 80;
        messages = new ArrayList<String>();
        createWorld();
        fov = new FieldOfView(world);

        CreatureFactory creatureFactory = new CreatureFactory(world);
        createCreatures(creatureFactory);
    }

    void displayMessages(AsciiPanel terminal, List<String> messages){
        int top = screenHeight - messages.size();
        for(int i = 0; i < messages.size(); i++){
            terminal.writeCenter(messages.get(i), top + i);
        }

        messages.clear();
    }

    private void createCreatures(CreatureFactory creatureFactory) {
        player = creatureFactory.newPlayer(messages, fov);

        for (int z = 0; z < world.depth(); z++) {


            for (int i = 0; i < 8; i++) {
                creatureFactory.newFungus(z);
            }
        }
    }
    void createWorld(){

        world = new WorldBuilder(90, 32, 2)
                .makeCaves()
                .build();
    }

    int getScrollX(){
        return Math.max(0, Math.min(player.x - screenWidth / 2, world.width() - screenWidth));
    }

    int getScrollY(){
        return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight));
    }



    private void displayTiles(AsciiPanel terminal, int left, int top) {
        fov.update(player.x, player.y, player.z, player.visionRadius());

        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                int wx = x + left;
                int wy = y + top;

                if (player.canSee(wx, wy, player.z))
                    terminal.write(world.glyph(wx, wy, player.z), x, y, world.color(wx, wy, player.z));
                else
                    terminal.write(fov.tile(wx, wy, player.z).glyph(), x, y, Color.darkGray);
            }
        }
    }

    public void displayOutput(AsciiPanel terminal) {
        int left = getScrollX();
        int top = getScrollY();

        displayTiles(terminal, left, top);
        displayMessages(terminal, messages);

        terminal.writeCenter("-- press [escape] to lose or [enter] to win --", 23);

        String stats = String.format(" %3d/%3d hp", player.hp(), player.maxHp());
        terminal.write(stats, 1, 23);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()){
            case KeyEvent.VK_LEFT: player.moveBy(-1, 0, 0); break;
            case KeyEvent.VK_RIGHT: player.moveBy(1, 0, 0); break;
            case KeyEvent.VK_UP: player.moveBy(0, -1, 0); break;
            case KeyEvent.VK_DOWN: player.moveBy(0, 1, 0); break;
/*          case KeyEvent.VK_Y: player.moveBy(-1, -1, 0); break;
            case KeyEvent.VK_U: player.moveBy(1, -1, 0); break;
            case KeyEvent.VK_B: player.moveBy(-1, 1, 0); break;
            case KeyEvent.VK_N: player.moveBy(1, 1, 0); break;
*/
            case KeyEvent.VK_ESCAPE: return new LoseScreen();
            case KeyEvent.VK_ENTER: return new WinScreen();
        }
        switch (key.getKeyChar()){
            case '<': player.moveBy( 0, 0, -1); break;
            case '>': player.moveBy( 0, 0, 1); break;
        }
        world.update();
    return this;
    }
}

