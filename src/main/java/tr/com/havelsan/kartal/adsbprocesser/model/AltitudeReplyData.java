package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class AltitudeReplyData {
    private byte flightStatus;
    private byte downlinkRequest;
    private byte utilityMsg;
    private short altitudeCode;
    private int altitude;
    private boolean hasAlert;
    private boolean hasSPI;
    private boolean isOnGround;
    private boolean isAirborne;
    private byte interrogatorIdentifier;
    private byte identifierDesignator;
}
