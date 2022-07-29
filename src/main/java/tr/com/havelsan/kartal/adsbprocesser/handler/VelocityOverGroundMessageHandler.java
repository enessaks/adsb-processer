package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.msgs.VelocityOverGroundMsg;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.IdentificationData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class VelocityOverGroundMessageHandler implements MessageHandler<VelocityOverGroundMsg> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_VELOCITY;
    }

    @Override
    public void handle(VelocityOverGroundMsg velocityOverGroundMsg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();

        if (adsbTrackRepository.get(new String(velocityOverGroundMsg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(velocityOverGroundMsg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setIdentification(new IdentificationData());

        adsbTrackData.getVelocityOverGround().setGeoMinusBaroAvailable(velocityOverGroundMsg.hasGeoMinusBaroInfo());
        adsbTrackData.getVelocityOverGround().setGeoMinusBaro(velocityOverGroundMsg.getGeoMinusBaro());
        adsbTrackData.getVelocityOverGround().setSupersonic(velocityOverGroundMsg.isSupersonic());
        adsbTrackData.getVelocityOverGround().setNorthToSouthVelocity(velocityOverGroundMsg.getNorthToSouthVelocity());
        adsbTrackData.getVelocityOverGround().setAccuracyBound(velocityOverGroundMsg.getAccuracyBound());
        adsbTrackData.getVelocityOverGround().setIntentChange(velocityOverGroundMsg.hasChangeIntent());
        adsbTrackData.getVelocityOverGround().setIfrCapability(velocityOverGroundMsg.hasIFRCapability());
        adsbTrackData.getVelocityOverGround().setAccuracyBound(velocityOverGroundMsg.getAccuracyBound());
        adsbTrackData.getVelocityOverGround().setVerticalSource(velocityOverGroundMsg.isBarometricVerticalSpeed());

        if (velocityOverGroundMsg.hasVelocityInfo()) {
            adsbTrackData.getVelocityOverGround().setVelocity((velocityOverGroundMsg.getVelocity()));
            adsbTrackData.getVelocityOverGround().setHeading((velocityOverGroundMsg.getHeading()));
        } else {
            adsbTrackData.getVelocityOverGround().setVelocity(-1);
            adsbTrackData.getVelocityOverGround().setHeading(-1);
        }
        if (adsbTrackData.getVelocityOverGround().isVerticalRateInfoAvailable()) {
            adsbTrackData.getVelocityOverGround().setVerticalRate(velocityOverGroundMsg.getVerticalRate());
        }
    }//?
}