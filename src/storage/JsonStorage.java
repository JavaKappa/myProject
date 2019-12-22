package storage;

import ru.webapp.model.Resume;
import util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStorage extends FileStorage {

    public JsonStorage(String path) {
        super(path);
    }

    public JsonStorage() {
    }

    @Override
    protected void write(ObjectOutputStream os, Resume resume) throws IOException{
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)){
            JsonParser.write(resume, w);
        }
    }

    @Override
    protected Resume read(ObjectInputStream is) {
        return JsonParser.read(new InputStreamReader(is, StandardCharsets.UTF_8), Resume.class);
    }
}
