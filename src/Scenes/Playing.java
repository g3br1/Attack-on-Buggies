package Scenes;

import Game.Game;
import levels.LevelBuild;
import managers.TileManager;
import ui.BottomBar;
import ui.Buttons;

import java.awt.Graphics;

import static Game.GameStates.*;

public class Playing extends GameScene implements SceneMethods{
    private int[][] lvl;
    private TileManager tileManager;
    private BottomBar bottomBar;
    private int lastWidth = 0;
    private int lastHeight = 0;
    private Buttons pMenu;

    public Playing(Game game) {
        super(game);

        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();

        initBar();
        initButtons();
    }

    private void initBar()
    {
        updateBarPosition();
    }

    private void initButtons()
    {
        pMenu = new Buttons(2, 2, 100, 50, "/resources/textures/ui/menubuttons/menu_button.png");
    }

    private void updateBarPosition() {
        int width = getGame().getWidth();
        int height = getGame().getHeight();


        if (bottomBar == null) {
        bottomBar = new BottomBar(5, height-260, width-10, 250, "/resources/textures/ui/menubuttons/bar.png");
        } else {
            bottomBar.updatePosition(5, height-260);
            bottomBar.updateSize(width-10, 250);
        }

        lastWidth = width;
        lastHeight = height;
    }

    @Override
    public void render(Graphics g) {

        int currentWidth = getGame().getWidth();
        int currentHeight = getGame().getHeight();

        if (currentWidth != lastWidth || currentHeight != lastHeight) {
            updateBarPosition();
        }

        for(int y = 0; y < lvl.length; y++) {
            for(int x=0; x<lvl[y].length; x++){
                int id = tileManager.getAutoTile(lvl, x, y);
                g.drawImage(tileManager.getSprite(id), x*64, y*64, null);
            }
        };

        bottomBar.draw(g);
        pMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(pMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (pMenu != null) {
            pMenu.setMouseOver(false);
        };

        if(pMenu.getBounds().contains(x, y))
        {
            pMenu.setMouseOver(true);
        }
    }
}
