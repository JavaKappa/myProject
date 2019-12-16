package storage;

import ru.webapp.model.Resume;
import java.util.*;

/**
 * Капу пк
 * 06.12.2019
 */
public class ArrayStorage extends AbstractStorage {
    private static final int LIMIT = 100;
    private int size = 0;
    private Resume[] resumes = new Resume[LIMIT];


    @Override
    public void doSave(Resume resume) {
        resumes[size++] = resume;
    }

    @Override
    public void doUpdate(Resume resume) {
        resumes[getIndex(resume.getUuid())] = resume;
    }


    @Override
    public Resume doLoad(String uuid) {

        return resumes[getIndex(uuid)];
    }

    @Override
    public void doDelete(String uuid) throws WebAppException {
//        int idx = getIndex(uuid);
//        int numMoved = size - idx - 1;
//        if (numMoved > 0)
//            System.arraycopy(resumes, idx + 1, resumes, idx,
//                    numMoved);
//        resumes[--size] = null;
        int numMoved = size - getIndex(uuid) - 1;
        if (numMoved > 0)
            System.arraycopy(resumes, getIndex(uuid) + 1, resumes, getIndex(uuid),
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
        List<Resume> list = getAll();
        Collections.sort(list);
        return list;
    }

    @Override
    public List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(resumes, size));
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    protected int getIndex(String uuid) {
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