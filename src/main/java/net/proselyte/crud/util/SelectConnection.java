package net.proselyte.crud.util;

import net.proselyte.crud.model.ConnectType;

public class SelectConnection {
    private static ConnectType connectType;
    private static SelectConnection INSTANCE = null;

    public static SelectConnection getInstance(){
        if (INSTANCE == null){
            INSTANCE = new SelectConnection();
        }
        return INSTANCE;
    }

    public ConnectType getConnectType() {
        return connectType;
    }

    public void setConnectType(ConnectType connectType) {
        this.connectType = connectType;
    }
}


