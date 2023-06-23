package com.demoqa.tests.HW10;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class JsonTest {

    ClassLoader cl = JsonTest.class.getClassLoader();

    public static class ProductProviders {
        private String productId;
        private List<Provider> providers;

        public String getProductId() {
            return productId;
        }
        public List<Provider> getProviders() {
            return providers;
        }
    }


    public static class Provider {
        private String name;
        private String contact;
        private String email;

        public String getName() {
            return name;
        }
        public String getContact() {
            return contact;
        }
        public String getEmail() {
            return email;
        }
    }


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
