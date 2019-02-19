package com.mimico.umldraw.UmlGen.parse;

import java.util.List;

public class ClassInfo {
    private  String className;
    private  List<String> modifiers;
    private  List<MethodInfo> constructors;
    private  List<MethodInfo> methods;
    private  List<MemberInfo> members;




    ClassInfo(String className, List<String> modifiers, List<MethodInfo> constructors, List<MethodInfo> methods, List<MemberInfo> members) {
        this.className = className;
        this.modifiers = modifiers;
        this.constructors = constructors;
        this.methods = methods;
        this.members = members;
    }

    public String getClassName() {
        return className;
    }

    public List<String> getModifiers(){ return modifiers; }

    public List<MethodInfo> getConstructors(){ return constructors; }

    public List<MethodInfo> getMethods(){ return methods; }

    public List<MemberInfo> getMembers(){ return members; }


    public void setClassName(String className) { this.className = className; }

    public void setModifiers(List<String> modifiers){ this.modifiers = modifiers; }

    public void setConstructors(List<MethodInfo> constructors){ this.constructors = constructors; }

    public void setMethods(List<MethodInfo> methods){ this.methods = methods; }

    public void setMembers(List<MemberInfo> members){ this.members = members; }

}