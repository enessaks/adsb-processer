package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensky.libadsb.msgs.AirborneOperationalStatusV2Msg;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.AirborneOperationalStatusData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Slf4j
@Component
public class AirborneOperationalStatusV2MessageHandler implements MessageHandler<AirborneOperationalStatusV2Msg> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_AIRBORN_STATUS_V2;
    }

    @Override
    public void handle(AirborneOperationalStatusV2Msg airborneOperationalStatusV2Msg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(airborneOperationalStatusV2Msg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(airborneOperationalStatusV2Msg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setAirborneOperationalStatus(new AirborneOperationalStatusData());
        adsbTrackData.getAirborneOperationalStatus().setVersion(airborneOperationalStatusV2Msg.getVersion());
        adsbTrackData.getAirborneOperationalStatus().setBarometricAltitudeIntegrityCode(airborneOperationalStatusV2Msg.getBarometricAltitudeIntegrityCode());
        adsbTrackData.getAirborneOperationalStatus().setGeometricVerticalAccuracy(airborneOperationalStatusV2Msg.getGeometricVerticalAccuracy());
        adsbTrackData.getAirborneOperationalStatus().setGeometricVerticalAccuracy(airborneOperationalStatusV2Msg.getGeometricVerticalAccuracy());
        adsbTrackData.getAirborneOperationalStatus().setHorizontalReferenceDirection(airborneOperationalStatusV2Msg.getHorizontalReferenceDirection());
        adsbTrackData.getAirborneOperationalStatus().setHasAirReferencedVelocity(airborneOperationalStatusV2Msg.hasAirReferencedVelocity());
        adsbTrackData.getAirborneOperationalStatus().setHasSingleAntenna(airborneOperationalStatusV2Msg.hasSingleAntenna());
        adsbTrackData.getAirborneOperationalStatus().setHasUATIn(airborneOperationalStatusV2Msg.hasUATIn());
        adsbTrackData.getAirborneOperationalStatus().setHasTCASResolutionAdvisory(airborneOperationalStatusV2Msg.hasTCASResolutionAdvisory());
        adsbTrackData.getAirborneOperationalStatus().setHasOperationalTCAS(airborneOperationalStatusV2Msg.hasOperationalTCAS());
        adsbTrackData.getAirborneOperationalStatus().setHasActiveIDENTSwitch(airborneOperationalStatusV2Msg.hasActiveIDENTSwitch());
        adsbTrackData.getAirborneOperationalStatus().setHas1090ESIn(airborneOperationalStatusV2Msg.has1090ESIn());
        adsbTrackData.getAirborneOperationalStatus().setSystemDesignAssurance(airborneOperationalStatusV2Msg.getSystemDesignAssurance());
        adsbTrackData.getAirborneOperationalStatus().setSil(airborneOperationalStatusV2Msg.getSIL());
        adsbTrackData.getAirborneOperationalStatus().setHasNICSupplementA(airborneOperationalStatusV2Msg.hasNICSupplementA());
        adsbTrackData.getAirborneOperationalStatus().setPositionUncertainty(airborneOperationalStatusV2Msg.getPositionUncertainty());

        adsbTrackData.getAirborneOperationalStatus().setHasSILSupplement(airborneOperationalStatusV2Msg.hasSILSupplement());
    }
}
