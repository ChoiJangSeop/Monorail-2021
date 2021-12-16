package board;

public class Tile {
    public static enum Type { UPDOWN, LEFTRIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, NONE };
    public static enum Connect { ABLE, DISABLE, DEFAULT };

    private Connect right, left, up, down;
    private int x, y;

    public Tile(Type type, int x, int y) {
        switch (type) {
            case UPDOWN :
                right = Connect.DISABLE; left = Connect.DISABLE; up = Connect.ABLE; down = Connect.ABLE;
                break;
            
            case LEFTRIGHT :
                right = Connect.ABLE; left = Connect.ABLE; up = Connect.DISABLE; down = Connect.DISABLE;
                break;

            case UPLEFT :
                right = Connect.DISABLE; left = Connect.ABLE; up = Connect.ABLE; down = Connect.DISABLE;
                break;
                
            case UPRIGHT :
                right = Connect.ABLE; left = Connect.DISABLE; up = Connect.ABLE; down = Connect.DISABLE;
                break;
                
            case DOWNLEFT :
                right = Connect.DISABLE; left = Connect.ABLE; up = Connect.DISABLE; down = Connect.ABLE;
                break;
            
            case DOWNRIGHT :
                right = Connect.ABLE; left = Connect.DISABLE; up = Connect.DISABLE; down = Connect.ABLE;
                break;
            
            case NONE :
                right = Connect.DEFAULT; left = Connect.DEFAULT; up = Connect.DEFAULT; down = Connect.DEFAULT;
        }

        this.x = x;
        this.y = y;
    }

    public void setType(Type type) {
        switch (type) {
            case UPDOWN :
                right = Connect.DISABLE; left = Connect.DISABLE; up = Connect.ABLE; down = Connect.ABLE;
                break;
            
            case LEFTRIGHT :
                right = Connect.ABLE; left = Connect.ABLE; up = Connect.DISABLE; down = Connect.DISABLE;
                break;

            case UPLEFT :
                right = Connect.DISABLE; left = Connect.ABLE; up = Connect.ABLE; down = Connect.DISABLE;
                break;
                
            case UPRIGHT :
                right = Connect.ABLE; left = Connect.DISABLE; up = Connect.ABLE; down = Connect.DISABLE;
                break;
                
            case DOWNLEFT :
                right = Connect.DISABLE; left = Connect.ABLE; up = Connect.DISABLE; down = Connect.ABLE;
                break;
            
            case DOWNRIGHT :
                right = Connect.ABLE; left = Connect.DISABLE; up = Connect.DISABLE; down = Connect.ABLE;
                break;
            
            case NONE :
                right = Connect.DEFAULT; left = Connect.DEFAULT; up = Connect.DEFAULT; down = Connect.DEFAULT;
        }
    }
    
    public Connect getRight() { return this.right; }
    public Connect getLeft() { return this.left; }
    public Connect getUp() { return this.up; }
    public Connect getDown() { return this.down; }

    public int getX() { return this.x; }
    public int getY() { return this.y; } 
}