package org.example.javafxdeneme2.Courses;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CourseStore {
    private static final String FILE_PATH = "src/main/data/all_courses_window_data.json";
    private static List<Course> courses;

    static {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            Type courseListType = new TypeToken<List<Course>>() {}.getType();
            courses = gson.fromJson(reader, courseListType);
        } catch (IOException e) {
            e.printStackTrace();
            courses = Collections.emptyList();
        }
    }

    public static List<Course> getCourses() {
        return courses;
    }
}
