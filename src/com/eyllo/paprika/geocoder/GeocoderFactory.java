package com.eyllo.paprika.geocoder;

import java.util.ArrayList;
import java.util.List;

import com.eyllo.paprika.geocoder.impl.BingGeocoder;
import com.eyllo.paprika.geocoder.impl.GoogleGeocoder;

/**
 * Geocoder factory in charge of creating an specific geocoder
 * @author renatomarroquin
 *
 */
public class GeocoderFactory {

    /**
     * Object geocoder
     */
    private static AbstractGeocoder geocoder;

    /**
     * Value to use when Google geocoder is chosen
     */
    public static final String GOOGLE = "google";

    /**
     * Value to use when BingMaps geocoder is chosen
     */
    public static final String BING = "bing";

    /**
     * Construct the specific geocoder
     * @param params The first one is always the geocoder name
     * @return
     */
    public static AbstractGeocoder getGeocoder(String...params){
        if (params.length > 0){
            if (params[0].toLowerCase().equals(GOOGLE))
                geocoder = new GoogleGeocoder();
            else if (params[0].toLowerCase().equals(BING))
                geocoder = new BingGeocoder();
        }
        return geocoder;
    }

    /**
     * Returns a list containing all geocoders available
     * @return
     */
    public static List<AbstractGeocoder> getAllGeocoders(){
        List<AbstractGeocoder> gcs = new ArrayList<AbstractGeocoder>();
        gcs.add(GeocoderFactory.getGeocoder(GeocoderFactory.GOOGLE));
        gcs.add(GeocoderFactory.getGeocoder(GeocoderFactory.BING));
        return gcs;
    }

    /**
     * @return the geocoder
     */
    public static AbstractGeocoder getGeocoder() {
        return geocoder;
    }

    /**
     * @param geocoder the geocoder to set
     */
    public static void setGeocoder(AbstractGeocoder geocoder) {
        GeocoderFactory.geocoder = geocoder;
    }
}
