package storage;

import ru.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

/**
 * Капу пк
 * 06.12.2019
 */
public class ArrayStorage extends AbstractStorage {
    private static final int LIMIT = 100;
    private int size = 0;
    private Resume[] resumes = new Resume[LIMIT];

    @Override
    public boolean exist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (resumes[i] == null){
                return false;
            }
            if (resumes[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doSave(Resume resume) {
        int idx = getIndex(resume.getUuid());
        resumes[size++] = resume;
    }

    @Override
    public void doUpdate(Resume resume) {
        int idx = getIndex(resume.getUuid());
        resumes[idx] = resume;
    }


    @Override
    public Resume doLoad(String uuid) {
        int idx = getIndex(uuid);
        return resumes[idx];
    }

    @Override
    public void doDelete(String uuid) throws WebAppException {
        int idx = getIndex(uuid);
        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(resumes, idx + 1, resumes, idx,
                    numMoved);
        resumes[--size] = null;
    }

    //added comment to check my git now

    @Override
    public void doClear() {
        Arrays.fill(resumes, null);
        size = 0;
    }

    @Override
    public Collection<Resume> doGetAllSorted() {
        List<Resume> list = Arrays.asList(Arrays.copyOf(resumes, size));
        list.sort(Comparator.comparing(Resume::getFullName));
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (resumes[i] != null) {
                if (resumes[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
