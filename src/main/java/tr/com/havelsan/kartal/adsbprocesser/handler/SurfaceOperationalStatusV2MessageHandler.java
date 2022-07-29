package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.msgs.SurfaceOperationalStatusV2Msg;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.SurfaceOperationalStatusData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class SurfaceOperationalStatusV2MessageHandler implements MessageHandler<SurfaceOperationalStatusV2Msg> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_SURFACE_STATUS_V2;
    }

    @Override
    public void handle(SurfaceOperationalStatusV2Msg surfaceOperationalStatusV2Msg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(surfaceOperationalStatusV2Msg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(surfaceOperationalStatusV2Msg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setSurfaceOperationalStatus(new SurfaceOperationalStatusData());

        adsbTrackData.getSurfaceOperationalStatus().setVersion(surfaceOperationalStatusV2Msg.getVersion());
        adsbTrackData.getSurfaceOperationalStatus().setGeometricVerticalAccuracy(surfaceOperationalStatusV2Msg.getGeometricVerticalAccuracy());
        adsbTrackData.getSurfaceOperationalStatus().setHasTrackHeadingInfo(surfaceOperationalStatusV2Msg.hasTrackHeadingInfo());
        adsbTrackData.getSurfaceOperationalStatus().setGPSAntennaOffset(surfaceOperationalStatusV2Msg.getGPSAntennaOffset());
        adsbTrackData.getSurfaceOperationalStatus().setHasLowTxPower(surfaceOperationalStatusV2Msg.hasLowTxPower());
        adsbTrackData.getSurfaceOperationalStatus().setNACv(surfaceOperationalStatusV2Msg.getNACv());
        adsbTrackData.getSurfaceOperationalStatus().setAirplaneLength(surfaceOperationalStatusV2Msg.getAirplaneLength());
        adsbTrackData.getSurfaceOperationalStatus().setAirplaneLenWidth(surfaceOperationalStatusV2Msg.getGPSAntennaOffset());
        adsbTrackData.getSurfaceOperationalStatus().setHasSingleAntenna(surfaceOperationalStatusV2Msg.hasSingleAntenna());
        adsbTrackData.getSurfaceOperationalStatus().setHasUATIn(surfaceOperationalStatusV2Msg.hasUATIn());
        adsbTrackData.getSurfaceOperationalStatus().setHasTCASResolutionAdvisory(surfaceOperationalStatusV2Msg.hasTCASResolutionAdvisory());
        adsbTrackData.getSurfaceOperationalStatus().setHasActiveIDENTSwitch(surfaceOperationalStatusV2Msg.hasActiveIDENTSwitch());
        adsbTrackData.getSurfaceOperationalStatus().setHas1090ESIn(surfaceOperationalStatusV2Msg.has1090ESIn());
        adsbTrackData.getSurfaceOperationalStatus().setSystemDesignAssurance(surfaceOperationalStatusV2Msg.getSystemDesignAssurance());
        adsbTrackData.getSurfaceOperationalStatus().setSil(surfaceOperationalStatusV2Msg.getSIL());
        adsbTrackData.getSurfaceOperationalStatus().setNICSupplementC(surfaceOperationalStatusV2Msg.getNICSupplementC());
        adsbTrackData.getSurfaceOperationalStatus().setHasNICSupplementA(surfaceOperationalStatusV2Msg.hasNICSupplementA());
        adsbTrackData.getSurfaceOperationalStatus().setPositionUncertainty(surfaceOperationalStatusV2Msg.getPositionUncertainty());
        adsbTrackData.getSurfaceOperationalStatus().setNACp(surfaceOperationalStatusV2Msg.getNACp());

        adsbTrackData.getSurfaceOperationalStatus().setHasSILSupplement(surfaceOperationalStatusV2Msg.hasSILSupplement());
    }
}
