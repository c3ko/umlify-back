package com.mimico.umldraw.UmlGen;

import com.mimico.umldraw.UmlGen.parse.ClassInfo;
import com.mimico.umldraw.UmlGen.parse.ClassSrc;
import com.mimico.umldraw.UmlGen.parse.Parser;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.DiagramDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class UMLGenService {


    @Autowired
    Parser parser;

    @Autowired
    PlantUMLStringBuilder plantUMLStringBuilder;

    public List<ClassInfo> parseOneClass(String src){

        List<ClassInfo> parsedClass = parser.parseClass(src);

        return parsedClass;
    }

    public String drawUMLSVG(List<List<ClassInfo>> classInfoList) throws IOException {

        String currentPlantUMLString = plantUMLStringBuilder.buildPlantUMLString(classInfoList);
        System.out.println(currentPlantUMLString);
        SourceStringReader reader = new SourceStringReader(currentPlantUMLString);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DiagramDescription desc = reader.outputImage(outputStream, new FileFormatOption(FileFormat.SVG));
        outputStream.close();

        final String svg = new String(outputStream.toByteArray(), Charset.forName("UTF-8"));
        return svg;
    }

    public byte[] drawUMLPNG(List<List<ClassInfo>> classInfoList) throws IOException{
        String currentPlantUMLString = plantUMLStringBuilder.buildPlantUMLString(classInfoList);

        SourceStringReader reader = new SourceStringReader(currentPlantUMLString);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DiagramDescription desc = reader.outputImage(outputStream, new FileFormatOption(FileFormat.PNG));
        outputStream.close();
        return outputStream.toByteArray();

    }

}
