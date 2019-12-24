package storage;

import ru.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> storage = new HashMap<>();


    @Override
    protected String getContext(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        } else {
            return "not exist";
        }
    }

    @Override
    protected boolean exist(String ctx) {
        return !ctx.equals("not exist");
    }

    @Override
    public void doSave(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void doUpdate(String uuid, Resume resume) {
        storage.put(uuid, resume);
    }

    @Override
    public Resume doLoad( String uuid) {
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

    @Override
    public boolean isSectionSupported() {
        return true;
    }
}
