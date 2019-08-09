package com.mimico.umldraw.UmlGen.parse;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class Parser {


    /**
     *
     * @param src java source code
     * @return
     */
    public List<ClassInfo> parseClass(String src){
        List<ClassInfo> classCollector = new ArrayList<>();
        CompilationUnit cu = JavaParser.parse(src);
        VoidVisitor<List<ClassInfo>> classV = new ClassPrinter();
        classV.visit(cu, classCollector);

        return classCollector;
    }

    /**
     * Static class used to visit contructor, method and member nodes in AST tree of Java source code
     */
    private static class ClassPrinter extends VoidVisitorAdapter<List<ClassInfo>> {
        @Override
        public void visit(ClassOrInterfaceDeclaration cd, List<ClassInfo> collector){
            super.visit(cd, collector);
            List<MethodInfo> methodCollector = new ArrayList<MethodInfo>();
            List<MemberInfo> memberCollector = new ArrayList<MemberInfo>();
            List<MethodInfo> constructorCollector = new ArrayList<MethodInfo>();
            List<String> implementedClasses = new ArrayList<>();
            List<String> extendedClasses = new ArrayList<>();

            for(ClassOrInterfaceType it: cd.getImplementedTypes()){
                implementedClasses.add(it.asString());
                System.out.println("Implemented Classes: " + it.asString());
            }

            for(ClassOrInterfaceType et: cd.getExtendedTypes()){
                extendedClasses.add(et.asString());
                System.out.println("Extended Classes: " + et.asString());

            }

            //handle members/fields
            for (FieldDeclaration fd: cd.getFields()){
                List<String> mods = new ArrayList<String>();

                for (Enum m: fd.getModifiers()) {
                    mods.add(m.name());

                }
                for (VariableDeclarator v: fd.getVariables()){
                    memberCollector.add(new MemberInfo(v.getNameAsString(), mods, v.getTypeAsString()));
                }
            }

            //handle methods
            for (MethodDeclaration md: cd.getMethods()){
                List<ParameterInfo> params = new ArrayList<ParameterInfo>();
                List<String> mods = new ArrayList<String>();

                for (Parameter p: md.getParameters() ) {
                    params.add(new ParameterInfo(p.getNameAsString(), p.getTypeAsString()));
                }
                for (Enum m: md.getModifiers()) {
                    mods.add(m.name());
                }
                methodCollector.add(new MethodInfo(md.getNameAsString(), mods, md.getTypeAsString(), params));
            }


            //handle constructors
            for (ConstructorDeclaration c: cd.getConstructors()){
                List<String> mods = new ArrayList<String>();

                List<ParameterInfo> params = new ArrayList<ParameterInfo>();

                for (Enum m: c.getModifiers()){
                    mods.add(m.name());
                }

                for (Parameter p: c.getParameters() ) {
                    params.add(new ParameterInfo(p.getNameAsString(), p.getTypeAsString()));
                }

                constructorCollector.add(new MethodInfo(c.getNameAsString(),mods, "void", params));
            }

            //Get modifiers for class
            List<String> mods = new ArrayList<String>();
            for (Enum m: cd.getModifiers()){
                mods.add(m.name());
            }

            collector.add(new ClassInfo(cd.getNameAsString(),  mods, implementedClasses, extendedClasses, constructorCollector, methodCollector, memberCollector));

        }
    }

}
