package com.mimico.umldraw.UmlGen;

import com.mimico.umldraw.UmlGen.parse.ClassInfo;
import com.mimico.umldraw.UmlGen.parse.ClassSrc;
import com.mimico.umldraw.UmlGen.parse.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UMLGenService {


    @Autowired
    Parser parser;


    public ClassInfo parseOneClass(String src){
        List<ClassInfo> parsedClass = parser.parseClass(src);

        System.out.println(parsedClass.size());

        return parsedClass.get(0);
    }

    public List<ClassInfo> parseMultipleClasses(List<ClassSrc> srcList){

        List<ClassInfo> parsedClasses = new ArrayList<>();
        for(ClassSrc src: srcList){
            parsedClasses.add(parser.parseClass(src.getSrc()).get(0));
        }

        return parsedClasses;
    }

}
