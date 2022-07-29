package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class LongACASData {
    private boolean airborne;
    private byte sensitivityLevel;
    private byte replyInformation;
    private short altitudeCode;
    private boolean validRac;
    private short activeResolutionAdvisories;
    private byte racsRecord;
    private boolean raTerminated;
    private boolean multipleThreatEncounter;
    private int MaximumAirspeed;
    private boolean noPassBelow;
    private boolean noPassAbove;
    private boolean noTurnLeft;
    private boolean noTurnRight;
    private byte ResolutionAdvisoryComplement;
    private boolean hasOperatingACAS;
    private int altitude;
}
