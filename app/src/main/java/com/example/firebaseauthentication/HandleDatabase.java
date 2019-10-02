package com.example.firebaseauthentication;

public class HandleDatabase {

    private String name;
    private String age;

    public HandleDatabase() {
    }

    public HandleDatabase(String name, String age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
