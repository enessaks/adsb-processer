package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.AltitudeReply;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.AltitudeReplyData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class AltitudeReplyMessageHandler implements MessageHandler<AltitudeReply>{
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ALTITUDE_REPLY;
    }

    @Override
    public void handle(AltitudeReply altitudeReply) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(altitudeReply.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(altitudeReply.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setAltitudeReply(new AltitudeReplyData());

        adsbTrackData.getAltitudeReply().setIdentifierDesignator(altitudeReply.getIdentifierDesignator());
        adsbTrackData.getAltitudeReply().setInterrogatorIdentifier(altitudeReply.getInterrogatorIdentifier());
        adsbTrackData.getAltitudeReply().setHasSPI(altitudeReply.hasSPI());
        adsbTrackData.getAltitudeReply().setOnGround(altitudeReply.isOnGround());
        adsbTrackData.getAltitudeReply().setHasAlert(altitudeReply.hasAlert());
        adsbTrackData.getAltitudeReply().setAirborne(altitudeReply.isAirborne());
        adsbTrackData.getAltitudeReply().setUtilityMsg(altitudeReply.getUtilityMsg());
        adsbTrackData.getAltitudeReply().setAltitudeCode(altitudeReply.getAltitudeCode());
        adsbTrackData.getAltitudeReply().setFlightStatus(altitudeReply.getFlightStatus());
        adsbTrackData.getAltitudeReply().setAltitude(altitudeReply.getAltitude());
        adsbTrackData.getAltitudeReply().setDownlinkRequest(altitudeReply.getDownlinkRequest());
    }
}//+
