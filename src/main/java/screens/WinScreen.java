package screens;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

/**
 * Created by buymeanicecream on 02/04/2015.
 */
public class WinScreen implements Screen{
    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("you won ", 1, 1);
        terminal.writeCenter("enter to restart", 22);

    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }
}
