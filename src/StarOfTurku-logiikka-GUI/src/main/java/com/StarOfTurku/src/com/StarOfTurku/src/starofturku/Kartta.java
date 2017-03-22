/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.StarOfTurku.src.starofturku;
import java.util.ArrayList;
import java.util.Random;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
/**
 *
 * @author Jussi
 */
public class Kartta {
    private ArrayList<Solmu> kartta;
    private ArrayList<Tokeni>tokenit;
    public Kartta(){
        this.kartta=new ArrayList<>();
        this.tokenit=new ArrayList<>();
        this.alusta();
    }
    public ArrayList<Solmu> getKartta(){
        return this.kartta;
    }
    public void alusta(){
        this.alustaTokenit();
        this.kartta.add(new Solmu("Yliopisto", null, new GoogleMapMarker("Alku", new LatLon(60.458593,22.285401),false, "VAADIN/red-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.457651583267726,22.285090684890747), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45630784,22.28507995), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.4548054,22.2854447), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.4533981189693,22.28448987007141), false,"VAADIN/black-circle.png")));
//        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45211773,22.28239774), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Portti", otaTokeni(), new GoogleMapMarker("Portti", new LatLon(60.452011919,22.282698154), false,"VAADIN/red-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45136641,22.2799193), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.450996041,22.27756977), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.451736788,22.275531291), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45262566394189,22.274233102798462), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Alepub", otaTokeni(), new GoogleMapMarker("Alepub", new LatLon(60.45268915,22.272140979), false,"VAADIN/red-circle.png")));        
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45182144466735,22.27157235145569), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.450900800789476,22.271894216537476), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Dynamo", otaTokeni(), new GoogleMapMarker("Dynamo", new LatLon(60.45045634268113,22.270435094833374), false,"VAADIN/red-circle.png")));        

        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.451250013608075,22.26904034614563), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45175795282388,22.2683322429657), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45245635627576,22.267473936080933), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Whiskey Bar", otaTokeni(), new GoogleMapMarker("Whiskey Bar", new LatLon(60.45208599267916,22.265875339508057), false,"VAADIN/red-circle.png")));        

//        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45192726413064,22.264856100082397), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.452361120325705,22.264212369918823), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.453048929245966,22.2632896900177), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45349335187866,22.261916399002075), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Puistokaljat", otaTokeni(), new GoogleMapMarker("Puistokaljat", new LatLon(60.45383195551696,22.26078987121582), false,"VAADIN/red-circle.png")));        
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45505936411316,22.261165380477905), false,"VAADIN/black-circle.png")));
        
//        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45566247030539,22.25932002067566), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45637137028135,22.261873483657837), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45702735348843,22.263718843460083), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45754578245479,22.26554274559021), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.458328700307696,22.267720699310303), false,"VAADIN/black-circle.png")));        
        this.kartta.add(new Solmu("Kaljapaikka", otaTokeni(), new GoogleMapMarker("Huoltoasemakaljat", new LatLon(60.45892116613532,22.26976990699768), false,"VAADIN/red-circle.png")));        

        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.459312610840726,22.27150797843933), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45890000682761,22.273374795913696), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.458635514317955,22.275391817092896), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu("Siltakaljat", otaTokeni(), new GoogleMapMarker("Siltakaljat", new LatLon(60.45876247099147,22.277172803878784), false,"VAADIN/red-circle.png")));

        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.459196235880704,22.280176877975464), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.45966173321825,22.281914949417114), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.460116644623156,22.283695936203003), false,"VAADIN/black-circle.png")));
        this.kartta.add(new Solmu(new GoogleMapMarker("-", new LatLon(60.4596088361296,22.285133600234985), false,"VAADIN/black-circle.png")));
     
        
        for(int i=1; i<this.kartta.size(); i++){
            this.kartta.get(i).lisaaSolmu(this.kartta.get(i-1));
        }
        this.kartta.get(0).lisaaSolmu(this.kartta.get(this.kartta.size()-1));
    }
    public void alustaTokenit(){
        Tokeni tokenisoija=new Tokeni("",0);
        Tokeni tinatuoppi=tokenisoija.luoPoliisit();
        Tokeni poliisit=tokenisoija.luoTinatuoppi();
        this.tokenit.add(tinatuoppi);
        this.tokenit.add(poliisit);
        for(int i=0; i<2; i++){
            Tokeni tuoppi=tokenisoija.luoTuoppi();
            this.tokenit.add(tuoppi);
        }
        for(int i=0; i<3; i++){
            Tokeni tyhja=tokenisoija.luoTyhja();
            this.tokenit.add(tyhja);
        }
    }
    public Tokeni otaTokeni(){
        Random r=new Random();
        int arpa=r.nextInt(this.tokenit.size());
        Tokeni token= this.tokenit.get(arpa);
        this.tokenit.remove(arpa);
        return token;
    }
    
}
