package ru.webapp.storage;

import ru.webapp.WebAppException;
import ru.webapp.model.Resume;

import java.util.Collection;

/**
 * Капу пк
 * 18.01.2020
 */
public class SqlStorage implements IStorage {

    @Override
    public void save(Resume resume) {
        
    }

    @Override
    public void update(Resume resume) throws WebAppException {

    }

    @Override
    public Resume load(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) throws WebAppException {

    }

    @Override
    public void clear() {

    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
