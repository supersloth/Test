package com.example.android.appnavigation.app;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.android.appnavigation.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;



public class BurqueMapsMapActivity extends MapActivity {
    
    protected GeoPoint pointFromLatAndLon(String latIn, String lonIn) {
        double lat = Double.parseDouble(latIn);
        double lng = Double.parseDouble(lonIn);
        
        GeoPoint p = new GeoPoint(
                (int) (lat * 1E6), 
                (int) (lng * 1E6));
        
        return p;
    }

    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burque_maps_map);
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        MapController mc = mapView.getController();
        String coordinates[] = {"35.0844", "-106.6506"};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);

        GeoPoint city = new GeoPoint(
            (int) (lat * 1E6), 
            (int) (lng * 1E6));

        mc.animateTo(city);
        mc.setZoom(11); 
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        //Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
        BurqueMapsItemizedOverlay itemizedoverlay = new BurqueMapsItemizedOverlay(drawable, this);
        
        //GeoPoint point = new GeoPoint(19240000,-99120000);
        GeoPoint point = pointFromLatAndLon("35.0844", "-106.6506");
        OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
        
        //GeoPoint point2 = new GeoPoint(35410000, 139460000);
        GeoPoint point2 = pointFromLatAndLon("35.0844", "-106.5506");
        OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");
        
        itemizedoverlay.addOverlay(overlayitem);
        itemizedoverlay.addOverlay(overlayitem2);
        mapOverlays.add(itemizedoverlay);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
