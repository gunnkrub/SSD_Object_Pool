import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class reusablePool {
    public List<Bullet> bullets = new ArrayList<Bullet>();
    public reusablePool(){
        for(int i=0; i<100; i++) {
            bullets.add(new Bullet(-100,-100,0,0));
        }
    }
    public Bullet acquireReusable(){
        Bullet bullet = bullets.get(0);
        Collections.rotate(bullets, -1);
        return bullet;
    }
    //public void releaseReusable(Bullet bullet) {
    //    bullet.x = 0;
    //}
}
