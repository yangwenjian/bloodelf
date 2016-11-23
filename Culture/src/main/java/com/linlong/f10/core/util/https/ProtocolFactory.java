package com.linlong.f10.core.util.https;


public class ProtocolFactory {

    public static Protocol createProtocol(ProtocolEnum protocolName) {
        Protocol protocol = null;
        switch (protocolName) {
            case HTTP:
                protocol = new HTTPSProtocol();
                break;
            case HTTPS:
                protocol = new HTTPSProtocol();
                break;
        }
        return protocol;
    }
}
