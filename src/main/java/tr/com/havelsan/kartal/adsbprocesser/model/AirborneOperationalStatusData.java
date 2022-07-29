package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class AirborneOperationalStatusData {
    private byte subtypeCode;
    private int capabilityClassCode;
    private int operationalModeCode;
    private byte version;
    private boolean nicSuppl;
    private byte nacPos;
    private int geometricVerticalAccuracy;
    private byte sil;
    private boolean nicTrkHdg;
    private boolean hrd;
    private boolean hasSingleAntenna;
    private boolean barometricAltitudeIntegrityCode;
    private boolean horizontalReferenceDirection;
    private boolean hasAirReferencedVelocity;
    private boolean hasUATIn;
    private boolean hasTCASResolutionAdvisory;
    private boolean hasOperationalTCAS;
    private boolean hasActiveIDENTSwitch;
    private boolean has1090ESIn;
    private boolean hasNICSupplementA;
    private byte systemDesignAssurance;
    private double positionUncertainty;
    private boolean hasSILSupplement;
}
