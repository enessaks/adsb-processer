package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.AllCallReply;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.tools;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.AllCallReplyData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class AllCallReplyMessageHandler implements MessageHandler<AllCallReply>{
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ALL_CALL_REPLY;
    }

    @Override
    public void handle(AllCallReply allCallReply) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(allCallReply.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(allCallReply.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setAllCallReply(new AllCallReplyData());

        adsbTrackData.getAllCallReply().setCapabilities(allCallReply.getCapabilities());
        adsbTrackData.getAllCallReply().setCodeLabel(allCallReply.getCodeLabel());
        adsbTrackData.getAllCallReply().setParityInterrogator(allCallReply.calcParity());
        adsbTrackData.getAllCallReply().setAirborne(allCallReply.isAirborne());
        adsbTrackData.getAllCallReply().setOnGround(allCallReply.isOnGround());
        adsbTrackData.getAllCallReply().setHasValidInterrogatorCode(allCallReply.hasValidInterrogatorCode());
        adsbTrackData.getAllCallReply().setSurveillanceID(allCallReply.isSurveillanceID());
        adsbTrackData.getAllCallReply().setInterrogatorCode(tools.toHexString(allCallReply.getInterrogatorCode()));
    }//+
}
