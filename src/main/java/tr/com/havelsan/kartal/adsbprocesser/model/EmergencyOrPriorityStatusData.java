package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class EmergencyOrPriorityStatusData {
    private byte msgsubtype;
    private String emergencyState;
    private byte[] modeACode;
}
