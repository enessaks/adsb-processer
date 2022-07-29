package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.CommBIdentifyReply;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.CommBIdentifyReplyData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class CommBIdentifyReplyMessageHandler implements MessageHandler<CommBIdentifyReply> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.COMM_B_IDENTIFY_REPLY;
    }
    @Override
    public void handle(CommBIdentifyReply commBIdentifyReply) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(commBIdentifyReply.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(commBIdentifyReply.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setCommBIdentifyReply(new CommBIdentifyReplyData());

        adsbTrackData.getCommBIdentifyReply().setMessage(commBIdentifyReply.getMessage());
        adsbTrackData.getCommBIdentifyReply().setDownlinkRequest(commBIdentifyReply.getDownlinkRequest());
        adsbTrackData.getCommBIdentifyReply().setUtilityMsg(commBIdentifyReply.getUtilityMsg());
        adsbTrackData.getCommBIdentifyReply().setFlightStatus(commBIdentifyReply.getFlightStatus());
        adsbTrackData.getCommBIdentifyReply().setIdentity(commBIdentifyReply.getIdentity());
        adsbTrackData.getCommBIdentifyReply().setHasSPI(commBIdentifyReply.hasSPI());
        adsbTrackData.getCommBIdentifyReply().setOnGround(commBIdentifyReply.isOnGround());
        adsbTrackData.getCommBIdentifyReply().setAirborne(commBIdentifyReply.isAirborne());
        adsbTrackData.getCommBIdentifyReply().setInterrogatorIdentifier(commBIdentifyReply.getInterrogatorIdentifier());
        adsbTrackData.getCommBIdentifyReply().setIdentifierDesignator(commBIdentifyReply.getIdentifierDesignator());
        adsbTrackData.getCommBIdentifyReply().setHasAlert(commBIdentifyReply.hasAlert());
    }
}//+