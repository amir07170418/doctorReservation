package org.example.doctorreservation.dto;


public class SpecialtyRequest {
    private String name;
    private String description;

    public SpecialtyRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public SpecialtyRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SpecialtyRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
