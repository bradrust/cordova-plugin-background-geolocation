package com.marianhello.backgroundgeolocation;

import com.marianhello.bgloc.Config;
import com.marianhello.bgloc.data.BackgroundLocation;
import com.marianhello.bgloc.data.HashMapLocationTemplate;
import com.marianhello.bgloc.data.LocationTemplate;
import com.marianhello.bgloc.data.LocationTemplateFactory;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by finch on 9.12.2017.
 */

public class HashMapLocationTemplateTest {
    @Test
    public void testObjectTemplateToString() {
        HashMap props = new HashMap();
        props.put("foo", "bar");
        HashMapLocationTemplate tpl = new HashMapLocationTemplate(props);

        Assert.assertEquals("{\"foo\":\"bar\"}" , tpl.toString());
    }

    @Test
    public void testLocationToJSONObject() throws JSONException {
        BackgroundLocation location = new BackgroundLocation();
        location.setLocationId(11L);
        location.setProvider("test");
        location.setElapsedRealtimeNanos(2000000000L * 60 * 2);
        location.setAltitude(100);
        location.setLatitude(49);
        location.setLongitude(5);
        location.setLocationProvider(1);
        location.setAccuracy(105);
        location.setSpeed(50);
        location.setBearing(1);

        HashMap map = new HashMap<String, String>();
        map.put("@id", "Id");
        map.put("@provider", "Provider");
        map.put("@time", "Time");
        map.put("@altitude", "Altitude");
        map.put("@latitude", "Latitude");
        map.put("@longitude", "Longitude");
        map.put("bar", "Foo");
        map.put("@locationProvider", "LocationProvider");
        map.put("@accuracy", "Accuracy");
        map.put("@speed", "Speed");
        map.put("@bearing", "Bearing");

        LocationTemplate tpl = new HashMapLocationTemplate(map);
        JSONObject expected = (JSONObject) tpl.locationToJson(location);

        Assert.assertEquals(expected.get("Id"), location.getLocationId());
        Assert.assertEquals(expected.get("Provider"), location.getProvider());
        Assert.assertEquals(expected.get("Time"), location.getTime());
        Assert.assertEquals(expected.get("Altitude"), location.getAltitude());
        Assert.assertEquals(expected.get("Latitude"), location.getLatitude());
        Assert.assertEquals(expected.get("Longitude"), location.getLongitude());
        Assert.assertEquals(expected.get("Foo"), "bar");
        Assert.assertEquals(expected.get("LocationProvider"), location.getLocationProvider());
        Assert.assertEquals(expected.get("Accuracy"), location.getAccuracy());
        Assert.assertEquals(expected.get("Speed"), location.getSpeed());
        Assert.assertEquals(expected.get("Bearing"), location.getBearing());
    }

    @Test
    public void testLocationToJSONObjectFactory() throws JSONException {
        BackgroundLocation location = new BackgroundLocation();
        location.setLocationId(11L);
        location.setProvider("test");
        location.setElapsedRealtimeNanos(2000000000L * 60 * 2);
        location.setAltitude(100);
        location.setLatitude(49);
        location.setLongitude(5);
        location.setLocationProvider(1);
        location.setAccuracy(105);
        location.setSpeed(50);
        location.setBearing(1);

        HashMap map = new HashMap();
        map.put("Id", "@id");
        map.put("Provider", "@provider");
        map.put("Time", "@time");
        map.put("Altitude", "@altitude");
        map.put("Latitude", "@latitude");
        map.put("Longitude", "@longitude");
        map.put("Foo", "bar");
        map.put("LocationProvider", "@locationProvider");
        map.put("Accuracy", "@accuracy");
        map.put("Speed", "@speed");
        map.put("Bearing", "@bearing");

        JSONObject json = new JSONObject(map);
        LocationTemplate tpl = LocationTemplateFactory.fromJSONReverted(json);

        JSONObject expected = (JSONObject) tpl.locationToJson(location);

        Assert.assertEquals(expected.get("Id"), location.getLocationId());
        Assert.assertEquals(expected.get("Provider"), location.getProvider());
        Assert.assertEquals(expected.get("Time"), location.getTime());
        Assert.assertEquals(expected.get("Altitude"), location.getAltitude());
        Assert.assertEquals(expected.get("Latitude"), location.getLatitude());
        Assert.assertEquals(expected.get("Longitude"), location.getLongitude());
        Assert.assertEquals(expected.get("Foo"), "bar");
        Assert.assertEquals(expected.get("LocationProvider"), location.getLocationProvider());
        Assert.assertEquals(expected.get("Accuracy"), location.getAccuracy());
        Assert.assertEquals(expected.get("Speed"), location.getSpeed());
        Assert.assertEquals(expected.get("Bearing"), location.getBearing());
    }
}
