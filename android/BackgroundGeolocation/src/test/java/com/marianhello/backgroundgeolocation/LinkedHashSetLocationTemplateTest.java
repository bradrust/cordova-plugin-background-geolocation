package com.marianhello.backgroundgeolocation;

import com.marianhello.bgloc.Config;
import com.marianhello.bgloc.data.BackgroundLocation;
import com.marianhello.bgloc.data.LinkedHashSetLocationTemplate;
import com.marianhello.bgloc.data.LocationTemplate;
import com.marianhello.bgloc.data.LocationTemplateFactory;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import java.util.LinkedHashSet;

/**
 * Created by finch on 9.12.2017.
 */

public class LinkedHashSetLocationTemplateTest {
    @Test
    public void testLinkedHashSetTemplateToString() {
        LinkedHashSet props = new LinkedHashSet();
        props.add("foo");
        props.add("bar");
        LinkedHashSetLocationTemplate tpl = new LinkedHashSetLocationTemplate(props);

        Assert.assertEquals("[\"foo\",\"bar\"]" , tpl.toString());
    }

    @Test
    public void testLocationToJSONArray() throws JSONException {
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

        LinkedHashSet props = new LinkedHashSet<String>();
        props.add("@id");
        props.add("@provider");
        props.add("@time");
        props.add("@altitude");
        props.add("@latitude");
        props.add("@longitude");
        props.add("foo");
        props.add("@locationProvider");
        props.add("@accuracy");
        props.add("@speed");
        props.add("@bearing");

        LocationTemplate tpl = new LinkedHashSetLocationTemplate(props);
        JSONArray expected = (JSONArray) tpl.locationToJson(location);

        Assert.assertEquals(expected.get(0), location.getLocationId());
        Assert.assertEquals(expected.get(1), location.getProvider());
        Assert.assertEquals(expected.get(2), location.getTime());
        Assert.assertEquals(expected.get(3), location.getAltitude());
        Assert.assertEquals(expected.get(4), location.getLatitude());
        Assert.assertEquals(expected.get(5), location.getLongitude());
        Assert.assertEquals(expected.get(6), "foo");
        Assert.assertEquals(expected.get(7), location.getLocationProvider());
        Assert.assertEquals(expected.get(8), location.getAccuracy());
        Assert.assertEquals(expected.get(9), location.getSpeed());
        Assert.assertEquals(expected.get(10), location.getBearing());
    }
}
