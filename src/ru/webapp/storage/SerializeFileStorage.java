package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.io.*;


/**
 * Капу пк
 * 18.12.2019
 */
public class SerializeFileStorage extends FileStorage {

    public SerializeFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(ObjectOutputStream os, Resume resume) throws IOException{
            os.writeObject(resume);
    }

    @Override
    protected Resume read(ObjectInputStream os) throws IOException{
        try {
            return (Resume) os.readObject();
        } catch (ClassNotFoundException e) {
            throw new WebAppException("Can't read resume", e);
        }
    }

    @Override
    public boolean isSectionSupported() {
        return true;
    }
}
