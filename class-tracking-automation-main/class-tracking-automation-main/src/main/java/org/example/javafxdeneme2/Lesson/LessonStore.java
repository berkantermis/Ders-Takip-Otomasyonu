package org.example.javafxdeneme2.Lesson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.javafxdeneme2.CourseDetailController;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class LessonStore {

    public static List<Section> loadSections(String filePath) throws IOException {
        Gson gson = new Gson();
        Type sectionListType = new TypeToken<List<Section>>() {}.getType();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, sectionListType);
        }

    }

    public static void saveSections(String filePath, List<Section> sections) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(sections);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        }
    }
}
