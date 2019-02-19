package com.mimico.umldraw.UmlGen.parse;

import java.util.List;

public class MemberInfo {
    private String memberName, varType;
    private List<String> modifiers;

    MemberInfo(String memberName, List<String> modifiers, String varType){
        this.memberName = memberName;
        this.modifiers = modifiers;
        this.varType = varType;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }


}
