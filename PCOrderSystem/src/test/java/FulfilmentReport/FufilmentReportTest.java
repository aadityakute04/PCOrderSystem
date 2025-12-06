package FulfilmentReport;

import computer.store.Report.FulfilmentSummary;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FulfilmentSummaryTest {

    @Test
    void testInternalMutationDoesNotAffectReport() {
        // test data hp models
        Map<String, Integer> phModelCounts = new HashMap<>();
        phModelCounts.put("deskelits", 10);

        // creating manufacturer order map
        Map<String, Map<String, Integer>> manufacturerData = new HashMap<>();
        manufacturerData.put("ph", phModelCounts);

        // Setting up parts data
        Map<String, Integer> partsData = new HashMap<>();
        partsData.put("RAM", 5);

        // report creation
        FulfilmentSummary report = new FulfilmentSummary(manufacturerData, partsData);

        // mutating the original maps
        phModelCounts.put("booksz", 5);
        partsData.put("GPU", 2);

        // verifying that the changes doesnt affect the report
        Map<String, Integer> reportphModels = report.getManufacturerOrders().get("ph");
        assertFalse(reportphModels.containsKey("booksz"), "report should not contain booksz after mutation");

        Map<String, Integer> reportParts = report.getPartsToCollect();
        assertFalse(reportParts.containsKey("GPU"), "report should not contain GPU after mutation");
    }

    @Test
    void testEmptyMapsAllowed() {
        // Test whether we can create a report with empty data
        Map<String, Map<String, Integer>> emptyManufacturers = Map.of();
        Map<String, Integer> emptyParts = Map.of();

        FulfilmentSummary report = new FulfilmentSummary(emptyManufacturers, emptyParts);

        // Both should be empty
        assertTrue(report.getManufacturerOrders().isEmpty(), "manufacturer order should be empty");
        assertTrue(report.getPartsToCollect().isEmpty(), "parts to collect should be empty");
    }
}