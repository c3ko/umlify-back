package com.mimico.umldraw.UmlGen.parse;

import java.util.ArrayList;
import java.util.List;

public class MethodInfo {
    private final String methodName,  returnType;
    private final List<String> modifiers;
    private final List<ParameterInfo> params;

    MethodInfo(){
        this.methodName = "";
        this.returnType = "";
        this.modifiers = new ArrayList<String>();
        this.params = new ArrayList<ParameterInfo>();
    }


    MethodInfo(String methodName, List<String> modifiers, String returnType, List<ParameterInfo> params){
        this.methodName = methodName;
        this.modifiers = modifiers;
        this.returnType = returnType;
        this.params = params;

    }

    public String getMethodName(){ return methodName; }
    public List<String> getModifiers(){ return modifiers; }
    public String getReturnType(){ return returnType; }
    public List<ParameterInfo> getParams(){ return params; }



}