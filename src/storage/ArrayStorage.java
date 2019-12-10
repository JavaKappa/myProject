package storage;

import ru.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Капу пк
 * 06.12.2019
 */
public class ArrayStorage implements IStorage {
    private static final int LIMIT = 100;
    private int size = 0;
    private Resume[] resumes = new Resume[LIMIT];
    private static Logger LOGGER = Logger.getLogger(ArrayStorage.class.getName());

    @Override
    public void save(Resume resume) {
        LOGGER.info("Save resume witRh uuid: " + resume.getUuid());
        int idx = getIndex(resume.getUuid());
        if (idx != -1) {
            LOGGER.severe("this resume already in use");
            throw new WebAppException("this resume already in use");
        }
        resumes[size++] = resume;

    }

    @Override
    public void update(Resume resume) {
        LOGGER.info("trying update " + resume.getUuid());
        int idx = getIndex(resume.getUuid());
        if (idx == -1) {
            LOGGER.severe("Resume does not exists");
            throw new WebAppException("Resume does not exists");
        }
        LOGGER.info("Resume updating was success!");
        resumes[idx] = resume;

    }

    @Override
    public Resume load(String uuid) {
        LOGGER.info("trying load" + uuid);
        int idx = getIndex(uuid);
        if (idx == -1) {
            LOGGER.severe("Resume does  not exists");
            throw new WebAppException("Resume does  not exists");
        }
        return resumes[idx];
    }

    @Override
    public void delete(String uuid) throws WebAppException {
        LOGGER.info("DELIting resume with uuid " + uuid);
        int idx = getIndex(uuid);
        if (idx == -1) {
            LOGGER.severe("Resume does not exists");
            throw new WebAppException("Resume does not exists");
        }
        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(resumes, idx + 1, resumes, idx,
                    numMoved);
        resumes[--size] = null;
    }

    //added comment to check my git now

    @Override
    public void clear() {
        Arrays.fill(resumes, null);
        LOGGER.info("Deleted all resumes.");
        size = 0;
    }

    @Override
    public Collection<Resume> getAllSorted() {
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
