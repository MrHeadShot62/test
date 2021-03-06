package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by novak on 18.01.2017.
 */
public class AuthorisationPacket extends CorePacket implements Serializable {
    private final Object[] data;
    private final byte type;

    public AuthorisationPacket(byte type, Object... data) {
        this.data = data;
        this.type = type;
    }

    public byte getType() {
        return type;
    }

    public Object[] getData() {
        return data;
    }
}
