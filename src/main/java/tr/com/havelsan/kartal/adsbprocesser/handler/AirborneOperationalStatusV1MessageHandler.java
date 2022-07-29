package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.AirborneOperationalStatusV1Msg;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.AirborneOperationalStatusData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class AirborneOperationalStatusV1MessageHandler implements MessageHandler<AirborneOperationalStatusV1Msg> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_AIRBORN_STATUS_V1;
    }

    @Override
    public void handle(AirborneOperationalStatusV1Msg airborneOperationalStatusV1Msg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(airborneOperationalStatusV1Msg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(airborneOperationalStatusV1Msg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setAirborneOperationalStatus(new AirborneOperationalStatusData());
        adsbTrackData.getAirborneOperationalStatus().setVersion(airborneOperationalStatusV1Msg.getVersion());
        adsbTrackData.getAirborneOperationalStatus().setBarometricAltitudeIntegrityCode(airborneOperationalStatusV1Msg.getBarometricAltitudeIntegrityCode());
        adsbTrackData.getAirborneOperationalStatus().setGeometricVerticalAccuracy(airborneOperationalStatusV1Msg.getGeometricVerticalAccuracy());
        adsbTrackData.getAirborneOperationalStatus().setGeometricVerticalAccuracy(airborneOperationalStatusV1Msg.getGeometricVerticalAccuracy());
        adsbTrackData.getAirborneOperationalStatus().setHorizontalReferenceDirection(airborneOperationalStatusV1Msg.getHorizontalReferenceDirection());
        adsbTrackData.getAirborneOperationalStatus().setHasAirReferencedVelocity(airborneOperationalStatusV1Msg.hasAirReferencedVelocity());
        adsbTrackData.getAirborneOperationalStatus().setHasSingleAntenna(airborneOperationalStatusV1Msg.hasSingleAntenna());
        adsbTrackData.getAirborneOperationalStatus().setHasUATIn(airborneOperationalStatusV1Msg.hasUATIn());
        adsbTrackData.getAirborneOperationalStatus().setHasTCASResolutionAdvisory(airborneOperationalStatusV1Msg.hasTCASResolutionAdvisory());
        adsbTrackData.getAirborneOperationalStatus().setHasOperationalTCAS(airborneOperationalStatusV1Msg.hasOperationalTCAS());
        adsbTrackData.getAirborneOperationalStatus().setHasActiveIDENTSwitch(airborneOperationalStatusV1Msg.hasActiveIDENTSwitch());
        adsbTrackData.getAirborneOperationalStatus().setHas1090ESIn(airborneOperationalStatusV1Msg.has1090ESIn());
        adsbTrackData.getAirborneOperationalStatus().setSystemDesignAssurance(airborneOperationalStatusV1Msg.getSystemDesignAssurance());
        adsbTrackData.getAirborneOperationalStatus().setSil(airborneOperationalStatusV1Msg.getSIL());
        adsbTrackData.getAirborneOperationalStatus().setHasNICSupplementA(airborneOperationalStatusV1Msg.hasNICSupplementA());
        adsbTrackData.getAirborneOperationalStatus().setPositionUncertainty(airborneOperationalStatusV1Msg.getPositionUncertainty());
    }
}