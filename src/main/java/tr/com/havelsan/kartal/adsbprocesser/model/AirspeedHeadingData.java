package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class AirspeedHeadingData {
    private byte msgSubtype;
    private boolean intentChange;
    private boolean ifrCapability;
    private byte navigationAccuracyCategory;
    private boolean headingStatusBit;
    private double heading;
    private boolean trueAirspeed;
    private int airspeed;
    private boolean airspeedAvailable;
    private boolean verticalSource;
    private boolean verticalRateDown;
    private int verticalRate;
    private boolean verticalRateInfoAvailable;
    private int geoMinusBaro;
    private boolean geoMinusBaroAvailable;
    private boolean isSupersonic;
    private double NACv;
}
