package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class SurfaceOperationalStatusData {
    private byte subtypeCode;
    private int capabilityClassCode;
    private int operationalModeCode;
    private byte airplaneLenWidth;
    private byte version;
    private boolean nicSuppl;
    private byte nacPos;
    private int geometricVerticalAccuracy;
    private byte sil;
    private boolean hasTrackHeadingInfo;
    private boolean hrd;
    private boolean silSupplement;
    private int airplaneLength;
    private double airplaneWidth;
    private double positionUncertainty;
    private boolean NICSupplementC;
    private boolean hasTCASResolutionAdvisory;
    private boolean hasActiveIDENTSwitch;
    private boolean hasSingleAntenna;
    private byte systemDesignAssurance;
    private byte GPSAntennaOffset;
    private boolean hasLowTxPower;
    private byte NACv;
    private boolean hasUATIn;
    private boolean has1090ESIn;
    private boolean hasNICSupplementA;
    private byte NACp;
    private boolean hasSILSupplement;
}
