package org.tlabs.md.cbs.integration.util;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

public class ClientPasswordCallback implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) {

        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        pc.setPassword("coder");
    }
}
