package storage;

import ru.webapp.model.Resume;

import java.text.ParseException;
import java.util.*;

/**
 * Капу пк
 * 06.12.2019
 */
public class ArrayStorage implements IStorage {
    private Resume[] resumes = new Resume[100];

    @Override
    public void save(Resume resume) {
        for (int i = 0; i < resumes.length; i++) {
            if (resumes[i] == null) {
                resumes[i] = resume;
            }
        }
    }

    @Override
    public void update(Resume resume) {
        for (int i = 0; i < resumes.length; i++) {
            if (resumes[i].getFullName().equals(resume.getFullName())) {
                resumes[i] = resume;
            }
        }
    }

    @Override
    public Resume load(String uuid) {
        return resumes[Integer.parseInt(uuid)];
    }
    @Override
    public void delete(String uuid) {
        resumes[Integer.parseInt(uuid)] = null;
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
        return resumes.length;
    }
}
