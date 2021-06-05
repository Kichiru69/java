package ru.stqa.pft.soap;

import lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ServiceTests {

  @Test
  public void testMyIP() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("109.252.45.58");
    assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
  }

  @Test
  public void testInvalidIP() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("xxx.252.45.58");
    assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
  }
}
