package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class IdentifyReplyData {
    private byte flightStatus;
    private byte downlinkRequest;
    private byte utilityMsg;
    private String identity;
    private boolean hasAlert;
    private boolean hasSPI;
    private boolean isOnGround;
    private boolean isAirborne;
    private byte identifierDesignator;
    private byte interrogatorIdentifier;
}
