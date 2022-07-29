package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.Position;
import org.opensky.libadsb.msgs.AirbornePositionV0Msg;
import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.decoder.ModeSDecoder;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.AirbornePositionData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class AirbornePositionV0MessageHandler implements MessageHandler<AirbornePositionV0Msg> {
    private final AdsbTrackRepository adsbTrackRepository;
    private ModeSDecoder decoder= new ModeSDecoder();

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_AIRBORN_POSITION_V0;
    }

    @Override
    public void handle(AirbornePositionV0Msg airbornePositionV0Msg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if(adsbTrackRepository.get(new String(airbornePositionV0Msg.getIcao24(), StandardCharsets.UTF_8))== null){
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(airbornePositionV0Msg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setAirbornePosition(new AirbornePositionData());

        Position position = decoder.decodePosition(0L, airbornePositionV0Msg, new Position());
        if (position != null){
            position.getLongitude(position.getLongitude());
            adsbTrackData.getAirbornePosition().setLongitude(position.getLongitude());
            adsbTrackData.getAirbornePosition().setHorizontalContainmentRadiusLimit(airbornePositionV0Msg.getHorizontalContainmentRadiusLimit());
        }
        adsbTrackData.getAirbornePosition().isBarometricAltitude(airbornePositionV0Msg.isBarometricAltitude());

        adsbTrackData.getAirbornePosition().setCprEncodedLat(airbornePositionV0Msg.);
        adsbTrackData.getAirbornePosition().setCprFormat();
        adsbTrackData.getAirbornePosition().setCprEncodedLon();
        adsbTrackData.getAirbornePosition().setNicSupplA();
        adsbTrackData.getAirbornePosition().setNicSupplB();
        adsbTrackData.getAirbornePosition().setTimeFlag();
        adsbTrackData.getAirbornePosition().setAltitudeAvailable();
        adsbTrackData.getAirbornePosition().setAltitudeEncoded();
        adsbTrackData.getAirbornePosition().setSurveillanceStatus();
        adsbTrackData.getAirbornePosition().setHorizontalPositionAvailable();
    }
}