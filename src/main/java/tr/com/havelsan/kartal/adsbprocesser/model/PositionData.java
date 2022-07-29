package tr.com.havelsan.kartal.adsbprocesser.model;

import lombok.Data;

@Data
public class PositionData {

    private double[] toECEF;
    private boolean isReasonable;
    private String toString;
    private boolean equals;
    private int hashCode;
    private double latitude;
    private double altitude;
}
