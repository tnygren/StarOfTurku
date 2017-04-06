package com.StarOfTurku.src.starofturku;

import java.util.ArrayList;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jussi
 */
public class Solmu {
    private String nimi;
    private Tokeni tokeni;
    private boolean pelaaja;
    private ArrayList<Solmu>vierussolmut;
    private GoogleMapMarker marker;

    /**
     * Tällä konstruktorilla luodaan kartan nimelliset solmut. Alkusolmua lukuunottamatta kaikille solmuille määritellään myös
     * oma tokeni. 
     */
    public Solmu(String nimi, Tokeni tokeni,GoogleMapMarker marker) {
        this.nimi = nimi;
        this.tokeni = tokeni;
        this.pelaaja=false;
        this.vierussolmut=new ArrayList<>();
        this.marker=marker;
    }
    /**
     * Tällä konstruktorilla luodaan kartan nimettömät siirtymäsolmut.
     */
    public Solmu(GoogleMapMarker marker) {
        this.nimi="-";
        this.tokeni=null;
        this.pelaaja=false;
        this.vierussolmut=new ArrayList<>();
        this.marker=marker;
    }

    public GoogleMapMarker getMarker() {
        return marker;
    }
    
    public String getNimi() {
        return nimi;
    }

    public Tokeni getTokeni() {
        return tokeni;
    }

    public void setTokeni(Tokeni tokeni) {
        this.tokeni = tokeni;
    }

    public boolean isPelaaja() {
        return pelaaja;
    }

    public void setPelaaja(boolean pelaaja) {
        this.pelaaja = pelaaja;
    }
    
    public ArrayList<Solmu> getVierussolmut() {
        return vierussolmut;
    }
    /**
     *  Lisätään kaksi solmua toisiinsa, niin että voidaan kulkea molempiin suuntiin.
     */
    public void lisaaSolmu(Solmu s){
        this.vierussolmut.add(s);
        if(!s.getVierussolmut().contains(this)){
            s.lisaaSolmu(this);
        }
    }
    /**
     * 
     * Tarkastaa, mikäli kahden solmun välillä on mahdollista pikamatkustaa (pelin tulevaa kehitystä varten)
     */
    public boolean julkinenLiikenne(Solmu s){
        return !this.nimi.equals("-") && !s.getNimi().equals("-");
    }
    /**
     *  Poistaa ja palauttaa solmussa olevan tokenin.
     */
    public Tokeni poistaTokeni(){
        Tokeni temp=this.tokeni;
        this.tokeni=null;
        return temp;
    }
    
}
