package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class TargetStateAndStatusData {
    private boolean silSuppl;
    private boolean selectedAltitudeType;
    private int selectedAltitude;
    private float barometricPressureSetting;
    private boolean selectectedHeadingStatus;
    private boolean selectectedHeadingSign;
    private float selectedHeading;
    private byte nacP;
    private boolean nicBaro;
    private byte sil;
    private boolean mcpFcuStatus;
    private boolean autopilotEngaged;
    private boolean vnavModeEngaged;
    private boolean altitudeHoldMode;
    private boolean approachMode;
    private boolean hasOperationalTcas;
    private boolean lnavModeEngaged;
    private boolean hasSelectedAltitudeInfo;
    private boolean hasBarometricPressureSettingInfo;
    private boolean hasSelectedHeadingInfo;
    private boolean hasModeInfo;
}
