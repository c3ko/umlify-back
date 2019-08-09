package com.mimico.umldraw.UmlGen;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.mimico.umldraw.UmlGen.parse.ClassInfo;
import com.mimico.umldraw.UmlGen.UMLGenService;

import com.mimico.umldraw.UmlGen.parse.ClassSrc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/uml")
public class UmlGenController {

    @Autowired
    private UMLGenService service;

    @CrossOrigin(origins = "*")
    @PostMapping("/parse")
    public ResponseEntity<ClassInfo> retrieveUML(@RequestBody String src){

        System.out.println(src);
        ClassInfo parsedClass = service.parseOneClass(src);

        return ResponseEntity.ok(parsedClass);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value="/parsemultiple")
    public ResponseEntity<List<ClassInfo>> retrieveMultipleUML(@RequestBody List<String> srcList){

        System.out.println(srcList);
        List<ClassInfo> parsedClasses = new ArrayList<>();

            for(String s: srcList){
                try {
                    String formattedSource = new Formatter().formatSource(s);
                    parsedClasses.add(service.parseOneClass(formattedSource));

                } catch (FormatterException f){
                    f.printStackTrace();
                }
            }
            /*
            List<ClassInfo> parsedClasses = srcList.stream().map(s -> service.parseOneClass(new Formatter().formatSource(s))).collect(Collectors.toList());
            */
            return ResponseEntity.ok(parsedClasses);


    }


}
