package RD;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class JsonComparison {








    public boolean compareJsonIgnoreFields1(String json1, String json2,
                                           String... ignoreFields) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node1 = mapper.readTree(json1);
        JsonNode node2 = mapper.readTree(json2);

        // Remove ignored fields from both JSON objects
        for (String field : ignoreFields) {
            ((com.fasterxml.jackson.databind.node.ObjectNode) node1).remove(field);
            ((com.fasterxml.jackson.databind.node.ObjectNode) node2).remove(field);
        }

        return node1.equals(node2);
    }

    // Usage
    @Test
    void simpleComparison() throws Exception {
        String json1 = "{\"id\":1,\"name\":\"John\",\"timestamp\":123}";
        String json2 = "{\"id\":2,\"name\":\"John\",\"timestamp\":456}";

        assertTrue(compareJsonIgnoreFields1(json1, json2, "id", "timestamp"));
    }


    @Test
    void CompareJSON_AvoidingLeftRightKeyOrphans_AlsoIgnoringCustomFields()  {

        String json1 = "{\"id\": 1, \"name\": \"Alice1\", \"timestamp\": \"2023-10-26T15:30:00Z\"}";
        String json2 = "{\"id\": 1, \"name\": \"Alice\", \"timestamp\": \"2023-10-26T15:30:00Z\", \"city\": \"London\"}";
        Customization ignoreName = new Customization("name", (o1, o2) -> true);
        JSONAssert.assertEquals(json1, json2, new CustomComparator(JSONCompareMode.LENIENT, ignoreName));

    }

    @Test
    void ToCheckIfJsonKeyContains_PartofValue()  {
        String json2 = "{\"id\": 1, \"name\": \"Alice John\", \"timestamp\": \"2023-10-26T15:30:00Z\", \"city\": \"London\"}";
       // Customization customization = new Customization("name", (actual, expected) -> actual.toString().contains(expected.toString()));
       // JSONAssert.assertEquals("{\"name\":\"Al\"}", json2, new CustomComparator(JSONCompareMode.LENIENT, customization));
        System.out.println(json2);

        JSONObject obj = new JSONObject(json2);

        assertTrue(obj.optString("name").contains("ohn"),"They dont match");
        assertTrue(obj.optString("lala").isEmpty(),"They dont match");

    }


}