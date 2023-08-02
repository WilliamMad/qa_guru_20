package qaguru.reqres.models;

import lombok.Data;

@Data
public class UserInfoModel {
    private Data data;
    private Support support;

    @lombok.Data
    public static class Data {
        private int id;
        private String email, first_name, last_name, avatar;
    }

    @lombok.Data
    public static class Support {
        private String url, text;
    }
}
