package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.msgs.ShortACAS;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.ShortACASData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class ShortACASMessageHandler implements MessageHandler<ShortACAS> {
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.SHORT_ACAS;
    }

    @Override
    public void handle(ShortACAS shortACAS) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(shortACAS.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(shortACAS.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setShortACAS(new ShortACASData());

        adsbTrackData.getShortACAS().setCrossLinkCapability(shortACAS.hasCrossLinkCapability());
        adsbTrackData.getShortACAS().setAltitude(shortACAS.getAltitude());
        adsbTrackData.getShortACAS().setAltitudeCode(shortACAS.getAltitudeCode());
        adsbTrackData.getShortACAS().setSensitivityLevel(shortACAS.getSensitivityLevel());
        adsbTrackData.getShortACAS().setAirborne(shortACAS.isAirborne());
        adsbTrackData.getShortACAS().setReplyInformation(shortACAS.getReplyInformation());
        adsbTrackData.getShortACAS().setHasVerticalResolutionCapability(shortACAS.hasCrossLinkCapability());
        adsbTrackData.getShortACAS().setHasHorizontalResolutionCapability(shortACAS.hasHorizontalResolutionCapability());
        adsbTrackData.getShortACAS().setMaximumAirspeed(shortACAS.getMaximumAirspeed());
        adsbTrackData.getShortACAS().setHasOperatingACAS(shortACAS.hasOperatingACAS());
    }//+
}