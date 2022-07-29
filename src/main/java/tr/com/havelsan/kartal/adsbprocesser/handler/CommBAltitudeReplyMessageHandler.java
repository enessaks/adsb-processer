package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.CommBAltitudeReplyData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class CommBAltitudeReplyMessageHandler implements MessageHandler<org.opensky.libadsb.msgs.CommBAltitudeReply>{
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.COMM_B_ALTITUDE_REPLY;
    }

    @Override
    public void handle(org.opensky.libadsb.msgs.CommBAltitudeReply commBAltitudeReply) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(commBAltitudeReply.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(commBAltitudeReply.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setCommBAltitudeReply(new CommBAltitudeReplyData());
        adsbTrackData.getCommBAltitudeReply().setMessage(commBAltitudeReply.getMessage());
        adsbTrackData.getCommBAltitudeReply().setDownlinkRequest(commBAltitudeReply.getDownlinkRequest());
        adsbTrackData.getCommBAltitudeReply().setUtilityMsg(commBAltitudeReply.getUtilityMsg());
        adsbTrackData.getCommBAltitudeReply().setFlightStatus(commBAltitudeReply.getFlightStatus());
        adsbTrackData.getCommBAltitudeReply().setHasSPI(commBAltitudeReply.hasSPI());
        adsbTrackData.getCommBAltitudeReply().setOnGround(commBAltitudeReply.isOnGround());
        adsbTrackData.getCommBAltitudeReply().setHasAlert(commBAltitudeReply.hasAlert());
        adsbTrackData.getCommBAltitudeReply().setIdentifierDesignator(commBAltitudeReply.getIdentifierDesignator());
        adsbTrackData.getCommBAltitudeReply().setInterrogatorIdentifier(commBAltitudeReply.getInterrogatorIdentifier());
        adsbTrackData.getCommBAltitudeReply().setAirborne(commBAltitudeReply.isAirborne());
        adsbTrackData.getCommBAltitudeReply().setAltitude(commBAltitudeReply.getAltitude());
    }//+
}
