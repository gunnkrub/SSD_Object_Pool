import java.util.List;
import java.util.Observable;

public class Game extends Observable {

    private int width = 600;
    private int height = 600;

    private Thread mainLoop;
    private boolean alive;
    reusablePool reusablePool = new reusablePool();
    //reusablePool pool = reusablePool.getInstance();
    public Game() {
        alive = true;

        mainLoop = new Thread() {
            @Override
            public void run() {
                while(alive) {
                    tick();
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mainLoop.start();
    }
    public void tick() {
        moveBullets();
        cleanupBullets();
    }

    private void moveBullets() {
        for(Bullet bullet : reusablePool.bullets) {
            bullet.move();
        }
    }

    private void cleanupBullets() {
        for(Bullet bullet : reusablePool.bullets) {
            if(bullet.getX() < -50 ||
                    bullet.getX() >= width ||
                    bullet.getY() < -50 ||
                    bullet.getY() >= height) {
                reusablePool.releaseReusable(bullet);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Bullet> getBullets() {
        return reusablePool.bullets;
    }

    public void burstBullets(int x, int y) {
        Bullet bullet = reusablePool.acquireReusable();
        bullet.set(x, y, 1, 0);
        Bullet bullet2 = reusablePool.acquireReusable();
        bullet2.set(x, y, 0, 1);
        Bullet bullet3 = reusablePool.acquireReusable();
        bullet3.set(x, y, -1, 0);
        Bullet bullet4 = reusablePool.acquireReusable();
        bullet4.set(x, y, 0, -1);
        Bullet bullet5 = reusablePool.acquireReusable();
        bullet5.set(x, y, 1, 1);
        Bullet bullet6 = reusablePool.acquireReusable();
        bullet6.set(x, y, 1, -1);
        Bullet bullet7 = reusablePool.acquireReusable();
        bullet7.set(x, y, -1, 1);
        Bullet bullet8 = reusablePool.acquireReusable();
        bullet8.set(x, y, -1, -1);

    }
}
