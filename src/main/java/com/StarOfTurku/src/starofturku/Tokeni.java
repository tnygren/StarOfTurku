/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.StarOfTurku.src.starofturku;

/**
 *
 * @author Jussi
 */
public class Tokeni {
    private String nimi;
    private int arvo;
    private boolean tinatuoppi;

    public Tokeni(String nimi, int arvo) {
        this.nimi = nimi;
        this.arvo = arvo;
        this.tinatuoppi=false;
    }
    public Tokeni luoTyhja(){
        return new Tokeni("Tyhjä", 0);
    }
    public Tokeni luoTuoppi(){
        return new Tokeni("Tuoppi", 300);
    }
    public Tokeni luoViini(){
        return new Tokeni("Viini", 500);
    }
    public Tokeni luoShotti(){
        return new Tokeni("Shotti", 1000);
    }
    public Tokeni luoTinatuoppi(){
        Tokeni tinatuoppi=new Tokeni("Tinatuoppi",0);
        tinatuoppi.setTinatuoppi(true);
        return tinatuoppi;
    }
    public Tokeni luoPoliisit(){
        return new Tokeni("Poliisit", -10000);
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    public void setTinatuoppi(boolean tinatuoppi) {
        this.tinatuoppi = tinatuoppi;
    }

    public String getNimi() {
        return nimi;
    }

    public int getArvo() {
        return arvo;
    }

    public boolean isTinatuoppi() {
        return tinatuoppi;
    }

    @Override
    public String toString() {
        return "Tokeni: " + nimi + ". Hilpeyttä: " + arvo;
    }
    
}
