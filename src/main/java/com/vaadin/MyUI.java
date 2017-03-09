package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        final GoogleMap googleMap = new GoogleMap("apiKey", null, null);
        googleMap.setCenter(new LatLon(60.451948, 22.275295));
        googleMap.setZoom(15);
        googleMap.setHeight("500px");
        googleMap.setWidth("650px");
        googleMap.addMarker("a", new LatLon(
                60.450043,22.277698), false, "VAADIN/red-circle.png");
        googleMap.addMarker("b", new LatLon(
                60.451419,22.275509), false, "VAADIN/black-circle.png");
        googleMap.addMarker("c", new LatLon(
                60.452519,22.273621), false, "VAADIN/black-circle.png");
        googleMap.setMinZoom(4);
        googleMap.setMaxZoom(16);

        ArrayList<LatLon> points = new ArrayList<LatLon>();
        points.add(new LatLon(60.450043,22.277698));
        points.add(new LatLon(60.451419,22.275509));
        points.add(new LatLon(60.452519,22.273621));
        points.add(new LatLon(60.451906,22.271218));

        GoogleMapPolyline overlay = new GoogleMapPolyline(
                points, "#d31717", 0.8, 8);
        googleMap.addPolyline(overlay);

        layout.addComponent(googleMap);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
