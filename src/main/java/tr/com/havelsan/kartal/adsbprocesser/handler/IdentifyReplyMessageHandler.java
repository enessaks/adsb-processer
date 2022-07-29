package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensky.libadsb.msgs.IdentifyReply;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.IdentifyReplyData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;


@RequiredArgsConstructor
@Slf4j
@Component
public class IdentifyReplyMessageHandler implements MessageHandler<IdentifyReply> {
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.IDENTIFY_REPLY;
    }

    @Override
    public void handle(IdentifyReply identifyReply) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(identifyReply.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(identifyReply.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setIdentifyReply(new IdentifyReplyData());
        adsbTrackData.getIdentifyReply().setIdentity(identifyReply.getIdentity());
        adsbTrackData.getIdentifyReply().setDownlinkRequest(identifyReply.getDownlinkRequest());
        adsbTrackData.getIdentifyReply().setFlightStatus(identifyReply.getFlightStatus());
        adsbTrackData.getIdentifyReply().setUtilityMsg(identifyReply.getUtilityMsg());
        adsbTrackData.getIdentifyReply().setAirborne(identifyReply.isAirborne());
        adsbTrackData.getIdentifyReply().setHasAlert(identifyReply.hasAlert());
        adsbTrackData.getIdentifyReply().setOnGround(identifyReply.isOnGround());
        adsbTrackData.getIdentifyReply().setHasSPI(identifyReply.hasSPI());
        adsbTrackData.getIdentifyReply().setIdentifierDesignator(identifyReply.getIdentifierDesignator());
        adsbTrackData.getIdentifyReply().setInterrogatorIdentifier(identifyReply.getInterrogatorIdentifier());
    }
}//+