package storage;

import ru.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public boolean exist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    public void doSave(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void doUpdate(Resume resume) {

        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doLoad(String uuid) {

        return storage.get(uuid);
    }

    @Override
    public void doDelete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    public void doClear() {
        storage = new HashMap<>();
    }

    @Override
    public Collection<Resume> doGetAllSorted() {
        return new TreeSet<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
