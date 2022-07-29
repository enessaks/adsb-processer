package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class AllCallReplyData {
    private byte capabilities;
    private byte[] parityInterrogator; // 3 bytes
    private byte codeLabel;
    private boolean isAirborne;
    private boolean isOnGround;
    private boolean hasValidInterrogatorCode;
    private String InterrogatorCode;
    private boolean isSurveillanceID;
}
