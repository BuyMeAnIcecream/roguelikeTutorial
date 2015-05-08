package screens;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

/**
 * Created by buymeanicecream on 02/04/2015.
 */
public class StartScreen implements Screen{
    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("bagel tut", 1, 1);
        terminal.writeCenter("--[enter] to start --", 22);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }
}
