package com.mimico.umldraw.UmlGen;


import com.mimico.umldraw.UmlGen.parse.ClassInfo;
import com.mimico.umldraw.UmlGen.parse.MemberInfo;
import com.mimico.umldraw.UmlGen.parse.MethodInfo;
import com.mimico.umldraw.UmlGen.parse.ParameterInfo;
import org.springframework.stereotype.Component;

import java.lang.String;

import java.util.List;
import java.util.Map;

/*
    TODO: Convert parsed Class Information in to PlantUML syntax for class diagram
 */

@Component
public class PlantUMLStringBuilder {

    public static final String START_UML = "@startuml";
    public static final String END_UML = "@enduml";
    public static final String CLASS_KEYWORD = "class";
    public static final String ABSTRACT_CLASS_KEYWORD = "abstract class";
    public static final String INTERFACE_KEYWORD = "interface";
    public static final String ENUM_KEYWORD = "enum";
    public static final String START_BLOCK = "{";
    public static final String END_BLOCK = "}";
    public static final String ABSTRACT = "{abstract}";
    public static final String STATIC = "{static}";
    public static final String NEW_LINE = "\n";

    // Default: Inheritance (Extension)
    public static final String UP_ARROW_OPEN = "<|--";
    public static final String DOWN_ARROW_OPEN = "--|>";

    //Default: Implementation (Implements)
    public static final String UP_ARROW_OPEN_DASHED = "<|..";
    public static final String DOWN_ARROW_OPEN_DASHED = "..|>";

    //Default Composition/Association
    public static final String LEFT_DIAMOND_FILLED = "*-";
    public static final String RIGHT_DIAMOND_FILLED = "-*";

    public final String DISABLE_VISIBILITY_ICON = "skinparam classAttributeIconSize 0";
    public final String PRIVATE_CHAR = "-";
    public final String PROTECTED_CHAR = "-";
    public final String PACKAGE_PRIVATE_CHAR = "-";
    public final String PUBLIC_CHAR = "-";



    public PlantUMLStringBuilder(List<String> srcList){

    }

    public static String buildPlantUMLString(List<List<ClassInfo>> classFiles){

        StringBuilder classString = new StringBuilder();

        classString.append(START_UML + NEW_LINE);


        for (List<ClassInfo> c : classFiles){
            for (ClassInfo ci : c){
                classString.append(buildClass(ci) + NEW_LINE);
                classString.append(buildRelations(ci));
            }
        }

        classString.append(END_UML);

        return classString.toString();
    };
    public static String buildRelations(ClassInfo classToBuild){
        StringBuilder extendedString = new StringBuilder();
        StringBuilder implementedString = new StringBuilder();

        for (String extendedClassName: classToBuild.getExtendedTypes()){
            extendedString.append(extendedClassName + " " + UP_ARROW_OPEN + " " + classToBuild.getClassName());
        }
        for (String implementedClassName: classToBuild.getImplementedTypes()){
            implementedString.append(implementedClassName + " " + UP_ARROW_OPEN_DASHED + " " + classToBuild.getClassName() + NEW_LINE);
        }
        return extendedString.toString() + "\n" + implementedString.toString();
    }

    public static String buildClass(ClassInfo classToBuild){
        StringBuilder classString = new StringBuilder();

        String entityKeyword = CLASS_KEYWORD;

        if (classToBuild.getModifiers().contains("ABSTRACT")){
            entityKeyword = ABSTRACT_CLASS_KEYWORD;
        } else if (classToBuild.getModifiers().contains("interface")) {
            entityKeyword = INTERFACE_KEYWORD;
        } else if (classToBuild.getModifiers().contains("enum")) {
            entityKeyword = ENUM_KEYWORD;
        }

        classString.append(entityKeyword + " " + classToBuild.getClassName() + " " + START_BLOCK + NEW_LINE);

        for (MemberInfo member: classToBuild.getMembers()){
            String currentMemberString = buildMember(member);
            classString.append(currentMemberString + NEW_LINE);
        }

        for (MethodInfo method: classToBuild.getConstructors()){
            String currentMethodString = buildMethod(method, false);
            classString.append(currentMethodString + NEW_LINE);
        }

        for (MethodInfo con: classToBuild.getMethods()) {
            String currentConstructorString = buildMethod(con, true);
            classString.append(currentConstructorString + NEW_LINE);
        }

        //getClassStringBuilders().put(classToBuild.getClassName(), )
        classString.append(END_BLOCK);
        return classString.toString();
    }

    /*
       TODO: Methods with many parameters should be spread out over several lines
     */
    public static String buildMethod(MethodInfo m, boolean isConstructor){
        StringBuilder methodString = new StringBuilder();
        if (!isConstructor){
            if (isAbstract(m.getModifiers())){
                methodString.append(ABSTRACT + " ");
            }
            if (isStatic(m.getModifiers())){
                methodString.append(STATIC + " ");
            }
            methodString.append(getAccessModifierString(m.getModifiers()) + " " + m.getReturnType() + " " + m.getMethodName() + "(");
        }

        else {
            methodString.append(m.getMethodName() + "(");
        }
        for (int i = 0; i < m.getParams().size(); i++){
            ParameterInfo param  = m.getParams().get(i);
            String paramEnding = i < m.getParams().size() - 1 ? ", ": "";
            methodString.append(param.getVarType() + " " + param.getParamName() + paramEnding);
        }
        methodString.append(")");

        return methodString.toString();
    }

    public static String buildMember(MemberInfo member){
        StringBuilder memberString = new StringBuilder();
        if (isAbstract(member.getModifiers())){
            memberString.append(ABSTRACT + " ");
        }
        if (isStatic(member.getModifiers())){
            memberString.append(STATIC + " ");
        }
        memberString.append(getAccessModifierString(member.getModifiers()) + " " + member.getVarType() + " " + member.getMemberName());
        return memberString.toString();
    };


    public static String getAccessModifierString(List<String> modifiers){

        if (modifiers.contains("public")){
            return "+";
        } else if (modifiers.contains("private")){
            return "-";
        } else if (modifiers.contains("protected")){
            return "#";
        } else {
            return "~";
        }
    }


    public static boolean isAbstract(List<String> modifiers){

        return modifiers.contains("abstract");

    }

    public static boolean isStatic(List<String> modifiers){
        return modifiers.contains("static");
    }

    public static void main(String args[]){


    }
}
