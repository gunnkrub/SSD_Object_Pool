import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class reusablePool {
    protected static  reusablePool instance = null;
    public List<Bullet> bullets = new ArrayList<Bullet>();
    public static reusablePool getInstance(){
        if(instance == null){
            instance = new reusablePool();
        }
        return instance;
    }
    public reusablePool(){
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
