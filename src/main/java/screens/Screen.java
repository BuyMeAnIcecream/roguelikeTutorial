package screens;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

/**
 * Created by buymeanicecream on 02/04/2015.
 */
public interface Screen {
    public void displayOutput(AsciiPanel terminal);

    public Screen respondToUserInput(KeyEvent key);
}
