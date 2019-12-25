package storage;

import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;


public class CollectionStorage extends AbstractStorage<Integer> {
    private ArrayList<Resume> storage = new ArrayList<>();



    @Override
    public void doSave(Resume resume) {
            storage.add(resume);
    }

    @Override
    public void doUpdate(Integer i, Resume resume) {
        storage.set(i, resume);
    }

    @Override
    public Resume doLoad(Integer i) {
        return storage.get(i);
    }

    @Override
    public void doDelete(Integer i) {
        storage.remove((int)i);
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
    public List<Resume> doGetAll() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isSectionSupported() {
        return true;
    }

    @Override
    protected Integer getContext(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean exist(Integer ctx) {
        return ctx != -1;
    }
}
