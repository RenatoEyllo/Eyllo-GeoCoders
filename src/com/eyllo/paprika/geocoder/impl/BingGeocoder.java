package com.eyllo.paprika.geocoder.impl;

import org.bingmaps.rest.BingMapsRestService;
import org.bingmaps.rest.models.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eyllo.paprika.geocoder.AbstractGeocoder;

/**
 * Class that uses BingMaps geocoder to decode a specific address
 * @author renatomarroquin
 *
 */
public class BingGeocoder extends AbstractGeocoder{

  /**
   * Application key to perform geocoding or reverse geocoding
   */
  private static final String APPLICATION_KEY = "AnXOdUZ6rZBpyNyRK9hyoJlDmaXogyes6zaz9TID25dWFkMiWk_ILI5bmqqpZuhi";

  /**
   * Object to help us log operations
   */
  private static Logger LOGGER = LoggerFactory.getLogger(BingGeocoder.class);

  /**
   * DEfault constructor
   */
  public BingGeocoder(){
    super();
    LOGGER.info("Initializing Bing Geocoder");
  }

  /**
   * Geocodes and sets latitude and longitude for a specific address
   * @param pAddress
   */
  @Override
  public void geoCodeAddress(String pAddress){
	  BingMapsRestService bms = new BingMapsRestService(APPLICATION_KEY);
	  //bms.GeocodeAsync("Rua An’bal de Mendona, 55 - Ipanema - 22430060");
	  try{
	      Location[] locations = bms.GeocodeAsync(pAddress);
	      if (locations.length > 0){
	          this.setFormattedAddress(locations[DEFAULT_LOCATIONS].Address.toString());
	          this.setLatitude(locations[DEFAULT_LOCATIONS].Point.Latitude);
	          this.setLongitude(locations[DEFAULT_LOCATIONS].Point.Longitude);
	          this.setLocationConfidence(locations[DEFAULT_LOCATIONS].Confidence);
	      }
	  }catch (Exception e){
	      LOGGER.error("Error geocoding address " + pAddress + " using BingMaps");
	  }
	  
  }
}
