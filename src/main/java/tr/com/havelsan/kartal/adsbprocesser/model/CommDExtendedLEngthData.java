package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class CommDExtendedLEngthData {
    private String message;
    private boolean ack;
    private byte seqno;
}
