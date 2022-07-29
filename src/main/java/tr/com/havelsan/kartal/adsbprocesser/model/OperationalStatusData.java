package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class OperationalStatusData {
    private byte enroute_capabilities;
    private boolean hasOperationalCDTI;
    private boolean hasOperationalTCAS;
    private byte version;
}
