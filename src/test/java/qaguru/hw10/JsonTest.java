package qaguru.hw10;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qaguru.hw10.models.ProductProviders;

import java.io.IOException;
import java.io.InputStream;


public class JsonTest {

    ClassLoader cl = JsonTest.class.getClassLoader();

    @Test
    public void testJsonParsing() throws JsonParseException, IOException {

        InputStream stream = cl.getResourceAsStream("json_sample.json");

        ObjectMapper objectMapper = new ObjectMapper();

        ProductProviders productProviders = objectMapper.readValue(stream, ProductProviders.class);

        Assertions.assertEquals("Product", productProviders.getProductId());
        Assertions.assertEquals(2, productProviders.getProviders().size());
        Assertions.assertEquals("Supplier A", productProviders.getProviders().get(0).getName());
        Assertions.assertEquals("John Smith", productProviders.getProviders().get(0).getContact());
        Assertions.assertEquals("johnsmith@suppliera.com", productProviders.getProviders().get(0).getEmail());
        Assertions.assertEquals("Supplier B", productProviders.getProviders().get(1).getName());
        Assertions.assertEquals("Jane Doe", productProviders.getProviders().get(1).getContact());
        Assertions.assertEquals("janedoe@supplierb.com", productProviders.getProviders().get(1).getEmail());
    }
}
