package org.example.javafxdeneme2.Lesson;

public class Lesson {
    private String lessonName;
    private String videoUrl;
    private Boolean completed;

    // Constructor, getters ve setters
    public Lesson(String lessonName, String videoUrl, Boolean completed) {
        this.lessonName = lessonName;
        this.videoUrl = videoUrl;
        this.completed=completed;
    }

    public String getlessonName() {
        return lessonName;
    }

    public void setlessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
