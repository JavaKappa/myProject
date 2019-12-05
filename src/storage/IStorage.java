package storage;

import ru.webapp.model.Resume;

import java.util.Collection;

/**
 * Капу пк
 * 06.12.2019
 */
public interface IStorage {
    void save(Resume resume);

    void update(Resume resume);

    Resume load(String uuid);

    void delete(String uuid);

    void clear();

    Collection<Resume> getAllSorted();

    int size();
}
