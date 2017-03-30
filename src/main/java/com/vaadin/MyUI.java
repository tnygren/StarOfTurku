package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.StarOfTurku.src.starofturku.Noppa;
import com.StarOfTurku.src.starofturku.Pelaaja;
import com.StarOfTurku.src.starofturku.Solmu;
import com.StarOfTurku.src.starofturku.Kartta;
import starofturku.ApiKey;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
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
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Push(PushMode.MANUAL)
public class MyUI extends UI {
    private Noppa noppa = new Noppa();
    private ApiKey key = new ApiKey();
    private Kartta kartta = new Kartta();
    private Pelaaja pelaaja = new Pelaaja("Simo", kartta.getKartta().get(0));
    private ArrayList<Solmu> sallitut;
    private ArrayList<GoogleMapMarker> sallitutMarkers = new ArrayList<>();
    private final GoogleMap googleMap = new GoogleMap(key.getKey(), null, null);
    private final Window InfoRuutu = new Window("Pelaajatiedot");
    private static final BeanItemContainer<Pelaaja> beanPelaaja = new BeanItemContainer<>(Pelaaja.class);
    private Panel tokeni;
    private Panel pelaajanTiedot;
    private GoogleMapMarker pelaajaMerkki;
    private VerticalLayout ui = new VerticalLayout();
    private GridLayout keratytTokenit = new GridLayout(4, 4);

    private int lastMessage = 0;
    private int vuoro = 1;

    public MyUI() {
        super();
        beanPelaaja.addItemSetChangeListener(this::messagesChanged);
    }

    @Override
    public void close() {
        beanPelaaja.removeItemSetChangeListener(this::messagesChanged);
        super.close();
    }

    private void messagesChanged(Container.ItemSetChangeEvent event) {
        this.access(() -> {
            List<Pelaaja> messages = beanPelaaja.getItemIds();
            for (; lastMessage < messages.size(); lastMessage++) {

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
                        Notification notif = new Notification(null, Notification.TYPE_HUMANIZED_MESSAGE);
                        notif.setPosition(Position.MIDDLE_CENTER);
                        notif.setIcon(new ThemeResource(pelaaja.getPaikka().getTokeni().getBigIcon()));
                        notif.setDelayMsec(3000);
                        notif.show(Page.getCurrent());
                        //ui.addComponent(notif);
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

    // TODO lisää "Aloita uusi peli"-nappi ??
    private void asetaKartta() {
        pelaajaMerkki = new GoogleMapMarker("Pelaaja",
                kartta.getKartta().get(0).getMarker().getPosition(),
                true,
                "VAADIN/pelaaja1.png");
        googleMap.addMarker(pelaajaMerkki);
        for (Solmu s : kartta.getKartta()) {
            googleMap.addMarker(s.getMarker());
        }
        googleMap.setCenter(new LatLon(60.456308, 22.28508));
        googleMap.setZoom(15);
        googleMap.setMinZoom(4);
        googleMap.setMaxZoom(16);
        googleMap.setSizeFull();
        googleMap.addMarkerClickListener(this::siirraPelaaja);
    }

    // TODO lisää joku Vaadin Theme
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
        button.setClickShortcut(ShortcutAction.KeyCode.N); // TODO ei toimi kokoruudussa
        button.setDescription("Pikanäppäin: N");
        button.addClickListener(e -> {
            if (vuoro==1) {
                noppaSound.play();
                nopanLuvut.setContent(nopanKuva(noppa.heita()));
                merkkaaSallitutSolmut(noppa.getTulos()); // TODO merkkaa edelleen joskus vääriä merkkejä
                vuoro = 0;
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
                    beanPelaaja.addBean(new Pelaaja(null, s));
                }
            }
            poistaSallitutSolmut();
            vuoro = 1;
        }
    }

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

    private void poistaSallitutSolmut() {
        sallitut.clear();
        for (GoogleMapMarker sm : sallitutMarkers) {
            googleMap.removeMarker(sm);
        }
    }

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
