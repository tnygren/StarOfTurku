package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Position;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Tämä on sovelluksen alkamiskohta. UI voi olla verkkoselaimen ikkuna (tai välilehti) tai joku muu
 * osa html sivua mihin Vaadin sovellus on sulautettu.
 */
@Theme("mytheme")
@Push(PushMode.MANUAL)
public class MyUI extends UI {
    private String avain = "ApiKey";
    private Noppa noppa = new Noppa();
    private Kartta kartta = new Kartta();
    private Pelaaja pelaaja = new Pelaaja("Mauri Antero", kartta.getKartta().get(0));
    private ArrayList<Solmu> sallitut;
    private ArrayList<GoogleMapMarker> sallitutMarkers = new ArrayList<>();
    private final GoogleMap googleMap = new GoogleMap(avain, null, null);
    private final Window InfoRuutu = new Window("Pelaajatiedot");
    private static final BeanItemContainer<Pelaaja> beanPelaaja = new BeanItemContainer<>(Pelaaja.class);
    private Panel tokeni;
    private Panel pelaajanTiedot;
    private GoogleMapMarker pelaajaMerkki;
    private VerticalLayout ui = new VerticalLayout();
    private GridLayout keratytTokenit = new GridLayout(4, 4);

    private int lastMessage = 0;

    /**
     * Lisää tapahtumakäsittelijän
     */
    public MyUI() {
        super();
        beanPelaaja.addItemSetChangeListener(this::pelaajaChanged);
    }

    /**
     * Poistaa tapahtumakäsittelijän
     */
    @Override
    public void close() {
        beanPelaaja.removeItemSetChangeListener(this::pelaajaChanged);
        super.close();
    }

    /**
     * Tapahtumakäsittelijä pelaaja-luokan muutoksille
     *
     * @param event Muutostapahtuma
     */
    private void pelaajaChanged(Container.ItemSetChangeEvent event) {
        this.access(() -> {
            List<Pelaaja> messages = beanPelaaja.getItemIds();
            for (; lastMessage < messages.size(); lastMessage++) {

                // Ollaanko kotiruudussa tinatuopin kanssa
                if (pelaaja.isTinatuoppi() && pelaaja.getPaikka().getNimi() == "Yo-kylä") {
                    BrowserFrame voitto= new BrowserFrame("", new ExternalResource("https://www.youtube.com/embed/h4QTXZMFcos?rel=0&autoplay=1"));
                    voitto.setWidth("0px");
                    voitto.setHeight("0px");
                    ui.addComponent(voitto);
                    Notification notif = new Notification(null, null, Notification.Type.ERROR_MESSAGE);
                    notif.setPosition(Position.MIDDLE_CENTER);
                    notif.setIcon(new ThemeResource("img/wincondition.jpg"));
                    notif.show(Page.getCurrent());
                }

                // Ollaaanko ruudussa missä on token
                if (pelaaja.getPaikka().getTokeni() != null) {
                    InfoRuutu.setModal(true);
                    VerticalLayout TokenKysymys = new VerticalLayout();
                    TokenKysymys.addComponent(new Label("Haluatko kääntää laatan?"));
                    Button kylla = new Button("Kyllä");
                    TokenKysymys.addComponent(kylla);
                    Button en = new Button("En");
                    TokenKysymys.addComponent(en);
                    ui.addComponent(TokenKysymys);

                    kylla.addClickListener(e -> {
                        Audio tokeniSound = new Audio(null, new ThemeResource(pelaaja.getPaikka().getTokeni().getAudio()));
                        tokeniSound.setShowControls(false);
                        tokeniSound.setSizeUndefined();
                        Notification notif = new Notification(null, null, Notification.Type.HUMANIZED_MESSAGE);
                        notif.setPosition(Position.MIDDLE_CENTER);
                        notif.setIcon(new ThemeResource(pelaaja.getPaikka().getTokeni().getBigIcon()));
                        notif.setDelayMsec(3000);
                        notif.show(Page.getCurrent());
                        ui.addComponent(tokeniSound);
                        ui.setExpandRatio(tokeniSound, 0);
                        tokeniSound.play();
                        tokeni.setContent(new Label(pelaaja.getPaikka().getTokeni().getNimi()));
                        Image tokeninKuva = new Image();
                        tokeninKuva.setSource(new ThemeResource(pelaaja.getPaikka().getTokeni().getSmallIcon()));
                        keratytTokenit.addComponent(tokeninKuva);
                        pelaaja.paivitaHilpeys(pelaaja.getPaikka().getTokeni());
                        pelaaja.getPaikka().setTokeni(null);
                        pelaajanTiedot.setContent(new Label("Hilpeyttä " + pelaaja.getHilpeys()));
                        pelaaja.getPaikka().getMarker().setIconUrl("VAADIN/bigblack-circle.png");
                        ui.removeComponent(TokenKysymys);
                        InfoRuutu.setModal(false);
                        InfoRuutu.setPosition(10, 60);
                    });

                    en.addClickListener(e -> {
                        ui.removeComponent(TokenKysymys);
                        InfoRuutu.setModal(false);
                        InfoRuutu.setPosition(10, 60);
                    });
                }
                push();
            }
        });
    }

