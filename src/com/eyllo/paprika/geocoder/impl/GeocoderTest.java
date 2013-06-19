/**
 * 
 */
package com.eyllo.paprika.geocoder.impl;

import com.eyllo.paprika.geocoder.AbstractGeocoder;

/**
 * @author renatomarroquin
 *
 */
public class GeocoderTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AbstractGeocoder geo = new BingGeocoder();
        //geo.geoCodeAddress("Rua Visconde de Pirajá, 330 - Ipanema - 22410000");
        geo.geoCodeAddress("Rua Joana Angélica, 40 - Ipanema - CEP 22420030");
        System.out.println(geo.getLatitude());
        System.out.println(geo.getLongitude());
        //geo.geoCodeAddress("Rua Henrique Dumont, 71 - Ipanema - CEP 22410060 -"); 
        //http://dev.virtualearth.net/REST/v1/Locations/Rua%20Visconde%20de%20Piraj%C3%A1%2C%20330%20%20%20%20Ipanema%20%20%20%20CEP%2022410000%20%20?key=AnXOdUZ6rZBpyNyRK9hyoJlDmaXogyes6zaz9TID25dWFkMiWk_ILI5bmqqpZuhi
    }

}
