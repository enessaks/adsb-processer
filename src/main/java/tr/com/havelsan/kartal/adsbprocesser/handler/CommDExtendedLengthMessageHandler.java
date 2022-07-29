package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.CommDExtendedLengthMsg;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.tools;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.CommDExtendedLEngthData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class CommDExtendedLengthMessageHandler implements MessageHandler<CommDExtendedLengthMsg> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.COMM_D_ELM;
    }

    @Override
    public void handle(CommDExtendedLengthMsg commDExtendedLengthMsg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(commDExtendedLengthMsg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(commDExtendedLengthMsg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setCommDExtendedLength(new CommDExtendedLEngthData());
        adsbTrackData.getCommDExtendedLength().setSeqno(commDExtendedLengthMsg.getSequenceNumber());
        adsbTrackData.getCommDExtendedLength().setAck(commDExtendedLengthMsg.isAck());
        adsbTrackData.getCommDExtendedLength().setMessage(tools.toHexString(commDExtendedLengthMsg.getMessage()));
    }
    //+
}
