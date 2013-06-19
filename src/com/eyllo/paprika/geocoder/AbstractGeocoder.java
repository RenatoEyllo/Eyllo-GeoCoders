package com.eyllo.paprika.geocoder;

import org.bingmaps.rest.models.Confidence;
import org.bingmaps.rest.models.Location;

public abstract class AbstractGeocoder {

  public static int DEFAULT_LOCATIONS = 0;
  
  // URL prefix to the geocoder
  private double latitude;
  
  private double longitude;
  
  private String formattedAddress;
  
  private Location[] locations;
  
  private int locationConfidence;
  
  public AbstractGeocoder(){
    this.latitude = Double.NaN;
    this.longitude = Double.NaN;
    this.locationConfidence = Confidence.Unknown;
  }

  /**
   * Geocodes and sets latitude and longitude for a specific address
   * @param pAddress
   */
  public abstract void geoCodeAddress(String pAddress);

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude2) {
    this.latitude = latitude2;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getFormattedAddress() {
    return formattedAddress;
  }

  public void setFormattedAddress(String formattedAddress) {
    this.formattedAddress = formattedAddress;
  }

	/**
	 * @return the locationConfidence
	 */
	public int getLocationConfidence() {
		return locationConfidence;
	}
	
	/**
	 * @param locationConfidence the locationConfidence to set
	 */
	public void setLocationConfidence(int locationConfidence) {
		this.locationConfidence = locationConfidence;
	}
	
	/**
	 * @return the locations
	 */
	public Location[] getLocations() {
		return locations;
	}

	/**
	 * @param locations the locations to set
	 */
	public void setLocations(Location[] locations) {
		this.locations = locations;
	}
}
