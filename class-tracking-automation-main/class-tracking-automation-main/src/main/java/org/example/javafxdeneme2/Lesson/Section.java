package org.example.javafxdeneme2.Lesson;

import java.util.List;

public class Section {
    private String sectionName;
    private List<Lesson> lessons;

    // Constructor, getters ve setters
    public Section(String sectionName, List<Lesson> lessons) {
        this.sectionName = sectionName;
        this.lessons = lessons;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
