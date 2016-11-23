package com.linlong.f10.core.util.https;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class MyX509TrustManager implements X509TrustManager{

    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
        System.out.println("checkClientTrusted");
    }

    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
        
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}