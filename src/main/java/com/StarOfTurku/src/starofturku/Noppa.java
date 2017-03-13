package starofturku;

import java.util.Random;

/**
 *
 * @author Jussi
 */
public class Noppa {
    private Random random;

    public Noppa() {
        this.random = new Random();
    }    
    public int heita(){
        return 1+this.random.nextInt(6);
    }
}
        