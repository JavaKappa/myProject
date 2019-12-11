package storage;

import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;

public class CollectionStorage extends AbstractStorage {
    private ArrayList<Resume> storage = new ArrayList<>();
    @Override
    public void doSave(Resume resume) {
        int idx = getIndex(resume.getUuid());
        if (idx != -1) {
            doException("Resume already exist");
        } else storage.add(resume);
    }

    @Override
    public void doUpdate(Resume resume) {
        int idx = getIndex(resume.getUuid());
        if (idx == -1) {
            doException("this resume does not exists");
        } else {
            storage.remove(idx);
            storage.add(resume);
        }

    }

    @Override
    public Resume doLoad(String uuid) {
        int idx = getIndex(uuid);
        if (idx == -1) {
            doException("Resume does not exist");
        }
        return storage.get(idx);
    }

    @Override
    public void doDelete(String uuid) {
        int idx = getIndex(uuid);
        if (idx == -1) doException("Resume does not exist");
        storage.remove(idx);
    }

    @Override
    public void doClear() {
        storage = new ArrayList<>();
    }

    @Override
    public Collection<Resume> doGetAllSorted() {
        storage.sort(new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
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
