package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class ShortACASData {
    private boolean airborne;
    private boolean crossLinkCapability;
    private byte sensitivityLevel;
    private byte replyInformation;
    private short altitudeCode;
    private boolean hasVerticalResolutionCapability;
    private boolean hasHorizontalResolutionCapability;
    private int MaximumAirspeed;
    private boolean hasOperatingACAS;
    private int Altitude;
}
