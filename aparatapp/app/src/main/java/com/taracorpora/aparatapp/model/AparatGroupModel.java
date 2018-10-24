package com.taracorpora.aparatapp.model;

public class AparatGroupModel {
    public int idgroup;
    public String name;

    public AparatGroupModel(int idgroup, String name) {
        this.idgroup = idgroup;
        this.name = name;
    }

    public int getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
