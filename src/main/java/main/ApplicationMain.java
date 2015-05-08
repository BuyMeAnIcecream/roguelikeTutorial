package main;

import asciiPanel.AsciiPanel;
import screens.Screen;
import screens.StartScreen;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by buymeanicecream on 4/2/2015.
 */
class ApplicationMain extends JFrame implements KeyListener{
    private final static long SerialVersionUID = 1060623638149583738L;

    private AsciiPanel terminal;
    private Screen screen;

    public ApplicationMain(){
        super();
        terminal = new AsciiPanel();
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }

    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    public void keyPressed(KeyEvent e){
        screen = screen.respondToUserInput(e);
        repaint();
    }
    public void keyReleased(KeyEvent e){}

    public void keyTyped(KeyEvent e){}

    public static void main(String[] args){
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

}
