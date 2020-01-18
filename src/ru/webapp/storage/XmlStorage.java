package ru.webapp.storage;

import ru.webapp.model.*;
import ru.webapp.util.XmlParser;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStorage extends FileStorage {
    private XmlParser parser;

    public XmlStorage(String path) {
        super(path);
        parser = new XmlParser(Resume.class, TextSection.class,Section.class, TextSectionWithTitle.class,
                 OrganizationSection.class,SectionType.class, Organization.class, Organization.Period.class, Link.class, SectionType.class);
    }



    @Override
    protected void write(ObjectOutputStream os, Resume resume) throws IOException {
        try(Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            parser.marshalling(w, resume);
        }
    }

    @Override
    protected Resume read(ObjectInputStream os) throws IOException {
        try(Reader r = new InputStreamReader(os)) {
            return parser.unmarshalling(r);
        }
    }

    @Override
    public boolean isSectionSupported() {
        return true;
    }
}
