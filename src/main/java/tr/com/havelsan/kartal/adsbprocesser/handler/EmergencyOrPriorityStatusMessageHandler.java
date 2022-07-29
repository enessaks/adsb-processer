package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.EmergencyOrPriorityStatusMsg;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.EmergencyOrPriorityStatusData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class EmergencyOrPriorityStatusMessageHandler implements MessageHandler<EmergencyOrPriorityStatusMsg> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_EMERGENCY;
    }

    @Override
    public void handle(EmergencyOrPriorityStatusMsg emergencyOrPriorityStatusMsg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(emergencyOrPriorityStatusMsg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(emergencyOrPriorityStatusMsg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setEmergencyOrPriorityStatus(new EmergencyOrPriorityStatusData());

        adsbTrackData.getEmergencyOrPriorityStatus().setEmergencyState(emergencyOrPriorityStatusMsg.getEmergencyStateText());
        adsbTrackData.getEmergencyOrPriorityStatus().setMsgsubtype(emergencyOrPriorityStatusMsg.getSubtype());
        adsbTrackData.getEmergencyOrPriorityStatus().setModeACode(emergencyOrPriorityStatusMsg.getModeACode());
    } //+
}
