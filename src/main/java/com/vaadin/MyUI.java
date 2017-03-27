package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.StarOfTurku.src.starofturku.Noppa;
import com.StarOfTurku.src.starofturku.Pelaaja;
import com.StarOfTurku.src.starofturku.Solmu;
import com.StarOfTurku.src.starofturku.Kartta;
import main.java.com.vaadin.ApiKey;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import com.vaadin.ui.*;

import java.util.ArrayList;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    private Noppa noppa = new Noppa();
    private ApiKey key = new ApiKey();
    private Kartta kartta=new Kartta();
    private Pelaaja pelaaja= new Pelaaja("Simo", kartta.getKartta().get(0));
    private ArrayList<Solmu> sallitut;
    private ArrayList<GoogleMapMarker> sallitutMarkers = new ArrayList<>();
    private final GoogleMap googleMap = new GoogleMap(key.getKey(), null, null);
    private GoogleMapMarker pelaajaMerkki = new GoogleMapMarker(
            "Pelaaja", kartta.getKartta().get(0).getMarker().getPosition(),
        true, "VAADIN/pelaaja1.png");

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final AbsoluteLayout layout = new AbsoluteLayout();
        layout.setWidth("2000px");
        layout.setHeight("2000px");
        setContent(layout);

        googleMap.addMarker(pelaajaMerkki);
        for(Solmu s: kartta.getKartta()){
            googleMap.addMarker(s.getMarker());
        }

        googleMap.setCenter(new LatLon(60.459946, 22.287888));
        googleMap.setZoom(15);
        googleMap.setHeight("500px");
        googleMap.setWidth("650px");
        googleMap.setMinZoom(4);
        googleMap.setMaxZoom(16);

        //googleMap.setSizeFull();
        layout.setSizeFull();
        layout.addComponent(googleMap, "left: 0px; top: 0px;");

        googleMap.addMarkerClickListener(this::siirraPelaaja);

        VerticalLayout ui = new VerticalLayout();
        Panel pelaajanTiedot= new Panel("Pelaaja 1");
        //ui.addComponent(new Label("Pelaaja 1"));
        pelaajanTiedot.setWidth("200px");
        pelaajanTiedot.setHeight("80px");
        
        pelaajanTiedot.setContent(new Label("Hilpeyttä " + pelaaja.getHilpeys()));
        layout.addComponent(pelaajanTiedot, "left: 700px; top: 0px;");
        //ui.addComponent(new Label("Hilpeyttä: " +pelaaja.getHilpeys()));
        Button button = new Button("Heitä noppaa");
        Panel nopanLuvut=new Panel("Heiton tulos:");
        nopanLuvut.setWidth("150px");
        nopanLuvut.setHeight("160px");
        layout.addComponent(nopanLuvut, "left: 700px; top: 300px;");
        button.addClickListener( e -> {
            // TODO lisää jokin ehto joka estää painamasta useaan kertaan
//            ui.addComponent( new Label(Integer.toString(noppa.heita())));
            nopanLuvut.setContent(nopanKuva(noppa.heita()));
            merkkaaSallitutSolmut(noppa.getTulos());
            pelaajanTiedot.setContent(new Label("testataan "+noppa.getTulos()));
//            Window window=new Window("Testi");
//            Image image = new Image(null,
//                new ThemeResource("img/cops.png"));
//            image.setSizeUndefined();
//            window.setWidth("210px");
//            window.setHeight("320px");
//            window.setContent(image);
//            window.center();
//            this.addWindow(window);
        });
        ui.addComponent(button);
        layout.addComponent(ui, "left: 700px; top: 150px;");
    }

    private void siirraPelaaja(GoogleMapMarker clicked) {
        for(Solmu s: sallitut){
            // Jos klikattu ruutu kuuluu sallittuihin ruutuihin
            if (clicked.getPosition().equals(s.getMarker().getPosition())) {
                // Siirtää pelaaja markerin
                googleMap.removeMarker(pelaajaMerkki);
                GoogleMapMarker m = new GoogleMapMarker(
                        pelaajaMerkki.getCaption(),
                        clicked.getPosition(),
                        false,
                        pelaajaMerkki.getIconUrl());
                googleMap.addMarker(m);
                pelaajaMerkki = m;
                pelaaja.setPaikka(s);
                poistaSallitutSolmut();
            }
        }
    }

    private void merkkaaSallitutSolmut(int noppa){
        sallitut = pelaaja.sallitutSolmut(noppa);
        for(Solmu s: sallitut){
            GoogleMapMarker m = new GoogleMapMarker(
                    s.getMarker().getCaption(),
                    s.getMarker().getPosition(),
                    false,
                    "VAADIN/yellow-circle.png");
            googleMap.addMarker(m);
            sallitutMarkers.add(m);
        }
    }

    private void poistaSallitutSolmut() {
//        sallitut.clear(); //TODO estää siirtämästä pelaajaa uudestaan mutta aiheuttaa virheilmoitukset
        for(GoogleMapMarker sm: sallitutMarkers){
                googleMap.removeMarker(sm);
        }
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    private Image nopanKuva(int luku){
        if(luku==1){
            return new Image(null, new ThemeResource("img/one.png"));
        }
        if(luku==2){
            return new Image(null, new ThemeResource("img/two.png"));
        }
        if(luku==3){
            return new Image(null, new ThemeResource("img/three.png"));
        }
        if(luku==4){
            return new Image(null, new ThemeResource("img/four.png"));
        }
        if(luku==5){
            return new Image(null, new ThemeResource("img/five.png"));
        }
        if(luku==6){
            return new Image(null, new ThemeResource("img/six.png"));
        }
        return new Image(null, new ThemeResource(""));
    }
}
