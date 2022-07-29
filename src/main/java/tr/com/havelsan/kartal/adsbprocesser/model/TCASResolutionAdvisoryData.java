package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class TCASResolutionAdvisoryData {
    private byte msgSubtype;
    private short activeRa;
    private byte racsRecord;
    private boolean raTerminated;
    private boolean multiThreatEncounter;
    private byte threatType;
    private int threatIdentity;
}
