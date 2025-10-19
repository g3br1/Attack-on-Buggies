package managers;

import levels.LoadAtlas;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile GRASS, ROAD;
    public Tile ROAD_UP, ROAD_DOWN, ROAD_LEFT, ROAD_RIGHT;
    public Tile ROAD_UP_RIGHT, ROAD_UP_LEFT, ROAD_DOWN_RIGHT, ROAD_DOWN_LEFT;
    public Tile ROAD_UP_DOWN, ROAD_LEFT_RIGHT;
    public Tile ROAD_CROSS;
    public Tile ROAD_UP_RIGHT_DOWN, ROAD_UP_LEFT_DOWN, ROAD_UP_RIGHT_LEFT, ROAD_RIGHT_DOWN_LEFT;

    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        // Базові тайли
        tiles.add(GRASS = new Tile(getSprite(1, 2)));                 // 0 - трава
        tiles.add(ROAD = new Tile(getSprite(9, 2)));                  // 1 - базова дорога

        // Прямі дороги
        tiles.add(ROAD_UP_DOWN = new Tile(getSprite(5, 2)));          // 2 - дорога вверх-вниз
        tiles.add(ROAD_LEFT_RIGHT = new Tile(getSprite(6, 1)));       // 3 - дорога вліво-вправо

        // Кути
        tiles.add(ROAD_UP_RIGHT = new Tile(getSprite(5, 3)));         // 4 - кут: вверх-вправо
        tiles.add(ROAD_UP_LEFT = new Tile(getSprite(7, 3)));          // 5 - кут: вверх-вліво
        tiles.add(ROAD_DOWN_RIGHT = new Tile(getSprite(5, 1)));       // 6 - кут: вниз-вправо
        tiles.add(ROAD_DOWN_LEFT = new Tile(getSprite(7, 1)));        // 7 - кут: вниз-вліво

        // Т-образні перетини
        tiles.add(ROAD_UP_RIGHT_DOWN = new Tile(getSprite(9, 1)));    // 8 - T: вверх-вправо-вниз
        tiles.add(ROAD_UP_LEFT_DOWN = new Tile(getSprite(9, 1)));     // 9 - T: вверх-вліво-вниз
        tiles.add(ROAD_UP_RIGHT_LEFT = new Tile(getSprite(9, 1)));   // 10 - T: вверх-вправо-вліво
        tiles.add(ROAD_RIGHT_DOWN_LEFT = new Tile(getSprite(9, 1))); // 11 - T: вправо-вниз-вліво

        // Х-образний перетин
        tiles.add(ROAD_CROSS = new Tile(getSprite(2, 7)));           // 12 - перехрестя
        // Тупики (dead ends)
        tiles.add(ROAD_UP = new Tile(getSprite(2, 8)));     // 13 - тупик вверх
        tiles.add(ROAD_RIGHT = new Tile(getSprite(1, 7)));  // 14 - тупик вправо
        tiles.add(ROAD_DOWN = new Tile(getSprite(2, 6)));   // 15 - тупик вниз
        tiles.add(ROAD_LEFT = new Tile(getSprite(3, 7)));   // 16 - тупик вліво

}

    private void loadAtlas() {
        atlas = LoadAtlas.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCodr){
        return atlas.getSubimage(xCord*64,yCodr*64,64,64);
    }

    // Метод для автоматичного вибору спрайту дороги на основі сусідів
    public int getAutoTile(int[][] lvlMap, int x, int y) {
        if (lvlMap[y][x] != 1) return 0;

        boolean up = isRoad(lvlMap, x, y - 1);
        boolean right = isRoad(lvlMap, x + 1, y);
        boolean down = isRoad(lvlMap, x, y + 1);
        boolean left = isRoad(lvlMap, x - 1, y);

        int roadCount = countRoadNeighbors(up, right, down, left);

        return calculateTileType(up, right, down, left, roadCount);
    }

    private boolean isRoad(int[][] lvlMap, int x, int y) {
        if (x < 0 || x >= lvlMap[0].length || y < 0 || y >= lvlMap.length) {
            return false;
        }
        return lvlMap[y][x] == 1;
    }

    private int countRoadNeighbors(boolean up, boolean right, boolean down, boolean left) {
        int count = 0;
        if (up) count++;
        if (right) count++;
        if (down) count++;
        if (left) count++;
        return count;
    }

    private int calculateTileType(boolean up, boolean right, boolean down, boolean left, int roadCount) {
        switch (roadCount) {
            case 1: return getDeadEndTile(up, right, down, left);
            case 2: return getCornerOrStraightTile(up, right, down, left);
            case 3: return getTTile(up, right, down, left);
            case 4: return 12; // ROAD_CROSS
            default: return 1; // ROAD (резерв)
        }
    }

    private int getDeadEndTile(boolean up, boolean right, boolean down, boolean left) {
        // Тупики для кожної сторони
        if (up) return 13; // ROAD_DEAD_END_UP
        if (right) return 14; // ROAD_DEAD_END_RIGHT
        if (down) return 15; // ROAD_DEAD_END_DOWN
        if (left) return 16; // ROAD_DEAD_END_LEFT
        return 1; // ROAD
    }

    private int getCornerOrStraightTile(boolean up, boolean right, boolean down, boolean left) {
        // Прямі дороги
        if (up && down) return 2; // ROAD_UP_DOWN
        if (left && right) return 3; // ROAD_LEFT_RIGHT

        // Кути
        if (up && right) return 4; // ROAD_UP_RIGHT
        if (up && left) return 5; // ROAD_UP_LEFT
        if (down && right) return 6; // ROAD_DOWN_RIGHT
        if (down && left) return 7; // ROAD_DOWN_LEFT

        return 1; // ROAD
    }

    private int getTTile(boolean up, boolean right, boolean down, boolean left) {
        if (up && right && down) return 8; // ROAD_UP_RIGHT_DOWN
        if (up && left && down) return 9; // ROAD_UP_LEFT_DOWN
        if (up && right && left) return 10; // ROAD_UP_RIGHT_LEFT
        if (right && down && left) return 11; // ROAD_RIGHT_DOWN_LEFT
        return 1; // ROAD
    }
}