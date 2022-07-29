package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class ExtendedSquitterData {
    private byte capabilities;
    private byte[] message;
    private byte formatTypeCode;
}
