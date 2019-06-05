package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {
    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("176.74.14.58");
        assertTrue(ipLocation.contains("RU"));
    }

    @Test
    public void testInvalidIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("176.74.14.xxx");
        assertTrue(ipLocation.contains("Invalid IP address"));
    }
}
