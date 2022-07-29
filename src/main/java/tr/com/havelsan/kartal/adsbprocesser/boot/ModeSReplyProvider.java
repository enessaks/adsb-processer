package tr.com.havelsan.kartal.adsbprocesser.boot;

import lombok.RequiredArgsConstructor;
import org.opensky.libadsb.exceptions.BadFormatException;
import org.opensky.libadsb.exceptions.UnspecifiedFormatError;
import org.opensky.libadsb.msgs.*;
import org.springframework.stereotype.Component;
import tr.com.havelsan.kartal.adsbprocesser.handler.MessageHandlerFactory;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ModeSReplyProvider {

    private final MessageHandlerFactory messageHandlerFactory;

    @PostConstruct
    public void generateTestMessage() throws BadFormatException, UnspecifiedFormatError {
        ModeSReply baseMessage = new ModeSReply("8D4840D6202CC371C32CE0576098");
        ExtendedSquitter extendedSquitter = new ExtendedSquitter(baseMessage);
        ExtendedSquitter IdentificationMsg= new IdentificationMsg(extendedSquitter);
        messageHandlerFactory.getMessageHandler(IdentificationMsg.getType()).handle(baseMessage);
    }
}
