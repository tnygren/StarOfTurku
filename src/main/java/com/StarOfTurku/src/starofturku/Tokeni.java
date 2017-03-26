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
    private String iconUrl;

    public Tokeni(String nimi, int arvo) {
        this.nimi = nimi;
        this.arvo = arvo;
        this.tinatuoppi=false;
        this.iconUrl=null;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    
    public Tokeni luoTyhja(){
        Tokeni t= new Tokeni("Tyhjä", 0);
        t.setIconUrl("img/tyhja.png");
        return t;
    }
    public Tokeni luoTuoppi(){
        Tokeni t= new Tokeni("Tuoppi", 300);
        t.setIconUrl("img/beer.png");
        return t;
    }
    public Tokeni luoViini(){
        Tokeni t= new Tokeni("Viini", 500);
        t.setIconUrl("img/wine.png");
        return t;
    }
    public Tokeni luoDrinkki(){
        Tokeni t= new Tokeni("Drinkki", 1000);
        t.setIconUrl("img/coctail.png");
        return t;
    }
    public Tokeni luoTinatuoppi(){
        Tokeni tinatuoppi=new Tokeni("Tinatuoppi",0);
        tinatuoppi.setTinatuoppi(true);
        tinatuoppi.setIconUrl("img/trophy.png");
        return tinatuoppi;
    }
    public Tokeni luoPoliisit(){
        Tokeni t= new Tokeni("Poliisit", -10000);
        t.setIconUrl("img/cops.png");
        return t;
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
