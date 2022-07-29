package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class VelocityOverGroundData {
    private double heading;
    private double velocity;
    private int verticalRate;
    private byte msgSubtype;
    private boolean intentChange;
    private boolean ifrCapability;
    private float accuracyBound;
    private boolean directionWest;
    private boolean velocityInfoAvailable;
    private boolean directionSouth;
    private boolean verticalSource;
    private boolean verticalRateDown;
    private boolean verticalRateInfoAvailable;
    private int geoMinusBaro;
    private boolean geoMinusBaroAvailable;
    private boolean isSupersonic;
    private int eastToWestVelocity;
    private int northToSouthVelocity;

}