package com.mrheadshot62.server.storage;

import com.mrheadshot62.api.types.UserPacket;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.ServerUser;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;

/**
 * Created by novak on 04.01.2017.
 */
public class ServerStorage {
    private DataBase dataBase;
    public ServerStorage() {
        this.dataBase = new DataBase();
    }

    public ArrayList<Client> getClients(){
        return dataBase.getClients();
    }

    public void addClient(Client client){
        dataBase.getClients().add(client);
    }

    public Client getClient(int id){
        return dataBase.getClients().get(id);
    }

    public void removeClient(int id){
        for (Client c:getClients()){
            if (c.getId() == id){
                getClients().remove(c);
                return;
            }
        }
    }

    @Nullable
    public ServerUser findServerUserById(int id){
        for (ServerUser u:dataBase.getServerUsers()){
            if (u.getId() == id){
                return u;
            }
        }
        return null;
    }

    @Nullable
    public ServerUser findServerUserByUser(UserPacket user){
        for (ServerUser u:dataBase.getServerUsers()){
            if (u.getUser().equals(user)){
                return u;
            }
        }
        return null;
    }

    public int createServerUser(UserPacket user){
        ServerUser serverUser = new ServerUser(user, dataBase.getServerUsers().size()+100);
        dataBase.getServerUsers().add(serverUser);
        return serverUser.getId();
    }
}
