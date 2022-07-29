package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class AirbornePositionData {
    private boolean horizontalPositionAvailable;
    private boolean altitudeAvailable;
    private byte surveillanceStatus;
    private boolean nicSupplA;
    private boolean nicSupplB;
    private short altitudeEncoded;
    private boolean timeFlag;
    private boolean cprFormat;
    private int cprEncodedLat;
    private int cprEncodedLon;
    private String SurveillanceStatusDescription;
    private double latitude;
    private double longitude;
    private double horizontalContainmentRadiusLimit;
    private boolean isBarometricAltitude;
}
