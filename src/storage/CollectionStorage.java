package storage;

import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;


public class CollectionStorage extends AbstractStorage {
    private ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public boolean exist(String uuid) {
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doSave(Resume resume) {
        int idx = getIndex(resume.getUuid());
        storage.add(resume);
    }

    @Override
    public void doUpdate(Resume resume) {
        int idx = getIndex(resume.getUuid());
        storage.remove(idx);
        storage.add(resume);
    }

    @Override
    public Resume doLoad(String uuid) {
        int idx = getIndex(uuid);
        return storage.get(idx);
    }

    @Override
    public void doDelete(String uuid) {
        int idx = getIndex(uuid);
        storage.remove(idx);
    }

    @Override
    public void doClear() {
        storage = new ArrayList<>();
    }

    @Override
    public Collection<Resume> doGetAllSorted() {
        storage.sort(Comparator.comparing(Resume::getFullName));
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
