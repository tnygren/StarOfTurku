package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.StarOfTurku.src.starofturku.Noppa;
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
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        final GoogleMap googleMap = new GoogleMap("apiKey", null, null);
        GoogleMapMarker koti1= new GoogleMapMarker(
                "koti", new LatLon(60.451863,22.282290), false, "VAADIN/red-circle.png");
        koti1.setAnimationEnabled(false);
        googleMap.addMarker(koti1);

        GoogleMapMarker laatta1= new GoogleMapMarker(
                "laatta", new LatLon(60.450043,22.277698), false, "VAADIN/red-circle.png");
        laatta1.setAnimationEnabled(false);
        googleMap.addMarker(laatta1);
        GoogleMapMarker laatta2= new GoogleMapMarker(
                "laatta", new LatLon(60.453683,22.270917), false, "VAADIN/red-circle.png");
        laatta2.setAnimationEnabled(false);
        googleMap.addMarker(laatta2);
        GoogleMapMarker laatta3= new GoogleMapMarker(
                "laatta", new LatLon(60.450160,22.273664), false, "VAADIN/red-circle.png");
        laatta3.setAnimationEnabled(false);
        googleMap.addMarker(laatta3);

        GoogleMapMarker ruutu1 = new GoogleMapMarker(
                "ruutu", new LatLon(60.451461,22.281088), false, "VAADIN/black-circle.png");
        ruutu1.setAnimationEnabled(false);
        googleMap.addMarker(ruutu1);
        GoogleMapMarker ruutu2 = new GoogleMapMarker(
                "ruutu", new LatLon(60.450879,22.279307), false, "VAADIN/black-circle.png");
        ruutu2.setAnimationEnabled(false);
        googleMap.addMarker(ruutu2);

        GoogleMapMarker ruutu3 = new GoogleMapMarker(
                "ruutu", new LatLon(60.450096,22.276883), false, "VAADIN/black-circle.png");
        ruutu3.setAnimationEnabled(false);
        googleMap.addMarker(ruutu3);
        GoogleMapMarker ruutu4 = new GoogleMapMarker(
                "ruutu", new LatLon(60.450128,22.275059), false, "VAADIN/black-circle.png");
        ruutu4.setAnimationEnabled(false);
        googleMap.addMarker(ruutu4);

        GoogleMapMarker ruutu5 = new GoogleMapMarker(
                "ruutu", new LatLon(60.450784,22.272870), false, "VAADIN/black-circle.png");
        ruutu5.setAnimationEnabled(false);
        googleMap.addMarker(ruutu5);
        GoogleMapMarker ruutu6 = new GoogleMapMarker(
                "ruutu", new LatLon(60.451260,22.272419), false, "VAADIN/black-circle.png");
        ruutu6.setAnimationEnabled(false);
        googleMap.addMarker(ruutu6);
        GoogleMapMarker ruutu7 = new GoogleMapMarker(
                "ruutu", new LatLon(60.451630,22.271840), false, "VAADIN/black-circle.png");
        ruutu7.setAnimationEnabled(false);
        googleMap.addMarker(ruutu7);
        GoogleMapMarker ruutu8 = new GoogleMapMarker(
                "ruutu", new LatLon(60.452149,22.271196), false, "VAADIN/black-circle.png");
        ruutu8.setAnimationEnabled(false);
        googleMap.addMarker(ruutu8);
        GoogleMapMarker ruutu9 = new GoogleMapMarker(
                "ruutu", new LatLon(60.452646,22.270681), false, "VAADIN/black-circle.png");
        ruutu9.setAnimationEnabled(false);
        googleMap.addMarker(ruutu9);

        GoogleMapMarker ruutu10 = new GoogleMapMarker(
                "ruutu", new LatLon(60.453609,22.272720), false, "VAADIN/black-circle.png");
        ruutu10.setAnimationEnabled(false);
        googleMap.addMarker(ruutu10);
        GoogleMapMarker ruutu11 = new GoogleMapMarker(
                "ruutu", new LatLon(60.453334,22.273128), false, "VAADIN/black-circle.png");
        ruutu11.setAnimationEnabled(false);
        googleMap.addMarker(ruutu11);
        GoogleMapMarker ruutu12 = new GoogleMapMarker(
                "ruutu", new LatLon(60.452890,22.273900), false, "VAADIN/black-circle.png");
        ruutu12.setAnimationEnabled(false);
        googleMap.addMarker(ruutu12);
        GoogleMapMarker ruutu13 = new GoogleMapMarker(
                "ruutu", new LatLon(60.452583,22.274458), false, "VAADIN/black-circle.png");
        ruutu13.setAnimationEnabled(false);
        googleMap.addMarker(ruutu13);
        GoogleMapMarker ruutu14 = new GoogleMapMarker(
                "ruutu", new LatLon(60.452191,22.275037), false, "VAADIN/black-circle.png");
        ruutu14.setAnimationEnabled(false);
        googleMap.addMarker(ruutu14);
        GoogleMapMarker ruutu15 = new GoogleMapMarker(
                "ruutu", new LatLon(60.451705,22.275660), false, "VAADIN/black-circle.png");
        ruutu15.setAnimationEnabled(false);
        googleMap.addMarker(ruutu15);
        GoogleMapMarker ruutu16 = new GoogleMapMarker(
                "ruutu", new LatLon(60.451218,22.276561), false, "VAADIN/black-circle.png");
        ruutu16.setAnimationEnabled(false);
        googleMap.addMarker(ruutu16);

//        GoogleMapMarker pelaaja1 = new GoogleMapMarker(
//                "pelaaja", new LatLon(60.452519,22.273621), true, "VAADIN/pelaaja1.png");
//        pelaaja1.setAnimationEnabled(true);
//        googleMap.addMarker(pelaaja1);
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