    /**
     * Rakentaa ja konfiguroi UI näkymän.
     * Ylikirjoittaa UI-luokan init() metodin.
     */
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        setContent(layout);
        asetaKartta();
        asetaIkkuna();
        layout.getUI().addWindow(InfoRuutu);
        layout.addComponent(googleMap);
    }

    /**
     * Konfiguroi Google Maps asetukset.
     * Lisää siihen pelilaudan ruudut sekä pelaajaa kuvaavan merkin.
     */
    private void asetaKartta() {
        pelaajaMerkki = new GoogleMapMarker("Pelaaja",
                kartta.getKartta().get(0).getMarker().getPosition(),
                true,
                "VAADIN/pelaaja1.png");
        googleMap.addMarker(pelaajaMerkki);
        for (Solmu s : kartta.getKartta()) {
            googleMap.addMarker(s.getMarker());
        }
        googleMap.setCenter(new LatLon(60.458963, 22.289));
        googleMap.setZoom(16);
        googleMap.setMinZoom(4);
        googleMap.setMaxZoom(16);
        googleMap.setSizeFull();
        googleMap.addMarkerClickListener(this::siirraPelaaja);
    }

    /**
     * Konfiguroi pelitapahtumia näyttävät ikkunan asetukset.
     */
    private void asetaIkkuna() {
        pelaajanTiedot = new Panel("Pelaaja 1");
        pelaajanTiedot.setWidth("200px");
        pelaajanTiedot.setHeight("80px");
        pelaajanTiedot.setContent(new Label("Hilpeyttä " + pelaaja.getHilpeys()));
        ui.addComponent(pelaajanTiedot);
        Audio noppaSound = new Audio(null, new ThemeResource("audio/dice-roll.wav"));
        noppaSound.setShowControls(false);
        noppaSound.setSizeUndefined();
        ui.addComponent(noppaSound);
        ui.setExpandRatio(noppaSound, 0);
        tokeni = new Panel("löydetyt laatat");
        tokeni.setWidth("200px");
        tokeni.setHeight("80px");
        ui.addComponent(tokeni);
        ui.addComponent(keratytTokenit);

        Panel nopanLuvut = new Panel("Heiton tulos:");
        nopanLuvut.setWidth("200px");
        nopanLuvut.setHeight("160px");
        ui.addComponent(nopanLuvut);

        Button button = new Button("Heitä noppaa");
        button.addClickListener(e -> {
            if (sallitutMarkers.isEmpty()) {
                noppaSound.play();
                nopanLuvut.setContent(nopanKuva(noppa.heita()));
                merkkaaSallitutSolmut(noppa.getTulos());
            }
        });
        ui.addComponent(button);
        ui.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        InfoRuutu.setWidth(200.0f, Unit.PIXELS);
        InfoRuutu.setStyleName("ui");
        InfoRuutu.setClosable(false);
        InfoRuutu.setPosition(10, 60);
        InfoRuutu.setContent(ui);
    }

    /**
     * Siirtää pelaajamerkin pelilaudalla hiirellä napsautettuun paikkaan.
     *
     * @param clicked Klikattu peliruutu
     */
    private void siirraPelaaja(GoogleMapMarker clicked) {
        Audio moveSound = new Audio(null, new ThemeResource("audio/move.mp3"));
        moveSound.setShowControls(false);
        moveSound.setSizeUndefined();
        ui.addComponent(moveSound);
        ui.setExpandRatio(moveSound, 0);
        if (!sallitut.isEmpty()) {
            for (Solmu s : sallitut) {
                // Jos klikattu ruutu kuuluu sallittuihin ruutuihin
                if (clicked.getPosition().equals(s.getMarker().getPosition())) {
                    // Siirtää pelaaja markerin
                    moveSound.play();
                    googleMap.removeMarker(pelaajaMerkki);
                    GoogleMapMarker m = new GoogleMapMarker(
                            pelaajaMerkki.getCaption(),
                            clicked.getPosition(),
                            false,
                            pelaajaMerkki.getIconUrl());
                    googleMap.addMarker(m);
                    pelaajaMerkki = m;
                    pelaaja.setPaikka(s);
                    googleMap.setCenter(pelaaja.getPaikka().getMarker().getPosition());
                    beanPelaaja.addBean(new Pelaaja(null, s));
                }
            }
            poistaSallitutSolmut();
        }
    }

    /**
     * Merkitsee pelilaudalle nopan lukeman mukaisen sallittujen siirtojen ruudut.
     *
     * @param noppa Nopan lukema
     */
    private void merkkaaSallitutSolmut(int noppa) {
        sallitut = pelaaja.sallitutSolmut(noppa);
        for (Solmu s : sallitut) {
            GoogleMapMarker m = new GoogleMapMarker(
                    s.getMarker().getCaption(),
                    s.getMarker().getPosition(),
                    false,
                    "VAADIN/yellow-circle.png");
            googleMap.addMarker(m);
            sallitutMarkers.add(m);
        }
    }

    /**
     * Poistaa kartalta sallittujen siirtojen merkit
     */
    private void poistaSallitutSolmut() {
        sallitut.clear();
        for (GoogleMapMarker sm : sallitutMarkers) {
            googleMap.removeMarker(sm);
        }
        sallitutMarkers.clear();
    }

    /**
     * Nopan lukemien kuvat
     *
     * @param luku Nopan lukema
     * @return Nopan lukemaa vastaava kuva nopasta
     */
    private Image nopanKuva(int luku) {
        if (luku == 1) {
            return new Image(null, new ThemeResource("img/one.png"));
        }
        if (luku == 2) {
            return new Image(null, new ThemeResource("img/two.png"));
        }
        if (luku == 3) {
            return new Image(null, new ThemeResource("img/three.png"));
        }
        if (luku == 4) {
            return new Image(null, new ThemeResource("img/four.png"));
        }
        if (luku == 5) {
            return new Image(null, new ThemeResource("img/five.png"));
        }
        if (luku == 6) {
            return new Image(null, new ThemeResource("img/six.png"));
        }
        return new Image(null, new ThemeResource(""));
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
