package qaguru.reqres.models;

import lombok.Data;

import java.util.List;

@Data
public class UsersMassiveModel {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Data> data;
    private Support support;

    @lombok.Data
    public static class Data {
        private int id, year;
        private String name, color, pantone_value;
    }

    @lombok.Data
    public static class Support {
        private String url, text;
    }
}
