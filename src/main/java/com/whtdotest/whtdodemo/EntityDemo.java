package com.whtdotest.whtdodemo;

import com.whtdotest.whtdodemo.entity.User;

public class EntityDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("John");
        user.setSurname("Wick");

        User goodUser = new User() {{
                setFirstName("John");
                setSurname("Connor");
        }};
    }

}
