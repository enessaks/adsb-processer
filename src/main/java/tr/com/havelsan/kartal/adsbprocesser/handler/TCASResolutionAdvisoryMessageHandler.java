package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.msgs.TCASResolutionAdvisoryMsg;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.TCASResolutionAdvisoryData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class TCASResolutionAdvisoryMessageHandler implements MessageHandler<TCASResolutionAdvisoryMsg> {
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_TCAS;
    }

    @Override
    public void handle(TCASResolutionAdvisoryMsg tcasResolutionAdvisoryMsg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();

        if (adsbTrackRepository.get(new String(tcasResolutionAdvisoryMsg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(tcasResolutionAdvisoryMsg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setTcasResolutionAdvisory(new TCASResolutionAdvisoryData());

        adsbTrackData.getTcasResolutionAdvisory().setMultiThreatEncounter(tcasResolutionAdvisoryMsg.hasMultiThreatEncounter());
        adsbTrackData.getTcasResolutionAdvisory().setMsgSubtype(tcasResolutionAdvisoryMsg.getSubtype());
        adsbTrackData.getTcasResolutionAdvisory().setActiveRa(tcasResolutionAdvisoryMsg.getActiveRA());
        adsbTrackData.getTcasResolutionAdvisory().setRaTerminated(tcasResolutionAdvisoryMsg.hasRATerminated());
        adsbTrackData.getTcasResolutionAdvisory().setRacsRecord(tcasResolutionAdvisoryMsg.getRACRecord());
        adsbTrackData.getTcasResolutionAdvisory().setThreatType(tcasResolutionAdvisoryMsg.getThreatType());

        if (tcasResolutionAdvisoryMsg.getThreatType() == 1) {
            adsbTrackData.getTcasResolutionAdvisory().setThreatIdentity(tcasResolutionAdvisoryMsg.getThreatIdentity());
        }
    }//+
}
