package storage;

import ru.webapp.model.Resume;

import java.io.*;
import java.util.*;


/**
 * Капу пк
 * 18.12.2019
 */
public class SerializeFileStorage extends FileStorage {

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
