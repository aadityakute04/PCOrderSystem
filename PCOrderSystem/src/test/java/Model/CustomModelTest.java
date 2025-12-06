package Model;

import computer.store.model.CustomModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class CustomModelTest {

    private CustomModel model;

    @BeforeEach
    void setUp() {
        model = new CustomModel("custom1", List.of("CPU", "RAM"));
    }

    @Test
    void testConstructorValidInput() {
        CustomModel custmodel = new CustomModel("MyComputer", List.of("SSD", "GPU"));
        assertEquals("MyComputer", custmodel.getName());
        assertEquals(List.of("SSD", "GPU"), custmodel.getParts());
    }

    @Test
    void testConstructorRejectsNullName() {
        assertThrows(IllegalArgumentException.class, () ->
                new CustomModel(null, List.of("CPU")));
    }

    @Test
    void testConstructorRejectsBlankName() {
        assertThrows(IllegalArgumentException.class, () ->
                new CustomModel("  ", List.of("CPU")));
    }

    @Test
    void testConstructorRejectsNullPartsList() {
        assertThrows(IllegalArgumentException.class, () ->
                new CustomModel("MyComputer", null));
    }


    @Test
    void testAddPartValid() {
        model.addPart("SSD");
        assertTrue(model.getParts().contains("SSD"));
    }

    @Test
    void testAddPartRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> model.addPart(null));
    }

    @Test
    void testAddPartRejectsBlank() {
        assertThrows(IllegalArgumentException.class, () -> model.addPart(" "));
    }


    @Test
    void testRemoveExistingPart() {
        boolean removed = model.removePart("CPU");
        assertTrue(removed);
        assertFalse(model.getParts().contains("CPU"));
    }

    @Test
    void testRemoveNonExistingPart() {
        boolean removed = model.removePart("SSD");
        assertFalse(removed);
    }


    @Test
    void testGetPartsReturnsUnmodifiableList() {
        List<String> parts = model.getParts();
        assertThrows(UnsupportedOperationException.class, () -> parts.add("GPU"));
    }


    @Test
    void testEqualsSameName() {
        CustomModel custmodel1 = new CustomModel("customA", List.of("Aa"));
        CustomModel custmodel2 = new CustomModel("customA", List.of("Bb"));
        assertEquals(custmodel1, custmodel2);
        assertEquals(custmodel1.hashCode(), custmodel2.hashCode());
    }

    @Test
    void testNotEqualsDifferentName() {
        CustomModel custmodel1 = new CustomModel("model1", List.of("Aa"));
        CustomModel custmodel2 = new CustomModel("model2", List.of("Aa"));
        assertNotEquals(custmodel1, custmodel2);
    }

    @Test
    void testEqualsSelf() {
        assertEquals(model, model);
    }

    @Test
    void testEqualsDifferentObjectType() {
        assertNotEquals("String", model);
    }


    @Test
    void testToStringContainsNameAndParts() {
        String result = model.toString();
        assertTrue(result.contains("custom1"));
        assertTrue(result.contains("CPU"));
        assertTrue(result.contains("RAM"));
    }
}
