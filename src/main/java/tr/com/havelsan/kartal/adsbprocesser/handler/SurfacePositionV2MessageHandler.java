package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.msgs.SurfacePositionV2Msg;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.SurfacePositionData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class SurfacePositionV2MessageHandler implements MessageHandler<SurfacePositionV2Msg> {
    private final AdsbTrackRepository adsbTrackRepository;

    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_SURFACE_POSITION_V2;
    }

    @Override
    public void handle(SurfacePositionV2Msg surfacePositionV2Msg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(surfacePositionV2Msg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(surfacePositionV2Msg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setSurfacePosition(new SurfacePositionData());

    }
}
