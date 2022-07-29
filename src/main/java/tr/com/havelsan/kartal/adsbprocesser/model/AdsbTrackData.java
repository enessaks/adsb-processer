package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class AdsbTrackData {
    private String icaoCode;
    private double longitude;
    private double altitude;
    private double latitude;
    private AirborneOperationalStatusData airborneOperationalStatus;
    private AirbornePositionData airbornePosition;
    private AirspeedHeadingData airspeedHeading;
    private AllCallReplyData allCallReply;
    private AltitudeReplyData altitudeReply;
    private CommBAltitudeReplyData commBAltitudeReply;
    private CommBIdentifyReplyData commBIdentifyReply;
    private CommDExtendedLEngthData commDExtendedLength;
    private EmergencyOrPriorityStatusData emergencyOrPriorityStatus;
    private ExtendedSquitterData extendedSquitter;
    private IdentificationData identification;
    private IdentifyReplyData identifyReply;
    private LongACASData longACAS;
    private MilitaryExtendedSquitterData militaryExtendedSquitter;
    private ModeSReplyData modeSReplay;
    private OperationalStatusData operationalStatus;
    private ShortACASData shortACAS;
    private SurfaceOperationalStatusData surfaceOperationalStatus;
    private SurfacePositionData surfacePosition;
    private TargetStateAndStatusData targetStateAndStatus;
    private TCASResolutionAdvisoryData tcasResolutionAdvisory;
    private VelocityOverGroundData velocityOverGround;
}