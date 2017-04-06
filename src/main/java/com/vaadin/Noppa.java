package com.vaadin;

import java.util.Random;

/**
 *
 * @author Jussi
 */
public class Noppa {
    private Random random;
    private int tulos;

    public Noppa() {
        this.random = new Random();
        this.tulos = 0;
    }
    /**
     * Muuttaa nopan tuloksen satunnaiseen arvoon välillä 1-6.
     */
    public int heita() {
        tulos = 1+this.random.nextInt(6);
        return tulos;
    }
    public int getTulos() {
        return tulos;
    }
}
        