package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class ModeSReplyData {
    private byte downlinkFormat;
    private byte firstField;
    private byte[] icao24;
    private byte[] payload;
    private byte[] parity;
    private boolean noCRC;
    private int hashCode;
    private int expectedLength;
}
