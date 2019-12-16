package storage;

import ru.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();


    @Override
    protected int getIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return 1;
        } else {
            return -1;
        }
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
    public List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
