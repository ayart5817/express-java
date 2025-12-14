package FreeTask.Class_Object;

import java.util.Objects;

public class GeoPoint {
    static float roundTo4(float value) {
        value = (float) (Math.round(value * 10000.00) /10000.00);
        return value;
    }

    private float latitude;

    private float longitude;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GeoPoint geoPoint = (GeoPoint) o;
        return Float.compare(latitude, geoPoint.latitude) == 0 && Float.compare(longitude, geoPoint.longitude) == 0;
    }

    @Override
    public String
    toString() {
        return "GeoPoint{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    public GeoPoint(float latitude, float longitude) {
        this.latitude = roundTo4(latitude);
        this.longitude = roundTo4(longitude);
    }

    static void main(String[] args) {
        GeoPoint point1 = new GeoPoint(45.9966466f, 58.6566564f);
        GeoPoint point2 = new GeoPoint(44.9966466f, 57.6566564f);
        GeoPoint point3 = new GeoPoint(44.f, 57.6f);
        System.out.println(point1);
        System.out.println(point2);
        System.out.println(point3);
    }
}
