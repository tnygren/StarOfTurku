/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.StarOfTurku.src.starofturku;

import java.util.ArrayList;
import java.util.Random;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.LatLon;

/**
 *
 * @author Jussi
 */
public class Kartta {
    private ArrayList<Solmu> kartta;
    private ArrayList<Tokeni> tokenit;
    public Kartta() {
        this.kartta = new ArrayList<>();
        this.tokenit=alustaTokenit();
        this.alusta();
    }
    public void alusta(){
        this.kartta.add(new Solmu("Aloitus", otaTokeni(), new GoogleMapMarker("Aloitus", new LatLon(60.45046692508773,22.277934551239014), false, "VAADIN/red-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45116535629983,22.27656126022339), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45180028073331,22.27527379989624), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45300660296379,22.274608612060547), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45442450338953,22.27407217025757), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Ruutu 1", otaTokeni(), new GoogleMapMarker("Ruutu 1", new LatLon(60.456159759968465,22.273900508880615), false, "VAADIN/red-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45753520235452,22.27274179458618), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.458847108498034,22.272913455963135), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.458741311580354,22.27656126022339), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Ruutu 2", otaTokeni(), new GoogleMapMarker("Ruutu 2", new LatLon(60.459037542081205,22.27977991104126), false, "VAADIN/red-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.457323599625525,22.280466556549072), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.456117437740424,22.280638217926025), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45453031437198,22.281067371368408), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Ruutu 3", otaTokeni(), new GoogleMapMarker("Ruutu 3", new LatLon(60.45357800311918,22.28149652481079), false, "VAADIN/red-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45266799072051,22.281067371368408), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45156747654881,22.280380725860596), false, "VAADIN/black-circle.png")));
        for(int i=1; i<this.kartta.size(); i++){
            this.kartta.get(i).lisaaSolmu(this.kartta.get(i-1));
        }
        this.kartta.get(0).lisaaSolmu(this.kartta.get(this.kartta.size()-1));
    }
    
    public ArrayList<Tokeni> alustaTokenit(){
        Tokeni tokenisoija= new Tokeni("",0);
        ArrayList<Tokeni> tokenit=new ArrayList<>();
        tokenit.add(tokenisoija.luoTuoppi());
        tokenit.add(tokenisoija.luoTyhja());
        tokenit.add(tokenisoija.luoTinatuoppi());
        tokenit.add(tokenisoija.luoShotti());
        return tokenit;
    }
    public Tokeni otaTokeni(){
        int arpa= new Random().nextInt(this.tokenit.size());
        Tokeni temp= this.tokenit.get(arpa);
        this.tokenit.remove(arpa);
        return temp;
    }

    public ArrayList<Solmu> getKartta() {
        return kartta;
    }
    
}
