package storage;

import ru.webapp.model.Resume;

import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;

/**
 * Капу пк
 * 06.12.2019
 */
public class ArrayStorage implements IStorage {
    private static final int LIMIT = 100;
    private int idx;
    private Resume[] resumes = new Resume[LIMIT];

    @Override
    public void save(Resume resume) throws Exception {

        idx = -1;
        for (int i = 0; i < LIMIT; i++) {
            if (resumes[i] != null) {
                if (resume.getUuid().equals(resumes[i].getUuid())) {
                    throw new IllegalStateException("Already in storage, but you can try to update");
                }
            } else {
                idx = i;
            }
        }
        if (idx != -1) {
            resumes[idx] = resume;
            return;
        }
        throw new Exception("array is full");
    }

    @Override
    public void update(Resume resume) {
        for (int i = 0; i < LIMIT; i++) {
            if (resumes[i] != null && resumes[i].getUuid().equals(resume.getUuid())) {
                if (resume.equals(resumes[i])) {
                    throw new IllegalStateException("nothing to added");
                }
                resumes[i] = resume;
            }
        }
    }

    @Override
    public Resume load(String uuid) {
        for (Resume resume : resumes) {
            if (resume != null && resume.getUuid().equals(uuid)) {
                return resume;
            }
        }
        return null;
    }
    @Override
    public void delete(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (resumes[i] != null && resumes[i].getUuid().equals(uuid)) {
                resumes[i] = null;
            }
        }
    }

    @Override
    public void clear() {
        Arrays.fill(resumes, null);
    }

    @Override
    public Collection<Resume> getAllSorted() {
        ArrayList<Resume> list = new ArrayList<>(Arrays.asList(resumes));
        list.sort(Comparator.comparing(Resume::getFullName));
        return list;
    }

    @Override
    public int size() {
        return Arrays.stream(resumes).filter(Objects::nonNull).toArray().length;
    }
}
