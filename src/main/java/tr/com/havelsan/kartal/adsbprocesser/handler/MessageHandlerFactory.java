package tr.com.havelsan.kartal.adsbprocesser.handler;

import org.opensky.libadsb.msgs.ModeSReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MessageHandlerFactory {

    private final Map<ModeSReply.subtype,MessageHandler> messageHandlerMap;

    @Autowired
    public MessageHandlerFactory(List<MessageHandler> messageHandlers){
        messageHandlerMap = messageHandlers.stream().collect(Collectors.toMap(MessageHandler::getType, messageHandler -> messageHandler));
    }

    public MessageHandler getMessageHandler(ModeSReply.subtype subtype){
        if(!messageHandlerMap.containsKey(subtype)){
            throw new RuntimeException();
        }
        return messageHandlerMap.get(subtype);
    }
}
