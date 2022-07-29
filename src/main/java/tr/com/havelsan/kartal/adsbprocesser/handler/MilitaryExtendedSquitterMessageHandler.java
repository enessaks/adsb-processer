package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.MilitaryExtendedSquitter;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.tools;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.IdentificationData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class MilitaryExtendedSquitterMessageHandler implements MessageHandler<MilitaryExtendedSquitter> {
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.MILITARY_EXTENDED_SQUITTER;
    }

    @Override
    public void handle(MilitaryExtendedSquitter militaryExtendedSquitter) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(militaryExtendedSquitter.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(militaryExtendedSquitter.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setIdentification(new IdentificationData());
        adsbTrackData.getMilitaryExtendedSquitter().setApplicationCode(militaryExtendedSquitter.getApplicationCode());
        adsbTrackData.getMilitaryExtendedSquitter().setMessage(tools.toHexString(militaryExtendedSquitter.getMessage()));
    }
    //+
}
