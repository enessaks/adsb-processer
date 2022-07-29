package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.IdentificationMsg;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.IdentificationData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

import static java.lang.String.copyValueOf;

@RequiredArgsConstructor
@Component
public class IdentificationMessageHandler implements MessageHandler<IdentificationMsg> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_IDENTIFICATION;
    }

    @Override
    public void handle(IdentificationMsg identificationMsg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(identificationMsg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(identificationMsg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setIdentification(new IdentificationData());
        adsbTrackData.getIdentification().setEmitterCategory(identificationMsg.getEmitterCategory());
        adsbTrackData.getIdentification().setIdentity(copyValueOf(identificationMsg.getIdentity()));
        adsbTrackData.getIdentification().setCategoryDescription(identificationMsg.getCategoryDescription());
    }//+
}
