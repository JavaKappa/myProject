package storage;

import ru.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void doSave(Resume resume) {
        if (storage.containsKey(resume.getUuid())) {
            doException("Resume already in use");
        }
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void doUpdate(Resume resume) {
        if (!storage.containsKey(resume.getUuid())) {
            doException("Resume does not exists");
        }
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doLoad(String uuid) {
        if (!storage.containsKey(uuid)) {
            doException("Resume does not exists");
        }
        return storage.get(uuid);
    }

    @Override
    public void doDelete(String uuid) {
        if (!storage.containsKey(uuid)) {
            doException("Resume does not exists");
        }
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
