package com.example.oldiebutgoldie;

public class Person {
    private int id;
    private String firstName;
    private String age;
    private String description;
    private String picture;

    public Person(int id, String firstName, String age, String description, String picture) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
        this.description = description;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }
}
