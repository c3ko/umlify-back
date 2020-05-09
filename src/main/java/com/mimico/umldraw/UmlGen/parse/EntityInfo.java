package com.mimico.umldraw.UmlGen.parse;

import java.util.List;

public class EntityInfo<T> {

    String name, type;
    List<T> children;

    public EntityInfo(String name, String type, List<T> children) {
        this.name = name;
        this.type = type;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
