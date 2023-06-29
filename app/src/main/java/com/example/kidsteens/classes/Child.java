package com.example.kidsteens.classes;

import java.util.Objects;

public class Child extends User {
    private int parent_id;
    private User parent;

    public Child(String phone) {
        super(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Child child = (Child) o;
        return this.id == child.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }
}
