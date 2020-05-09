package com.mimico.umldraw.UmlGen.parse;

import java.util.List;

public class EnumInfo {


    String name;
    List<String> modifiers;
    List<String> values;
    public EnumInfo(String name, List<String> modifiers, List<String> values){
        this.name = name;
        this.modifiers = modifiers;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }



}
