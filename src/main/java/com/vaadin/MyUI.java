package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.StarOfTurku.src.starofturku.Noppa;
import com.StarOfTurku.src.starofturku.Pelaaja;
import com.StarOfTurku.src.starofturku.Solmu;
import com.StarOfTurku.src.starofturku.Kartta;
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
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        googleMap.addMarker(pelaajaMerkki);
        for(Solmu s: kartta.getKartta()){
            googleMap.addMarker(s.getMarker());
        }

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

//        googleMap.addMarkerClickListener(new MarkerClickListener() {
//            @Override
//            public void markerClicked(GoogleMapMarker clickedMarker) {
//                if (clickedMarker.getCaption().equalsIgnoreCase("pelaaja")) {
//                    googleMap.removeMarker(clickedMarker);
//                    pelaaja1.setPosition(new LatLon(60.451419, 22.275509));
//                    googleMap.addMarker(pelaaja1);
//                }
//
//                if (clickedMarker.getCaption().equalsIgnoreCase("laatta")) {
//                    googleMap.removeMarker(clickedMarker);
//                    googleMap.addMarker(tinakuppi);
//                }
//            }
//        });

        googleMap.setSizeFull();
        layout.setSizeFull();
        layout.addComponent(googleMap);

        VerticalLayout ui = new VerticalLayout();
        ui.addComponent(new Label("Pelaaja 1"));
        ui.addComponent(new Label("Pisteet 1000"));
        Button button = new Button("Heitä noppaa");
        button.addClickListener( e -> {
            ui.addComponent(new Label(Integer.toString(noppa.heita())));
        });
        ui.addComponent(button);
        layout.addComponent(ui);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
