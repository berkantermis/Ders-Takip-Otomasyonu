package org.example.javafxdeneme2.Courses;

public class Course extends ParentCourse {
    private String instructor;
    private String duration;
    private String imageUrl;
    private String description;
    private String language;

    public Course(String name, String instructor, String duration, String imageUrl,String description,String language) {
        super(name);
        this.instructor = instructor;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.description=description;
        this.language=language;
    }


    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

