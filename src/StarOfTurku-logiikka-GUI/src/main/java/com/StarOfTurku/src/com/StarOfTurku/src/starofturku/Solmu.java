package com.StarOfTurku.src.starofturku;

import java.util.ArrayList;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
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

    //luodaan konstruktori sekä nimetyille paikoille, että nimettömille siirtymäsolmuille
    public Solmu(String nimi, Tokeni tokeni, GoogleMapMarker m ) {
        this.nimi = nimi;
        this.tokeni = tokeni;
        this.pelaaja=false;
        this.vierussolmut=new ArrayList<>();
        this.marker=m;
    }
    
    public Solmu(GoogleMapMarker m) {
        this.nimi="-";
        this.tokeni=null;
        this.pelaaja=false;
        this.vierussolmut=new ArrayList<>();
        this.marker=m;
    }
    public GoogleMapMarker getMarker(){
        return this.marker;
    }
    public String getNimi() {
        return nimi;
    }

    public Tokeni getTokeni() {
        return tokeni;
    }

    public boolean isPelaaja() {
        return pelaaja;
    }
    public void setMarker(GoogleMapMarker m){
        this.marker=m;
    }
    public void setPelaaja(boolean pelaaja) {
        this.pelaaja = pelaaja;
    }
    
    public ArrayList<Solmu> getVierussolmut() {
        return vierussolmut;
    }
    //lisätään kaksi solmua toisiinsa, niin että voidaan kulkea molempiin suuntiin
    public void lisaaSolmu(Solmu s){
        this.vierussolmut.add(s);
        if(!s.getVierussolmut().contains(this)){
            s.lisaaSolmu(this);
        }
    }
    //julkinen liikenne a.k.a. pikamatkustaminen voi tapahtua vain kahden nimetyn paikan välillä
    public boolean julkinenLiikenne(Solmu s){
        return !this.nimi.equals("-") && !s.getNimi().equals("-");
    }
    public Tokeni poistaTokeni(){
        Tokeni temp=this.tokeni;
        this.tokeni=null;
        return temp;
    }
    
}
