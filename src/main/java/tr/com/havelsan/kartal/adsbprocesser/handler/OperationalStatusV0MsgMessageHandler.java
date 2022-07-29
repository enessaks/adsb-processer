package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.msgs.OperationalStatusV0Msg;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.OperationalStatusData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class OperationalStatusV0MsgMessageHandler implements MessageHandler<OperationalStatusV0Msg> {
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_STATUS_V0;
    }

    @Override
    public void handle(OperationalStatusV0Msg operationalStatusV0Msg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(operationalStatusV0Msg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(operationalStatusV0Msg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setOperationalStatus(new OperationalStatusData());

        adsbTrackData.getOperationalStatus().setHasOperationalCDTI(operationalStatusV0Msg.hasOperationalCDTI());
        adsbTrackData.getOperationalStatus().setHasOperationalTCAS(operationalStatusV0Msg.hasOperationalTCAS());
        adsbTrackData.getOperationalStatus().setEnroute_capabilities(operationalStatusV0Msg.getCapabilities());
        adsbTrackData.getOperationalStatus().setVersion(operationalStatusV0Msg.getVersion());
    }//+
}
