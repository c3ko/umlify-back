package com.mimico.umldraw.UmlGen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runners.Parameterized;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Disabled
public class PlantUMLStringTest {
    String[] modifierArray0 = {"public", "int"};
    String[] modifierArray1 = {"static", "void", "public"};
    String[] modifierArray2 = {"", "private", "String"};
    String[] modifierArray3 = {"protected", "volatile"};
    String[] modifierArray4 = {"static", "", ""};

    public List<String> m0 = Arrays.asList(modifierArray0);
    public List<String> m1 = Arrays.asList(modifierArray1);
    public List<String> m2 = Arrays.asList(modifierArray2);
    public List<String> m3 = Arrays.asList(modifierArray3);

    @Test
    void testAccessModifierStrings(){
        assertEquals(PlantUMLStringBuilder.getAccessModifierString(m0), "+");
        assertEquals(PlantUMLStringBuilder.getAccessModifierString(m1), "+");
        assertEquals(PlantUMLStringBuilder.getAccessModifierString(m2), "-");
        assertEquals(PlantUMLStringBuilder.getAccessModifierString(m3), "#");

    }

    @Test
    void testStaticModifierStrings(){

    }

}
