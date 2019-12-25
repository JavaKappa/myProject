package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.Collection;

/**
 * Капу пк
 * 06.12.2019
 */
public interface IStorage {
    void save(Resume resume);

    void update(Resume resume) throws WebAppException;

    Resume load(String uuid);

    void delete(String uuid) throws WebAppException;

    void clear();

    Collection<Resume> getAllSorted();

    int size();

    boolean isSectionSupported();
}
