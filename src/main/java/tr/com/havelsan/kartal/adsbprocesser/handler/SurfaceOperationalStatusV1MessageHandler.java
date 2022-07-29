package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.msgs.SurfaceOperationalStatusV1Msg;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.SurfaceOperationalStatusData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class SurfaceOperationalStatusV1MessageHandler implements MessageHandler<SurfaceOperationalStatusV1Msg> {
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_SURFACE_STATUS_V1;
    }

    @Override
    public void handle(SurfaceOperationalStatusV1Msg surfaceOperationalStatusV1Msg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(surfaceOperationalStatusV1Msg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(surfaceOperationalStatusV1Msg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setSurfaceOperationalStatus(new SurfaceOperationalStatusData());

        adsbTrackData.getSurfaceOperationalStatus().setVersion(surfaceOperationalStatusV1Msg.getVersion());
        adsbTrackData.getSurfaceOperationalStatus().setGeometricVerticalAccuracy(surfaceOperationalStatusV1Msg.getGeometricVerticalAccuracy());
        adsbTrackData.getSurfaceOperationalStatus().setHasTrackHeadingInfo(surfaceOperationalStatusV1Msg.hasTrackHeadingInfo());
        adsbTrackData.getSurfaceOperationalStatus().setGPSAntennaOffset(surfaceOperationalStatusV1Msg.getGPSAntennaOffset());
        adsbTrackData.getSurfaceOperationalStatus().setHasLowTxPower(surfaceOperationalStatusV1Msg.hasLowTxPower());
        adsbTrackData.getSurfaceOperationalStatus().setNACv(surfaceOperationalStatusV1Msg.getNACv());
        adsbTrackData.getSurfaceOperationalStatus().setAirplaneLength(surfaceOperationalStatusV1Msg.getAirplaneLength());
        adsbTrackData.getSurfaceOperationalStatus().setAirplaneLenWidth(surfaceOperationalStatusV1Msg.getGPSAntennaOffset());
        adsbTrackData.getSurfaceOperationalStatus().setHasSingleAntenna(surfaceOperationalStatusV1Msg.hasSingleAntenna());
        adsbTrackData.getSurfaceOperationalStatus().setHasUATIn(surfaceOperationalStatusV1Msg.hasUATIn());
        adsbTrackData.getSurfaceOperationalStatus().setHasTCASResolutionAdvisory(surfaceOperationalStatusV1Msg.hasTCASResolutionAdvisory());
        adsbTrackData.getSurfaceOperationalStatus().setHasActiveIDENTSwitch(surfaceOperationalStatusV1Msg.hasActiveIDENTSwitch());
        adsbTrackData.getSurfaceOperationalStatus().setHas1090ESIn(surfaceOperationalStatusV1Msg.has1090ESIn());
        adsbTrackData.getSurfaceOperationalStatus().setSystemDesignAssurance(surfaceOperationalStatusV1Msg.getSystemDesignAssurance());
        adsbTrackData.getSurfaceOperationalStatus().setSil(surfaceOperationalStatusV1Msg.getSIL());
        adsbTrackData.getSurfaceOperationalStatus().setNICSupplementC(surfaceOperationalStatusV1Msg.getNICSupplementC());
        adsbTrackData.getSurfaceOperationalStatus().setHasNICSupplementA(surfaceOperationalStatusV1Msg.hasNICSupplementA());
        adsbTrackData.getSurfaceOperationalStatus().setPositionUncertainty(surfaceOperationalStatusV1Msg.getPositionUncertainty());
        adsbTrackData.getSurfaceOperationalStatus().setNACp(surfaceOperationalStatusV1Msg.getNACp());
    }
}
