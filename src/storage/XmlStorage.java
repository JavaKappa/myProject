package storage;

import ru.webapp.model.Resume;
import util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStorage extends FileStorage {

    public XmlStorage(String path) {
        super(path);
    }

    @Override
    protected void write(ObjectOutputStream os, Resume resume) throws IOException {
        Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        XmlParser.write(w, resume);
    }

    @Override
    protected Resume read(ObjectInputStream os) throws IOException {
        Reader r = new InputStreamReader(os);
        return XmlParser.reader(r, Resume.class);
    }
}
