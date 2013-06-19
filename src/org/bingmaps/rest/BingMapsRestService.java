package org.bingmaps.rest;

import org.bingmaps.data.ContentTypes;
import org.bingmaps.data.RequestType;
import org.bingmaps.data.ServiceRequest;
import org.bingmaps.rest.models.Location;
import org.bingmaps.rest.models.ResourceSet;
import org.bingmaps.rest.models.Response;
import org.bingmaps.sdk.Coordinate;
import org.bingmaps.sdk.Utilities;
import org.json.JSONException;
import org.json.JSONObject;

public class BingMapsRestService {
	private String _bingMapsKey;
	private String _culture;
	
	public BingMapsRestService(String bingMapsKey){
		_bingMapsKey = bingMapsKey;
	}
	
	public BingMapsRestService(String bingMapsKey, String culture){
		_bingMapsKey = bingMapsKey;
		_culture = culture;
	}
	
	/* Public Methods */
	
    public Location[] GeocodeAsync(String query){
        //generate URL
        String requestURL = Constants.BaseServiceURL + "Locations/" + Utilities.EnodeURLParam(query) + "?";
        if(!Utilities.isNullOrEmpty(_culture)){
            requestURL += "c=" + _culture + "&";
        }

        requestURL += "key=" + _bingMapsKey;

        //create service request
        ServiceRequest request = new ServiceRequest(requestURL, RequestType.GET, ContentTypes.JSON);
        Location[] locations = null;
        //System.out.println(requestURL);
        String result = request.execute();
        if (result != null && !result.equals("")){
                try {
                    JSONObject obj = new JSONObject(result);
                    Response response = new Response(obj);
                    if(response.ResourceSets.length > 0){
                        ResourceSet resourceSet = response.ResourceSets[0];
                        int len = resourceSet.Resources.length;
                        locations = new Location[len];
    
                        for(int i = 0; i < len; i++)
                        {
                            obj = resourceSet.Resources[i];
                            locations[i] = new Location(obj);
                        }
                    }                       
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }// END-IF
            else{
                System.out.println("Couldn't find address " + query);
            }
		return locations;
	}
	
	public void GeocodeAsync(org.bingmaps.rest.models.Address address){
		// TODO	//
		/*
		//generate URL
		String requestURL = Constants.BaseServiceURL + "Locations?";
		
		StringBuilder sb = new StringBuilder();
		
		if(address != null){
			if(!Utilities.isNullOrEmpty(address.AddressLine)){
				sb.append("&addressLine=");
				sb.append(Utilities.EnodeURLParam(address.AddressLine));
			}

			if(!Utilities.isNullOrEmpty(address.Locality)){
				sb.append("&locality=");
				sb.append(Utilities.EnodeURLParam(address.Locality));
			}
			
			if(!Utilities.isNullOrEmpty(address.AdminDistrict)){
				sb.append("&adminDistrict=");
				sb.append(Utilities.EnodeURLParam(address.AdminDistrict));
			}
			
			if(!Utilities.isNullOrEmpty(address.PostalCode)){
				sb.append("&postalCode=");
				sb.append(Utilities.EnodeURLParam(address.PostalCode));
			}
			
			if(!Utilities.isNullOrEmpty(address.CountryRegion)){
				sb.append("&countryRegion=");
				sb.append(Utilities.EnodeURLParam(address.CountryRegion));
			}
		}
		
		if(!Utilities.isNullOrEmpty(_culture)){
			sb.append("&c=");
			sb.append(_culture);
		}
		
		sb.append("&key=");
		sb.append(_bingMapsKey);
		
		requestURL += sb.substring(1);
		*/
		//create service request
		// TODO
		//ServiceRequest request = new ServiceRequest(requestURL, RequestType.GET, ContentTypes.JSON);
		//LocationServiceAsyncTask service = new LocationServiceAsyncTask(GeocodeAsyncCompleted);
		//service.execute(request);
	}

	//includeEntityTypes not supported
	public void ReverseGeocodeAsync(Coordinate coordinate){
		// TODO	//
		/*
		//generate URL
		String requestURL = Constants.BaseServiceURL + "Locations/" 
							+ coordinate.Latitude + "," + coordinate.Longitude + "?";
		
		if(!Utilities.isNullOrEmpty(_culture)){
			requestURL += "c=" + _culture + "&";
		}
		
		requestURL += "key=" + _bingMapsKey;
		*/
		//create service request
		// TODO	//
		//ServiceRequest request = new ServiceRequest(requestURL, RequestType.GET, ContentTypes.JSON);
		//LocationServiceAsyncTask service = new LocationServiceAsyncTask(ReverseGeocodeAsyncCompleted);
		//service.execute(request);
	}
	
	public void RouteAsync(RouteRequest routeRequest){
		// TODO	//
		/*
		String requestURL = routeRequest.toString();
		
		if(!Utilities.isNullOrEmpty(_culture)){
			requestURL += "&c=" + _culture;
		}
		
		requestURL += "&key=" + _bingMapsKey;
		*/
		//create service request
		// TODO	//
		//ServiceRequest request = new ServiceRequest(requestURL, RequestType.GET, ContentTypes.JSON);
		//RouteServiceAsyncTask service = new RouteServiceAsyncTask(RouteAsyncCompleted);
		//service.execute(request);
	}
	
	public void MajorRouteAsync(MajorRouteRequest routeRequest){
		// TODO	//
		/*
		String requestURL = routeRequest.toString();
		
		if(!Utilities.isNullOrEmpty(_culture)){
			requestURL += "&c=" + _culture;
		}
		
		requestURL += "&key=" + _bingMapsKey;
		*/
		//create service request
		//ServiceRequest request = new ServiceRequest(requestURL, RequestType.GET, ContentTypes.JSON);
		//RouteServiceAsyncTask service = new RouteServiceAsyncTask(MajorRouteAsyncCompleted);
		//service.execute(request);
	}
}
