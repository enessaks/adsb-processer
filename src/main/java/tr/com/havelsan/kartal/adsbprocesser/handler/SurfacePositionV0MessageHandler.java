package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.msgs.SurfacePositionV0Msg;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.SurfacePositionData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class SurfacePositionV0MessageHandler implements MessageHandler<SurfacePositionV0Msg> {
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_SURFACE_POSITION_V0;
    }

    @Override
    public void handle(SurfacePositionV0Msg surfacePositionV0Msg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(surfacePositionV0Msg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(surfacePositionV0Msg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setSurfacePosition(new SurfacePositionData());

        adsbTrackData.getSurfacePosition().setHeading(surfacePositionV0Msg.getHeading());
        adsbTrackData.getSurfacePosition().setHorizontalPositionAvailable();
        adsbTrackData.getSurfacePosition().setCprEncodedLat();
        adsbTrackData.getSurfacePosition().setCprFormat();
        adsbTrackData.getSurfacePosition().setCprEncodedLon();
        adsbTrackData.getSurfacePosition().setTimeFlag();
        adsbTrackData.getSurfacePosition().setHeadingstatus();
        adsbTrackData.getSurfacePosition().setMovement();
        adsbTrackData.getSurfacePosition().setNicCupplC();
        adsbTrackData.getSurfacePosition().setNicSupplA();
    }
}