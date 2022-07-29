package tr.com.havelsan.kartal.adsbprocesser.handler;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.msgs.ModeSReply;
import org.opensky.libadsb.msgs.TargetStateAndStatusMsg;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;
import tr.com.havelsan.kartal.adsbprocesser.model.TargetStateAndStatusData;
import tr.com.havelsan.kartal.adsbprocesser.repository.AdsbTrackRepository;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class TargetStateAndStatusMessageHandler implements MessageHandler<TargetStateAndStatusMsg> {
    private final AdsbTrackRepository adsbTrackRepository;


    @Override
    public ModeSReply.subtype getType() {
        return ModeSReply.subtype.ADSB_TARGET_STATE_AND_STATUS;
    }

    @Override
    public void handle(TargetStateAndStatusMsg targetStateAndStatusMsg) {
        AdsbTrackData adsbTrackData = new AdsbTrackData();
        if (adsbTrackRepository.get(new String(targetStateAndStatusMsg.getIcao24(), StandardCharsets.UTF_8)) == null) {
            adsbTrackRepository.put(adsbTrackData.getIcaoCode(), adsbTrackData);
        } else {
            adsbTrackData = adsbTrackRepository.get(new String(targetStateAndStatusMsg.getIcao24(), StandardCharsets.UTF_8));
        }
        adsbTrackData.setTargetStateAndStatus(new TargetStateAndStatusData());

        adsbTrackData.getTargetStateAndStatus().setApproachMode(targetStateAndStatusMsg.hasActiveApproachMode());
        adsbTrackData.getTargetStateAndStatus().setMcpFcuStatus(targetStateAndStatusMsg.getBarometricAltitudeIntegrityCode());
        adsbTrackData.getTargetStateAndStatus().setAutopilotEngaged(targetStateAndStatusMsg.hasAutopilotEngaged());
        adsbTrackData.getTargetStateAndStatus().setAltitudeHoldMode(targetStateAndStatusMsg.hasActiveAltitudeHoldMode());
        adsbTrackData.getTargetStateAndStatus().setSil(targetStateAndStatusMsg.getSIL());
        adsbTrackData.getTargetStateAndStatus().setSilSuppl(targetStateAndStatusMsg.hasSILSupplement());
        adsbTrackData.getTargetStateAndStatus().setSelectedHeading(targetStateAndStatusMsg.getSelectedAltitude());
        adsbTrackData.getTargetStateAndStatus().setSelectedAltitudeType(targetStateAndStatusMsg.hasSelectedAltitudeInfo());
        adsbTrackData.getTargetStateAndStatus().setSelectectedHeadingStatus(targetStateAndStatusMsg.hasSelectedHeadingInfo());
        adsbTrackData.getTargetStateAndStatus().setSelectectedHeadingSign(targetStateAndStatusMsg.hasSelectedHeadingInfo());
        adsbTrackData.getTargetStateAndStatus().setNacP(targetStateAndStatusMsg.getNACp());
        adsbTrackData.getTargetStateAndStatus().setNicBaro(targetStateAndStatusMsg.getBarometricAltitudeIntegrityCode());
        adsbTrackData.getTargetStateAndStatus().setVnavModeEngaged(targetStateAndStatusMsg.hasVNAVModeEngaged());
        adsbTrackData.getTargetStateAndStatus().setLnavModeEngaged(targetStateAndStatusMsg.hasLNAVModeEngaged());
        adsbTrackData.getTargetStateAndStatus().setHasOperationalTcas(targetStateAndStatusMsg.hasOperationalTCAS());
        adsbTrackData.getTargetStateAndStatus().setSelectedAltitude(targetStateAndStatusMsg.getSelectedAltitude());
        adsbTrackData.getTargetStateAndStatus().setHasSelectedAltitudeInfo(targetStateAndStatusMsg.hasSelectedAltitudeInfo());
        adsbTrackData.getTargetStateAndStatus().setHasBarometricPressureSettingInfo(targetStateAndStatusMsg.hasBarometricPressureSettingInfo());
        adsbTrackData.getTargetStateAndStatus().setBarometricPressureSetting(targetStateAndStatusMsg.getBarometricPressureSetting());
        adsbTrackData.getTargetStateAndStatus().setHasSelectedHeadingInfo(targetStateAndStatusMsg.hasSelectedHeadingInfo());
        adsbTrackData.getTargetStateAndStatus().setSelectedHeading(targetStateAndStatusMsg.getSelectedHeading());
        adsbTrackData.getTargetStateAndStatus().setHasModeInfo(targetStateAndStatusMsg.hasModeInfo());
    }
}//+