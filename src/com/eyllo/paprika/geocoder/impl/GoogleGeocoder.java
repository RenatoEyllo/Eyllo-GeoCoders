package com.eyllo.paprika.geocoder.impl;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.bingmaps.rest.models.Confidence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.eyllo.paprika.geocoder.AbstractGeocoder;

/**
 * Class that uses GoogleGeocoder to geocode an address
 * @author renatomarroquin
 *
 */
public class GoogleGeocoder extends AbstractGeocoder{

  /**
   * URL prefix to the geocoder
   */
  private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";

  /**
   * Logger to help us log events
   */
  private static Logger LOGGER = LoggerFactory.getLogger(GoogleGeocoder.class);

  /**
   * Default constructor
   */
  public GoogleGeocoder(){
      super();
  }

  /**
   * Geocodes and sets latitude and longitude for a specific address
   * @param pAddress
   */
  @Override
  public void geoCodeAddress(String pAddress){
    
    // Getting ready to relocate 
    this.setLatitude(Double.NaN);
    this.setLongitude(Double.NaN);
    
    // prepare a URL to the geocoder
    URL url;
    HttpURLConnection conn = null;
    
    try {
    
      url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address=" + URLEncoder.encode(pAddress, "UTF-8") + "&sensor=false");
      
      //System.out.println(url);
      // prepare an HTTP connection to the geocoder
      conn = (HttpURLConnection) url.openConnection();

      Document geocoderResultDocument = null;

      // open the connection and get results as InputSource.
      conn.connect();
      InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());

      // read result and parse into XML Document
      geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);

      // prepare XPath
      XPath xpath = XPathFactory.newInstance().newXPath();

      // extract the result
      NodeList resultNodeList = null;

      // a) obtain the formatted_address field for every result
      resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result/formatted_address", geocoderResultDocument, XPathConstants.NODESET);
      for(int i=0; i<resultNodeList.getLength(); ++i) {
        this.setFormattedAddress(resultNodeList.item(i).getTextContent());
        LOGGER.debug("gcc " + resultNodeList.item(i).getTextContent());
      }

      // b) extract the locality for the first result
      /*resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/address_component[type/text()='locality']/long_name", geocoderResultDocument, XPathConstants.NODESET);
      for(int i=0; i<resultNodeList.getLength(); ++i) {
        System.out.println("gcc " + resultNodeList.item(i).getTextContent());
      }*/

      // c) extract the coordinates of the first result
      resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/*", geocoderResultDocument, XPathConstants.NODESET);
      for(int i=0; i<resultNodeList.getLength(); ++i) {
        Node node = resultNodeList.item(i);
        // Setting operation results
        if("lat".equals(node.getNodeName())) this.setLatitude(Float.parseFloat(node.getTextContent()));
        if("lng".equals(node.getNodeName())) this.setLongitude(Float.parseFloat(node.getTextContent()));
        this.setLocationConfidence(Confidence.Unknown);
      }
      LOGGER.debug("lat/lng=" + this.getLatitude() + "," + this.getLongitude());
      

      // c) extract the coordinates of the first result
      /*resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/address_component[type/text() = 'administrative_area_level_1']/country[short_name/text() = 'US']/*", geocoderResultDocument, XPathConstants.NODESET);
      lat = Float.NaN;
      lng = Float.NaN;
      for(int i=0; i<resultNodeList.getLength(); ++i) {
        Node node = resultNodeList.item(i);
        if("lat".equals(node.getNodeName())) lat = Float.parseFloat(node.getTextContent());
        if("lng".equals(node.getNodeName())) lng = Float.parseFloat(node.getTextContent());
      }
      System.out.println("lat/lng=" + lat + "," + lng);
       */

    } catch (MalformedURLException e) {
      LOGGER.error("Error while geodecoding");
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      LOGGER.error("Error while geodecoding");
      e.printStackTrace();
    } catch (IOException e) {
      LOGGER.error("Error while geodecoding");
      e.printStackTrace();
    } catch (XPathExpressionException e) {
      LOGGER.error("Error while geodecoding");
      e.printStackTrace();
    } catch (SAXException e) {
      LOGGER.error("Error while geodecoding");
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      LOGGER.error("Error while geodecoding");
      e.printStackTrace();
    } finally {
      conn.disconnect();
      LOGGER.debug("Disconnected from geodecoding");
    }// END-TRY
  }// END-GEOCODE
}
