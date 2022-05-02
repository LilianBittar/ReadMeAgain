package com.lilianbittar.readmeagain.model;

public class Profile {
    private String FirstName;
    private String LastName;
    private int age;
    private String Profession;
    private String Location;

    public Profile(String firstName, String lastName, int age, String profession, String location) {
        FirstName = firstName;
        LastName = lastName;
        this.age = age;
        Profession = profession;
        Location = location;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
