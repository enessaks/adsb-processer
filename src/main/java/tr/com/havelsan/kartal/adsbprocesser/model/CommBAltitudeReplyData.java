package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class CommBAltitudeReplyData {
    private byte flightStatus;
    private byte downlinkRequest;
    private byte utilityMsg;
    private byte[] message;
    private byte IdentifierDesignator;
    private byte InterrogatorIdentifier;
    private boolean hasAlert;
    private boolean hasSPI;
    private boolean isOnGround;
    private boolean isAirborne;
    private int altitude;
}
