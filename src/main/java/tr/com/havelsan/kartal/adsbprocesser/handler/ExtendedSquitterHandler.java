package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensky.libadsb.msgs.ExtendedSquitter;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.ExtendedSquitterData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Slf4j
@Component
public class ExtendedSquitterHandler implements MessageHandler<ExtendedSquitter> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.EXTENDED_SQUITTER;
    }

    @Override
    public void handle(ExtendedSquitter extendedSquitter) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(extendedSquitter.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(extendedSquitter.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setExtendedSquitter(new ExtendedSquitterData());

        adsbTrackData.getExtendedSquitter().setCapabilities(extendedSquitter.getCapabilities());
        adsbTrackData.getExtendedSquitter().setMessage(extendedSquitter.getMessage());
        adsbTrackData.getExtendedSquitter().setFormatTypeCode(extendedSquitter.getFormatTypeCode());
    }//+
}
