package com.mimico.umldraw.UmlGen.parse;

import javax.annotation.Nullable;
import java.util.List;

public class ClassInfo {
    private  String className;
    private  List<String> modifiers;
    private  List<String> implementedTypes;
    private  List<String> extendedTypes;
    private  List<MethodInfo> constructors;
    private  List<MethodInfo> methods;
    private  List<MemberInfo> members;

    /**
     *
     * @return list of types implemented by the class
     */

    public List<String> getImplementedTypes() {
        return implementedTypes;
    }

    /**
     *
     * @param implementedTypes set the types implemented by the class
     */
    public void setImplementedTypes(List<String> implementedTypes) {
        this.implementedTypes = implementedTypes;
    }

    public List<String> getExtendedTypes() {
        return extendedTypes;
    }

    public void setExtendedTypes(List<String> extendedTypes) {
        this.extendedTypes = extendedTypes;
    }

    ClassInfo(String className, List<String> modifiers, List<String> implementedTypes, List<String> extendedTypes, List<MethodInfo> constructors, List<MethodInfo> methods, List<MemberInfo> members) {
        this.className = className;
        this.modifiers = modifiers;
        this.implementedTypes = implementedTypes;
        this.extendedTypes = extendedTypes;
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