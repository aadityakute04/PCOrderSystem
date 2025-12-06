package Model;

import computer.store.model.PresetModelImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PresetModelImplTest {

    @Test
    void testValidConstructionAndAccessors() {
        List<String> parts = List.of("CPU", "RAM", "SSD");
        PresetModelImpl model = new PresetModelImpl("ph", "deskelits", parts);

        assertEquals("ph", model.getManufacturer());
        assertEquals("deskelits", model.getName());
        assertEquals(parts, model.getParts());
    }

    @Test
    void testImmutabilityOfPartsList() {
        List<String> parts = new ArrayList<>(List.of("CPU", "RAM"));
        PresetModelImpl model = new PresetModelImpl("elld", "plexiplex", parts);

        parts.add("GPU");
        assertEquals(List.of("CPU", "RAM"), model.getParts());
        assertThrows(UnsupportedOperationException.class, () -> model.getParts().add("SSD"));
    }

    @Test
    void testInvalidArgumentsThrowException() {
        List<String> parts = List.of("CPU");

        assertThrows(IllegalArgumentException.class, () -> new PresetModelImpl(null, "Model", parts));
        assertThrows(IllegalArgumentException.class, () -> new PresetModelImpl("", "Model", parts));
        assertThrows(IllegalArgumentException.class, () -> new PresetModelImpl("ph", null, parts));
        assertThrows(IllegalArgumentException.class, () -> new PresetModelImpl("ph", "", parts));
        assertThrows(IllegalArgumentException.class, () -> new PresetModelImpl("ph", "deskelits", null));
    }

    @Test
    void testEqualsAndHashCode() {
        PresetModelImpl m1 = new PresetModelImpl("ph", "deskelits", List.of("CPU"));
        PresetModelImpl m2 = new PresetModelImpl("ph", "deskelits", List.of("CPU", "RAM"));
        PresetModelImpl m3 = new PresetModelImpl("elld", "plexiplex", List.of("CPU"));

        assertEquals(m1, m2); // same manufacturer + name
        assertEquals(m1.hashCode(), m2.hashCode());
        assertNotEquals(m1, m3);
    }

    @Test
    void testToStringFormat() {
        PresetModelImpl model = new PresetModelImpl("ph", "booksz", List.of("SSD"));
        String result = model.toString();
        assertTrue(result.contains("ph"));
        assertTrue(result.contains("booksz"));
        assertTrue(result.startsWith("PresetModel"));
    }
}
