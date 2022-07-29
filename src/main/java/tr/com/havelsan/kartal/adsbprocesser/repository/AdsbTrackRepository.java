package tr.com.havelsan.kartal.adsbprocesser.repository;

import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.model.AdsbTrackData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AdsbTrackRepository {

    private Map<String, AdsbTrackData> adsbTrackDataMap = new ConcurrentHashMap<>();

    public void put(String key, AdsbTrackData adsbTrackData) {
        adsbTrackDataMap.put(key, adsbTrackData);
    }

    public AdsbTrackData get(String key) {
        return adsbTrackDataMap.get(key);
    }

    public void delete(String key) {
        adsbTrackDataMap.remove(key);
    }

}
