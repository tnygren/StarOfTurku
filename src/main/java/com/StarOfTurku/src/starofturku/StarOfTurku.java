/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starofturku;

/**
 *
 * @author Jussi
 */
public class StarOfTurku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tokeni tokenisoija=new Tokeni("",0);
        Solmu hyvinkaa=new Solmu("Hyvinkaa", tokenisoija.luoTinatuoppi());
        hyvinkaa.setPelaaja(true);
        Solmu siirtyma= new Solmu("Jaa", tokenisoija.luoTyhja());
        Solmu yksi= new Solmu("Ploo" ,tokenisoija.luoTyhja());
        Solmu kaksi= new Solmu();
        Solmu kolme= new Solmu();
        Solmu nelja= new Solmu();
        hyvinkaa.lisaaSolmu(kaksi);
        kaksi.lisaaSolmu(siirtyma);
        siirtyma.lisaaSolmu(kolme);
        siirtyma.lisaaSolmu(yksi);
        yksi.lisaaSolmu(nelja);
        Pelaaja p=new Pelaaja("Pekka",hyvinkaa);
        p.liiku(1);
        if(hyvinkaa.julkinenLiikenne(siirtyma)){
            System.out.println("Hep!");        
        
    
        }
    }
}
