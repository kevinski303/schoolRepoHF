package global.models;

public class DestroyableWall extends Tile {
    private boolean destroy;

    public DestroyableWall(int h, int v){
        super(h, v);
        destroy = false;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void Destroy() {
        this.destroy = true;
    }
}
