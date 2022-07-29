package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.LongACAS;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.LongACASData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class LongACASMessageHandler implements MessageHandler<LongACAS> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.LONG_ACAS;
    }

    @Override
    public void handle(LongACAS longACAS) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(longACAS.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(longACAS.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setLongACAS(new LongACASData());
        adsbTrackData.getLongACAS().setSensitivityLevel(longACAS.getSensitivityLevel());
        adsbTrackData.getLongACAS().setMultipleThreatEncounter(longACAS.hasMultipleThreats());
        adsbTrackData.getLongACAS().setAltitudeCode(longACAS.getAltitudeCode());
        adsbTrackData.getLongACAS().setAltitude(longACAS.getAltitude());
        adsbTrackData.getLongACAS().setRaTerminated(longACAS.hasTerminated());
        adsbTrackData.getLongACAS().setValidRac(longACAS.hasValidRAC());
        adsbTrackData.getLongACAS().setAirborne(longACAS.isAirborne());
        adsbTrackData.getLongACAS().setActiveResolutionAdvisories(longACAS.getActiveResolutionAdvisories());
        adsbTrackData.getLongACAS().setMaximumAirspeed(longACAS.getMaximumAirspeed());
        adsbTrackData.getLongACAS().setNoPassAbove(longACAS.noPassAbove());
        adsbTrackData.getLongACAS().setNoTurnLeft(longACAS.noTurnLeft());
        adsbTrackData.getLongACAS().setNoTurnRight(longACAS.noTurnRight());
        adsbTrackData.getLongACAS().setNoPassBelow(longACAS.noPassBelow());
        adsbTrackData.getLongACAS().setRacsRecord(longACAS.getResolutionAdvisoryComplement());
        adsbTrackData.getLongACAS().setReplyInformation(longACAS.getReplyInformation());
        adsbTrackData.getLongACAS().setResolutionAdvisoryComplement(longACAS.getResolutionAdvisoryComplement());
        adsbTrackData.getLongACAS().setHasOperatingACAS(longACAS.hasOperatingACAS());
    }//+
}
