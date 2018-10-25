package com.taracorpora.aparatapp.model;

public class AparatGroupMemberModel {
    public int id;
    public int idgroup;
    public String idfb;
    public Boolean is_admin;
    public String name;
    public String profilepict;

    public AparatGroupMemberModel(int id, int idgroup, String idfb, Boolean is_admin, String name, String profilepict) {
        this.id = id;
        this.idgroup = idgroup;
        this.idfb = idfb;
        this.is_admin = is_admin;
        this.name = name;
        this.profilepict = profilepict;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }

    public String getIdfb() {
        return idfb;
    }

    public void setIdfb(String idfb) {
        this.idfb = idfb;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilepict() {
        return profilepict;
    }

    public void setProfilepict(String profilepict) {
        this.profilepict = profilepict;
    }
}
