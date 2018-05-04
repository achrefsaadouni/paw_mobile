/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.veterinaire;

import Entity.Veterinaire;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;
import service.ServiceVeterinaire;
import com.codename1.googlemaps.MapContainer;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import static com.mycompany.myapp.MyApplication.membre;

/**
 *
 * @author gmehd
 */
public class AfficheVeterinaire {

    Form f, current, hi;
    SpanLabel lb;
    String x;
    
    public AfficheVeterinaire() {

        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        ServiceVeterinaire service = new ServiceVeterinaire();
        //lb.setText(service.getList2().toString());
        ArrayList<Veterinaire> lv = service.getList2();
        for (Veterinaire vet : lv) {
            f.add(addItem(vet));
        }

        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            VeterinaireForm h = new VeterinaireForm();
            h.getF().show();

        });
        f.show();
    }

    public Container addItem(Veterinaire v) {
        Container c = new Container(BoxLayout.x());
        Container c1 = new Container(BoxLayout.x());
        Container c2 = new Container(BoxLayout.x());
        Container c3 = new Container(BoxLayout.x());
        Container c4 = new Container(BoxLayout.x());
        Container c5 = new Container(BoxLayout.x());
        Container c6 = new Container(BoxLayout.x());
        Container c7 = new Container(BoxLayout.x());
        Container c8 = new Container(BoxLayout.x());

        Container cnt1 = new Container(BoxLayout.y());
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(250, 250), true);
        ImageViewer i = new ImageViewer();
        i.setImage(URLImage.createToStorage(placeholder, v.getImages(), "http://localhost/paw_z/web/images/pawVets/" + v.getImages()));

        Label a = new Label("      Nom : ");
        Label lbnom = new Label(v.getNom());

        Label b = new Label(" Prenom : ");
        Label lbprenom = new Label(v.getPrenom());

        Label x = new Label("     Email : ");
        Label lbmail = new Label(v.getEmail());

        Label d = new Label("Adresse : ");
        Label lbadresse = new Label(v.getAdresse());

        Label e = new Label("  Region : ");
        Label lbregion = new Label(v.getRegion());

        Label f = new Label("Numero : ");
        Label lbnumero = new Label("" + v.getNumero());

        Button btn = new Button("Afficher position");
        
        Button btn1 = new Button("Contacter");
        
        
        
        btn1.addActionListener(l -> {
            Message m = new Message("Bonjour Dr. "+v.getPrenom() + " " + v.getNom());
            
            Display.getInstance().sendMessage(new String[]{v.getEmail()}, "PI", m);
        });
        
        btn.addActionListener(l -> {
//            Position p = new Position();
//            p.getF().show();

            

            if (current != null) {
                current.show();
                return;
            }
            hi = new Form(v.getPrenom() + " " + v.getNom());
            hi.setLayout(new BorderLayout());
            final MapContainer cnt = new MapContainer();

            cnt.addLongPressListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    Coord curPosition = cnt.getCoordAtPosition(evt.getX(), evt.getY());
                    setMarker(cnt, curPosition);
                }
            });

            hi.addComponent(BorderLayout.CENTER, cnt);
            // set camera to the current position
            setCurrentPosition(cnt, v.getLatitude(), v.getLongitude());

            cnt.zoom(cnt.getCameraPosition(), 10);
            hi.getToolbar().addCommandToRightBar("back", null, (ev)
                    -> {
                AfficheVeterinaire h = new AfficheVeterinaire();

                //h.show();
            });

            hi.show();

        });

        c.add(i);
        c1.add(a);
        c1.add(lbnom);
        c2.add(b);
        c2.add(lbprenom);
        c3.add(x);
        c3.add(lbmail);
        c4.add(d);
        c4.add(lbadresse);
        c5.add(e);
        c5.add(lbregion);
        c6.add(f);
        c6.add(lbnumero);
        c7.add(btn);
        c7.add(btn1);
        
        //c8.add();

        cnt1.add(FlowLayout.encloseCenter(c));
        cnt1.add(FlowLayout.encloseCenter(createStarRankSlider(v.getId())));
        cnt1.add(c1);
        cnt1.add(c2);
        cnt1.add(c3);
        cnt1.add(c4);
        cnt1.add(c5);
        cnt1.add(c6);
        cnt1.add(FlowLayout.encloseCenter(c7));

        return cnt1;
    }

    private void setCurrentPosition(MapContainer cnt, double lat, double longi) {
        Location position = LocationManager.getLocationManager().getCurrentLocationSync();

        if (position != null) {
            Coord c = new Coord(lat, longi);
            cnt.setCameraPosition(c);
            setMarker(cnt, c);

        }
    }

    public void setMarker(MapContainer cnt, Coord c) {
        try {
            cnt
                    .addMarker(EncodedImage.create("/maps-pin.png"), c, "Marker At", "Latddd ", new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            Command back = new Command("Back") {
                                public void actionPerformed(ActionEvent ev) {
                                    hi.showBack();
                                }
                            };
                        }
                    });
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider(int id) {
        Slider starRank = new Slider();

        starRank.setEditable(true);

        starRank.setMinValue(1);
        starRank.setMaxValue(6);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));

        //starRank.setProgress(3);
        starRank.addDataChangedListener((type, index) -> {
            ServiceVeterinaire se = new ServiceVeterinaire();
            se.rating(starRank.getProgress(), membre.getId(), id);
            if ((starRank.getProgress() > 1.0) && (starRank.getProgress() < 2.0)) {
                starRank.setProgress(1);

            }

            if ((starRank.getProgress() > 2.0) && (starRank.getProgress() < 3.0)) {
                starRank.setProgress(2);
            }

            if ((starRank.getProgress() > 3.0) && (starRank.getProgress() <4.0)) {
                starRank.setProgress(3);
            }

            if ((starRank.getProgress() > 4.0) && (starRank.getProgress() < 5.0)) {
                starRank.setProgress(4);
            }
            if ((starRank.getProgress() > 5.0) && (starRank.getProgress() < 6.0)) {
                starRank.setProgress(5);
            }
        });

        return starRank;
    }
}
