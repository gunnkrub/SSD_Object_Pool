import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReusablePool {
    protected static  ReusablePool instance = null;
    public List<Bullet> bullets = new ArrayList<Bullet>();
    public static ReusablePool getInstance(){
        if(instance == null){
            instance = new ReusablePool();
        }
        return instance;
    }
    private ReusablePool(){
        for(int i=0; i<100; i++) {
            //bullets storage
            bullets.add(new Bullet(-50,-50,0,0));
        }
    }
    public Bullet acquireReusable(){
        Bullet bullet = bullets.get(0);
        Collections.rotate(bullets, -1);
        return bullet;
    }
    public void releaseReusable(Bullet bullet) {
        bullet.set(-50,-50,0,0);
    }
}
