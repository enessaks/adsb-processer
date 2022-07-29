package tr.com.havelsan.kartal.adsbprocesser.handler;

import org.opensky.libadsb.msgs.ModeSReply;

public interface MessageHandler<T extends ModeSReply> {
    ModeSReply.subtype getType();

    void handle(T modeSReply);
}
