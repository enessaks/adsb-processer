package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.AirspeedHeadingMsg;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.AirspeedHeadingData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class AirspeedHeadingMessageHandler implements MessageHandler<AirspeedHeadingMsg> {
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_AIRSPEED;
    }

    @Override
    public void handle(AirspeedHeadingMsg airspeedHeadingMsg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(airspeedHeadingMsg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(airspeedHeadingMsg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setAirspeedHeading(new AirspeedHeadingData());

        adsbTrackData.getAirspeedHeading().setVerticalSource(airspeedHeadingMsg.isBarometricVerticalSpeed());
        adsbTrackData.getAirspeedHeading().setVerticalRateInfoAvailable(airspeedHeadingMsg.hasVerticalRateInfo());
        adsbTrackData.getAirspeedHeading().setIntentChange(airspeedHeadingMsg.hasChangeIntent());
        adsbTrackData.getAirspeedHeading().setIfrCapability(airspeedHeadingMsg.hasIFRCapability());
        adsbTrackData.getAirspeedHeading().setHeading(airspeedHeadingMsg.getHeading());
        adsbTrackData.getAirspeedHeading().setHeadingStatusBit(airspeedHeadingMsg.hasHeadingStatusFlag());
        adsbTrackData.getAirspeedHeading().setGeoMinusBaro(airspeedHeadingMsg.getGeoMinusBaro());
        adsbTrackData.getAirspeedHeading().setGeoMinusBaroAvailable(airspeedHeadingMsg.hasGeoMinusBaroInfo());
        adsbTrackData.getAirspeedHeading().setAirspeedAvailable(airspeedHeadingMsg.isTrueAirspeed());
        adsbTrackData.getAirspeedHeading().setAirspeed(airspeedHeadingMsg.getAirspeed());
        adsbTrackData.getAirspeedHeading().setTrueAirspeed(airspeedHeadingMsg.isTrueAirspeed());
        adsbTrackData.getAirspeedHeading().setSupersonic(airspeedHeadingMsg.isSupersonic());
        adsbTrackData.getAirspeedHeading().setNACv(airspeedHeadingMsg.getNACv());

        if (airspeedHeadingMsg.hasVerticalRateInfo()) {
            adsbTrackData.getAirspeedHeading().setVerticalRate(airspeedHeadingMsg.getVerticalRate());
        }
    }//+
}

