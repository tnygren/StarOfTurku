package com.vaadin;

import javax.servlet.annotation.WebServlet;
import com.StarOfTurku.src.starofturku.Solmu;
import com.StarOfTurku.src.starofturku.Noppa;
import com.StarOfTurku.src.starofturku.Kartta;
import com.StarOfTurku.src.starofturku.Pelaaja;
import com.StarOfTurku.src.starofturku.BFS;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicMarkableReference;

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

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        final GoogleMap googleMap = new GoogleMap("AIzaSyCAElLsCHW5qLZ4r6SfdOT93dp196r0qUQ", null, null);
        GoogleMapMarker markA = new GoogleMapMarker(
                "laatta", new LatLon(60.450043,22.277698), false, "VAADIN/red-circle.png");
        markA.setAnimationEnabled(false);
        googleMap.addMarker(markA);
        Kartta kartta=new Kartta();
        for(Solmu s: kartta.getKartta()){
            GoogleMapMarker m= s.getMarker();
            googleMap.addMarker(m);
        }
        Pelaaja pelaaja=new Pelaaja("Pekka", kartta.getKartta().get(0));
//        BFS testi= new BFS(kartta.getKartta().get(0),3);
        ArrayList<Solmu> solmut=pelaaja.sallitutSolmut(4);
        for(Solmu s: solmut){
            GoogleMapMarker marker=s.getMarker();
            marker.setIconUrl("VAADIN/tinakuppi.jpg");
        }

////        GoogleMapMarker markB = new GoogleMapMarker(
//                "ruutu", new LatLon(60.451419,22.275509), false, "VAADIN/black-circle.png");
//        markB.setAnimationEnabled(false);
//        googleMap.addMarker(markB);
//        GoogleMapMarker markC = new GoogleMapMarker(
//                "ruutu", new LatLon(60.452519,22.273621), false, "VAADIN/black-circle.png");
//        markC.setAnimationEnabled(false);
//        googleMap.addMarker(markC);
//
        GoogleMapMarker pelaaja1 = new GoogleMapMarker(
                "pelaaja", new LatLon(60.452519,22.273621), true, "VAADIN/pelaaja1.png");
        pelaaja1.setAnimationEnabled(true);
        googleMap.addMarker(pelaaja1);
        GoogleMapMarker tinakuppi = new GoogleMapMarker(
                "palkinto", new LatLon(60.450043,22.277698), false, "VAADIN/tinakuppi.jpg");
        tinakuppi.setAnimationEnabled(false);

        googleMap.setCenter(new LatLon(60.451948, 22.275295));
        googleMap.setZoom(15);
        googleMap.setHeight("500px");
        googleMap.setWidth("650px");
        googleMap.setMinZoom(4);
        googleMap.setMaxZoom(16);

//        ArrayList<LatLon> points = new ArrayList<LatLon>();
//        points.add(new LatLon(60.450043,22.277698));
//        points.add(new LatLon(60.451419,22.275509));
//        points.add(new LatLon(60.452519,22.273621));
//        points.add(new LatLon(60.451906,22.271218));
//
//        GoogleMapPolyline overlay = new GoogleMapPolyline(
//                points, "#d31717", 0.8, 8);
//        googleMap.addPolyline(overlay);

        googleMap.addMarkerClickListener(new MarkerClickListener() {
            @Override
            public void markerClicked(GoogleMapMarker clickedMarker) {
                if (clickedMarker.getCaption().equalsIgnoreCase("pelaaja")) {
                    googleMap.removeMarker(clickedMarker);
                    pelaaja1.setPosition(new LatLon(60.451419, 22.275509));
                    googleMap.addMarker(pelaaja1);
                }

                if (clickedMarker.getCaption().equalsIgnoreCase("laatta")) {
                    googleMap.removeMarker(clickedMarker);
                    googleMap.addMarker(tinakuppi);
                }
            }
        });

        layout.addComponent(googleMap);

        Button button = new Button("Heitä noppaa");
        button.addClickListener( e -> {
            int heitto=noppa.heita();
            layout.addComponent(new Label(Integer.toString(heitto)));
//            ArrayList<Solmu> solmut= pelaaja.sallitutSolmut(heitto);
//            for(Solmu s: solmut){
//                GoogleMapMarker marker=s.getMarker();
//                marker.setIconUrl("VAADIN/tinakuppi.jpg");
//            }
        });
        layout.addComponent(button);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
