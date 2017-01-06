package com.mrheadshot62.server.serverListener;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.Types;
import com.mrheadshot62.api.types.ImagePacket;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.clientListener.ClientListener;
import com.mrheadshot62.server.storage.ServerStorage;

/**
 * Created by novak on 04.01.2017.
 */
public class ServerListener extends AbstractServerListener {
    public ServerListener(ServerStorage storage) {
        super(storage);
    }


    public void sendPacket(Packet... packets){
        boolean valid=false;
        for (Packet p:packets){
            if (p.getType() == Types.PERMISSION) valid=true;
        }
        if (!valid) return;
        MultiPacket multiPacket = new MultiPacket(packets);

    }

    @Override
    protected void onListenerAttached(ClientListener clientListener) {
        super.onListenerAttached(clientListener);
    }

    @Override
    protected void onServerStarted() {
        super.onServerStarted();
        System.out.println("Server Started!");
    }

    @Override
    protected void onServerStopped() {
        super.onServerStopped();
        System.out.println("Server Stopped!");
    }

    @Override
    protected void onClientConnected(Client c) {
        System.out.printf("Client #%d connected!%n", c.getId());
    }

    @Override
    protected void onClientDisconnected(Client c) {
        System.out.printf("Client #%d disconnected!%n", c.getId());
        storage.removeClient(c.getId());
    }
}