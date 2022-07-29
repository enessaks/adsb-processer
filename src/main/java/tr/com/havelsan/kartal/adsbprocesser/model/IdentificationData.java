package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class IdentificationData {
    private byte emitterCategory;
    private String identity;
    private String CategoryDescription;
}
