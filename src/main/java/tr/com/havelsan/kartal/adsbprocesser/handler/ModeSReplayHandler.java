package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.ModeSReplyData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Slf4j
@Component
public class ModeSReplayHandler implements MessageHandler<ModeSReply> {
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.MODES_REPLY;
    }

    @Override
    public void handle(ModeSReply modeSReply) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(modeSReply.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(modeSReply.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setModeSReplay(new ModeSReplyData());

        adsbTrackData.getModeSReplay().setFirstField(modeSReply.getFirstField());
        adsbTrackData.getModeSReplay().setDownlinkFormat(modeSReply.getDownlinkFormat());
        adsbTrackData.getModeSReplay().setParity(modeSReply.getParity());
        adsbTrackData.getModeSReplay().setNoCRC(modeSReply.checkParity());
        adsbTrackData.getModeSReplay().setIcao24(modeSReply.getIcao24());
        adsbTrackData.getModeSReplay().setPayload(modeSReply.getPayload());
    }
}