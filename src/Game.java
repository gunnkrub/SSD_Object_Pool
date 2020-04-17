import java.util.List;
import java.util.Observable;

public class Game extends Observable {

    private int width = 600;
    private int height = 600;

    private Thread mainLoop;
    private boolean alive;
    reusablePool pool = reusablePool.getInstance();
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
        for(Bullet bullet : pool.bullets) {
            bullet.move();
        }
    }

    private void cleanupBullets() {
        for(Bullet bullet : pool.bullets) {
            if(bullet.getX() < -50 ||
                    bullet.getX() >= width ||
                    bullet.getY() < -50 ||
                    bullet.getY() >= height) {
                pool.releaseReusable(bullet);
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
        return pool.bullets;
    }

    public void burstBullets(int x, int y) {
        Bullet bullet = pool.acquireReusable();
        bullet.set(x, y, 1, 0);
        Bullet bullet2 = pool.acquireReusable();
        bullet2.set(x, y, 0, 1);
        Bullet bullet3 = pool.acquireReusable();
        bullet3.set(x, y, -1, 0);
        Bullet bullet4 = pool.acquireReusable();
        bullet4.set(x, y, 0, -1);
        Bullet bullet5 = pool.acquireReusable();
        bullet5.set(x, y, 1, 1);
        Bullet bullet6 = pool.acquireReusable();
        bullet6.set(x, y, 1, -1);
        Bullet bullet7 = pool.acquireReusable();
        bullet7.set(x, y, -1, 1);
        Bullet bullet8 = pool.acquireReusable();
        bullet8.set(x, y, -1, -1);

    }
}
