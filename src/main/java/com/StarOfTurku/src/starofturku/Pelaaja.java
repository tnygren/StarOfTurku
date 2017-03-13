/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.StarOfTurku.src.starofturku;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Jussi
 */
public class Pelaaja {
    private String nimi;
    private int hilpeys;
    private boolean tinatuoppi;
    private Solmu paikka;
    
    //pelaaja alustetaan nimellä ja aloitussolmulla
    public Pelaaja(String nimi, Solmu paikka) {
        this.nimi = nimi;
        this.hilpeys=500;
        this.tinatuoppi= false;
        this.paikka= paikka;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getHilpeys() {
        return hilpeys;
    }

    public void setHilpeys(int hilpeys) {
        this.hilpeys = hilpeys;
    }
    //Poliisi-tokeni pienentää pelaajan hilpeyden nollaan, joten jos hilpeys saa negatiivisen arvon, palautetaan se nollaan
    public void paivitaHilpeys(Tokeni t){
        this.hilpeys+=t.getArvo();
        if(this.hilpeys<0){
            this.hilpeys=0;
        }
    }
    //tokenin avaaminen maksaa 100 hilpeyttä
    public void avaaTokeni(){
        if(this.hilpeys<100){
            System.out.println("Herra on nyt sen näköinen, että hänelle ei enää tarjoilla");
        }else{
            this.hilpeys-=100;
            System.out.println("Löysit tokenin: " + this.paikka.getTokeni());
            Tokeni temp= this.paikka.poistaTokeni();
            this.hilpeys+=temp.getArvo();
        }
    }
    public void avaaNopalla(){
        Noppa n=new Noppa();
        int arpa=n.heita();
        System.out.print("Heitit " + arpa);
        if(arpa==6){
            System.out.println("Onnistuit! Löysit tokenin: " + this.paikka.getTokeni());
            Tokeni temp= this.paikka.poistaTokeni();
            this.hilpeys+=temp.getArvo();            
        }else{
            System.out.println("Ei onnistunut.. Yritä seuraavalla kierroksella uudelleen.");
        }        
    }
    public void liiku(int askeleet){
        Scanner lukija=new Scanner(System.in);
        //tallennetaan läpikäydyt solmut, jotta ei voida mennä edestakaisin kahden solmun välillä
        ArrayList<Solmu> kaydyt=new ArrayList<>();
        String vastaus="l";
        if(this.paikka.getTokeni()!=null && this.hilpeys<100){
            System.out.println("Liikutaanko vai yritetäänkö avata? l/a");
            vastaus=lukija.nextLine();
        }
        if(vastaus.equals("l")){
            for(int i=0; i<=askeleet; i++){
                System.out.println("Valitse paikka johon siirrytään: ");
                for(int j=0; j<this.paikka.getVierussolmut().size(); j++){
                    if(!kaydyt.contains(this.paikka.getVierussolmut().get(j))){
                       System.out.println(j + ": " + this.paikka.getVierussolmut().get(j).getNimi());
                    }
                }
                int valinta=Integer.parseInt(lukija.nextLine());
                kaydyt.add(this.paikka);
                this.paikka.setPelaaja(false);
                this.paikka=this.paikka.getVierussolmut().get(valinta);
                this.paikka.setPelaaja(true);
            }
            if(this.paikka.getTokeni()!=null){
                System.out.println("Paikassa on tokeni. Avataanko se? k/e");
                vastaus=lukija.nextLine();
                if(vastaus.equals("k")){
                    this.avaaTokeni();
                }                
            }
        }
        if(vastaus.equals("a")){            
            avaaNopalla();
        }
    }
}