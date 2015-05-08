package world;

import asciiPanel.AsciiPanel;

import java.awt.*;

/**
 * Created by buymeanicecream on 4/2/2015.
 */
public enum Tile {
    FLOOR((char)250, AsciiPanel.yellow),
    WALL((char)177, AsciiPanel.yellow),
    BOUNDS('x', AsciiPanel.brightBlack),
    STAIRS_DOWN('>', AsciiPanel.white),
    STAIRS_UP('<', AsciiPanel.white),
    UNKNOWN(' ', AsciiPanel.white);

    Tile(char glyph, Color color) {
        this.glyph = glyph;
        this.color = color;
    }

    private char glyph;
    public char glyph(){ return glyph; }

    private Color color;
    public Color color(){ return color; }

    public boolean isDigable(){
        return this == Tile.WALL;
    }

    public boolean isGround(){
        return this != WALL && this != BOUNDS;
    }
}
