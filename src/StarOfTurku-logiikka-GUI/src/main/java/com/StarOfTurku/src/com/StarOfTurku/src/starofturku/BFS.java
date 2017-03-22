
package com.StarOfTurku.src.starofturku;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 *
 * @author Jussi
 */
public class BFS {
    private Solmu aloitus;
    private int askeleet;

    public BFS(Solmu aloitus, int askeleet) {
        this.aloitus = aloitus;
        this.askeleet = askeleet;
    }
    public ArrayList<Solmu> laske(){
        if(askeleet<2){
            return this.aloitus.getVierussolmut();
        }
        Queue<Solmu> jono=new LinkedList<>();
        ArrayList<Solmu> kaydyt=new ArrayList<>();
        jono.add(this.aloitus);
        kaydyt.add(this.aloitus);
        for(int i=0; i<(askeleet*2+1); i++){
            Solmu tutkittava=jono.remove();
            for(Solmu s:tutkittava.getVierussolmut()){
                if(!kaydyt.contains(s)){
                    jono.add(s);
                }
            }
            kaydyt.add(tutkittava);
        }
        ArrayList<Solmu>palautus=new ArrayList<>();
        for(Solmu s:jono){
            palautus.add(s);
        }
        return palautus;
    }
    
}
