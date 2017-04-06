/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author Jussi
 */
public class Pelaaja {
    private String nimi;
    private int hilpeys;
    private boolean tinatuoppi;
    private Solmu paikka;
    
    /**
     * Pelaaja alustetaan nimellä ja aloitussolmulla.
     */
    
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

    public void setPaikka(Solmu s){
        this.paikka = s;
    }

    public Solmu getPaikka() {
        return paikka;
    }

    public boolean isTinatuoppi() {
        return tinatuoppi;
    }

    public void setHilpeys(int hilpeys) {
        this.hilpeys = hilpeys;
    }
    /**
     * Päivittää pelaajan hilpeyden tokenin osoittamalla määrällä. Mikäli tokeni on tinatuoppi, metodi myös muuttaa
     * pelaajan tinatuoppi arvon muotoon true. Mikäli hilpeys menee negatiiviseksi poliisit tokenin takia, metodi palauttaa hilpeyden
     * arvoon 0.
     */
    public void paivitaHilpeys(Tokeni t){
        if(t.isTinatuoppi()){
            this.tinatuoppi=true;
        }
        this.hilpeys+=t.getArvo();
        if(this.hilpeys<0){
            this.hilpeys=0;
        }
    }
    /**
     * Palauttaa tietyn solmut, jotka ovat tietyn etäisyyden päässä pelaajan tämänhetkisestä paikasta. 
     * Parametrina käytetään nopanheiton silmälukua. 
     * @param askeleet Nopan lukema
     */
    public ArrayList<Solmu> sallitutSolmut(int askeleet){       
        if(askeleet<2){
            return this.paikka.getVierussolmut();
        }
        askeleet--;
        Queue<Solmu> jono=new LinkedList<>();
        ArrayList<Solmu> kaydyt=new ArrayList<>();
        jono.add(this.paikka);
        kaydyt.add(this.paikka);
        int laskuri=askeleet*2+1;
        Solmu tutkittava;
        while(laskuri>0){
                tutkittava=jono.remove();
            for(Solmu s:tutkittava.getVierussolmut()){
                if(tutkittava.getVierussolmut().size()>2){
                    laskuri++;
                }
                if(!kaydyt.contains(s)){
                    jono.add(s);
                }
            }
            laskuri--;
            kaydyt.add(tutkittava);
        }
        ArrayList<Solmu>palautus=new ArrayList<>();
        for(Solmu s:jono){
            palautus.add(s);
        }
        //välillä metodi ei palauta mitään, joten tällaisissa tilanteissa palautetaan alkuperäisen solmun vierussolmut
        if(palautus.isEmpty()){
            return this.paikka.getVierussolmut();
        }
        jono.clear();
        kaydyt.clear();
        return palautus;
    }

//    public void liiku(int askeleet){
//        Scanner lukija=new Scanner(System.in);
//        //tallennetaan läpikäydyt solmut, jotta ei voida mennä edestakaisin kahden solmun välillä
//        ArrayList<Solmu> kaydyt=new ArrayList<>();
//        String vastaus="l";
//        if(this.paikka.getTokeni()!=null && this.hilpeys<100){
//            System.out.println("Liikutaanko vai yritetäänkö avata? l/a");
//            vastaus=lukija.nextLine();
//        }
////        if(vastaus.equals("l")){
////            for(int i=0; i<=askeleet; i++){
////                System.out.println("Valitse paikka johon siirrytään: ");
////                for(int j=0; j<this.paikka.getVierussolmut().size(); j++){
//                    if(!kaydyt.contains(this.paikka.getVierussolmut().get(j))){
//                       System.out.println(j + ": " + this.paikka.getVierussolmut().get(j).getNimi());
//                    }
//                }
//                int valinta=Integer.parseInt(lukija.nextLine());
//                kaydyt.add(this.paikka);
//                this.paikka.setPelaaja(false);
//                this.paikka=this.paikka.getVierussolmut().get(valinta);
//                this.paikka.setPelaaja(true);
//            }
//            if(this.paikka.getTokeni()!=null){
//                System.out.println("Paikassa on tokeni. Avataanko se? k/e");
//                vastaus=lukija.nextLine();
//                if(vastaus.equals("k")){
//                    this.avaaTokeni();
//                }                
//            }
//        }
//        if(vastaus.equals("a")){            
//            avaaNopalla();
//        }
//    }
}
