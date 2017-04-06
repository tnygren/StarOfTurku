/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin;
import java.util.ArrayList;
import java.util.Random;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.LatLon;

/**
 *
 * @author Jussi
 */

/**
 * Luo uuden kartan, jossa on ennalta määritellyt solmut. Jos haluat muuttaa solmuja, tee se alusta()-metodin kautta
*/
public class Kartta {
    private ArrayList<Solmu> kartta;
    private ArrayList<Tokeni> tokenit;
    public Kartta() {
        this.kartta = new ArrayList<>();
        this.tokenit=alustaTokenit();
        this.alusta();
    }
    /**
     * Alustaa kartan ennalta määrätyillä solmuilla. Metodi liittää solmut yhtene siten, että syntyy yhtenäinen jana, jonka molemmissa päissä
     * on silmukat.
     */
    public void alusta(){
        this.kartta.add(new Solmu("Yo-kylä", null, new GoogleMapMarker("Yo-kylä", new LatLon(60.460794, 22.285921), false, "VAADIN/start.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.461529, 22.286880), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.462021, 22.287885), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Three Beers", otaTokeni(), new GoogleMapMarker("Three Beers", new LatLon(60.462488, 22.288767), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.462208, 22.289464), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.461848, 22.290301), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.461330, 22.290462), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Hotelli Caribia", otaTokeni(), new GoogleMapMarker("Hotelli Caribia", new LatLon(60.460558, 22.290183), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.460026, 22.289449), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.459109, 22.288789), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.458638, 22.287566), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.457935, 22.286944), false, "VAADIN/black-circle.png")));
        Solmu yliopisto=new Solmu("Yliopisto", otaTokeni(), new GoogleMapMarker("Yliopisto", new LatLon(60.457279, 22.286880), false, "VAADIN/blue-circle.png"));
        this.kartta.add(yliopisto);
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.456762, 22.287055), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.456083, 22.287345), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.455232, 22.288114), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Proffan kellari", otaTokeni(), new GoogleMapMarker("Proffan kellari", new LatLon(60.454587, 22.287792), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.453846, 22.287567), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.453544, 22.286730), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.453205, 22.285700), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.452798, 22.284370), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.452443, 22.283243), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Portti", otaTokeni(), new GoogleMapMarker("Portti", new LatLon(60.452141, 22.282352), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.452141, 22.281116), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.452030, 22.279464), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.451781, 22.278112), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Brahen puisto", otaTokeni(), new GoogleMapMarker("Brahen puisto", new LatLon(60.451749, 22.276664), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.451939, 22.275495), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.451905, 22.273834), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.451583, 22.273485), false, "VAADIN/black-circle.png")));
        Solmu alepub= new Solmu("Alepub", otaTokeni(), new GoogleMapMarker("Alepub", new LatLon(60.451303, 22.272198), false, "VAADIN/blue-circle.png"));
        this.kartta.add(alepub);
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.451060, 22.271814), false, "VAADIN/black-circle.png")));        
        this.kartta.add(new Solmu("Dynamo", otaTokeni(), new GoogleMapMarker("Dynamo", new LatLon(60.450679, 22.271245), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.450383, 22.270376), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.450066, 22.269249), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.449711, 22.268122), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Monkey", otaTokeni(), new GoogleMapMarker("The Monkey", new LatLon(60.450166, 22.267489), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.450573, 22.266877), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.451245, 22.265826), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Baari Baari", otaTokeni(), new GoogleMapMarker("Baari Baari", new LatLon(60.451832, 22.265000), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu("Whiskey Bar", otaTokeni(), new GoogleMapMarker("Whiskey Bar", new LatLon(60.452239, 22.265322), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.452413, 22.266480), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.452150, 22.267896), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.451732, 22.268475), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Teerenpeli", otaTokeni(), new GoogleMapMarker("Teerenpeli", new LatLon(60.451340, 22.269205), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu("Pub Tinatuoppi", otaTokeni(), new GoogleMapMarker("Pub Tinatuoppi", new LatLon(60.451663, 22.269842), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.452023, 22.270668), false, "VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Hunter's Inn", otaTokeni(), new GoogleMapMarker("Hunter's Inn", new LatLon(60.451959, 22.271633), false, "VAADIN/blue-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.451691, 22.271888), false, "VAADIN/black-circle.png")));
        for(int i=1; i<this.kartta.size(); i++){
            this.kartta.get(i).lisaaSolmu(this.kartta.get(i-1));
        }
        this.kartta.get(kartta.size()-1).lisaaSolmu(alepub);
        //this.kartta.get(0).lisaaSolmu(this.kartta.get(this.kartta.size()-1));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.460238, 22.285785), false, "VAADIN/black-circle.png")));
        this.kartta.get(0).lisaaSolmu(this.kartta.get(this.kartta.size()-1));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.459534, 22.285871), false, "VAADIN/black-circle.png")));
        this.kartta.get(this.kartta.size()-2).lisaaSolmu(this.kartta.get(this.kartta.size()-1));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.458942, 22.285281), false, "VAADIN/black-circle.png")));
        this.kartta.get(this.kartta.size()-2).lisaaSolmu(this.kartta.get(this.kartta.size()-1));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.458170, 22.285479), false, "VAADIN/black-circle.png")));
        this.kartta.get(this.kartta.size()-2).lisaaSolmu(this.kartta.get(this.kartta.size()-1));

        this.kartta.get(this.kartta.size()-1).lisaaSolmu(yliopisto);
    }
    /**
     * Alustaa tokenit, joita pelin kartta käyttää. 
     */
    public ArrayList<Tokeni> alustaTokenit(){
        Tokeni tokenisoija= new Tokeni("",0);
        ArrayList<Tokeni> tokenit=new ArrayList<>();
        //3 tuoppia
        for(int i=0; i<3; i++){
            tokenit.add(tokenisoija.luoTuoppi());
        }
        //5 tyhjää
        for(int i=0; i<5; i++){
            tokenit.add(tokenisoija.luoTyhja());
        }
        tokenit.add(tokenisoija.luoViini());
        tokenit.add(tokenisoija.luoViini());
        //tinatuoppi
        tokenit.add(tokenisoija.luoTinatuoppi());
        tokenit.add(tokenisoija.luoDrinkki());
        tokenit.add(tokenisoija.luoPoliisit());
        tokenit.add(tokenisoija.luoPoliisit());
        //yht. 3+5+2+1+1+2= 14
        return tokenit;
    }
    /**
     * Palauttaa yhden satunnaisen tokenin ja poistaa sen listasta.
     */
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
