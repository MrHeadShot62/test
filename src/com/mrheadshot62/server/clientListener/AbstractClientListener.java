package com.mrheadshot62.server.clientListener;

import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.types.*;
import com.mrheadshot62.server.Client;


abstract class AbstractClientListener extends Thread{
    protected Client client;
    private BlueBearInputStream input;

    AbstractClientListener(Client client) {
        this.client = client;
        this.input = client.getInput();
    }

    @Override
    public synchronized void start() {
        super.start();
        System.out.println("Started client listener #"+client.getId());
    }

    @Override
    public void run() {
        while (input != null){
            try {
                Object o = input.readObject();
                if (!(o instanceof Packet)) throw new ClassNotFoundException("Input object is not Packet");
                Packet packet = (Packet)o;
                onReceivePacket(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    protected void onReceivePacket(Packet p){
        try {
            switch (p.getType()) {
                case Types.Image:
                    onReceiveImage((Image) p.getData());
                    break;
                case Types.Command:
                    onReceiveCommand((Command) p.getData());
                    break;
                case Types.USER:
                    onReceivedUser((User) p.getData());
                default:
                    throw new ClassNotFoundException("Invalid data type");
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    protected abstract void onReceiveImage(Image image);
    protected abstract void onReceiveCommand(Command command);
    protected abstract void onReceivedUser(User user);
    protected abstract void onClientDisconnected(Client client);
}
