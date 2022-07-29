package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class SurfacePositionData {
    private boolean horizontalPositionAvailable;
    private byte movement;
    private boolean headingstatus;
    private byte groundTrack;
    private boolean timeFlag;
    private boolean cprFormat;
    private int cprEncodedLat;
    private int cprEncodedLon;
    private boolean nicSupplA;
    private boolean nicCupplC;
    private double heading;
}
