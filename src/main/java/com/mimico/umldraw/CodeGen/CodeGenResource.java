package com.mimico.umldraw.CodeGen;


import com.mimico.umldraw.UmlGen.UML;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class CodeGenResource {

    @PostMapping("/code")
    public ResponseEntity<Code> generateCode(@RequestBody UML uml){

        return ResponseEntity.ok().build();
    }

}
