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
    private String bigIcon;
    private String smallIcon;
    private String audio;

    public Tokeni(String nimi, int arvo) {
        this.nimi = nimi;
        this.arvo = arvo;
        this.tinatuoppi=false;
        this.bigIcon=null;
        this.audio=null;
        this.smallIcon=null;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
    
    public String getBigIcon() {
        return bigIcon;
    }

    public void setBigIcon(String iconUrl) {
        this.bigIcon = iconUrl;
    }
    
    public Tokeni luoTyhja(){
        Tokeni t= new Tokeni("Tyhjä", 0);
        t.setBigIcon("img/tyhja.png");
        t.setAudio("audio/empty.mp3");
        t.setSmallIcon("img/tyhjasmall.png");
        return t;
    }
    public Tokeni luoTuoppi(){
        Tokeni t= new Tokeni("Tuoppi", 300);
        t.setBigIcon("img/beer.png");
        t.setAudio("audio/beer.mp3");
        t.setSmallIcon("img/beersmall.png");
        return t;
    }
    public Tokeni luoViini(){
        Tokeni t= new Tokeni("Viini", 500);
        t.setBigIcon("img/wine.png");
        t.setAudio("audio/wine.mp3");
        t.setSmallIcon("img/winesmall.png");
        return t;
    }
    public Tokeni luoDrinkki(){
        Tokeni t= new Tokeni("Drinkki", 1000);
        t.setBigIcon("img/coctail.png");
        t.setAudio("audio/cocktail.mp3");
        t.setSmallIcon("img/coctailsmall.png");
        return t;
    }
    public Tokeni luoTinatuoppi(){
        Tokeni tinatuoppi=new Tokeni("Tinatuoppi",0);
        tinatuoppi.setTinatuoppi(true);
        tinatuoppi.setBigIcon("img/trophy.png");
        tinatuoppi.setAudio("audio/victory.mp3");
        tinatuoppi.setSmallIcon("img/trophysmall.png");
        return tinatuoppi;
    }
    public Tokeni luoPoliisit(){
        Tokeni t= new Tokeni("Poliisit", -10000);
        t.setBigIcon("img/cops.png");
        t.setSmallIcon("img/beer.png");
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
