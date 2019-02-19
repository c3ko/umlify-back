package com.mimico.umldraw.UmlGen.parse;

public class ParameterInfo {
    private String paramName, modifiers[], varType;

    ParameterInfo(String paramName, String varType){
        this.paramName = paramName;
        this.varType = varType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String[] getModifiers() {
        return modifiers;
    }

    public void setModifiers(String[] modifiers) {
        this.modifiers = modifiers;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    @Override
    public String toString() {
        return "{memberName: " + this.paramName +", varType: " + this.varType + "}";
    }
}
