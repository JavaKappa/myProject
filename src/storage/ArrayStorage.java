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
    public void update(Resume resume){
        LOGGER.info("trying update " + resume.getUuid());
        for (int i = 0; i < LIMIT; i++) {
            if (resumes[i] == null) {
                try {
                    throw new WebAppException("this resume already in use");
                } catch (Exception e) {
                    LOGGER.severe("this resume already in use");
                }
            }
            if (resumes[i].getUuid().equals(resume.getUuid())) {
                resumes[i] = resume;
                return;
            }
        }
        try {
            throw new WebAppException("this resume already in use");
        } catch (Exception e) {
            LOGGER.severe("this resume already in use");

        }
    }

    @Override
    public Resume load(String uuid) {
        LOGGER.info("trying load" + uuid);
        for (int i = 0; i < LIMIT; i++) {
            if (resumes[i] == null) {
                LOGGER.info("this resume does not exist");
                return null;
            }
            if (resumes[i].getUuid().equals(uuid)) {
                return resumes[i];
            }
        }
        LOGGER.info("this resume does not exist");
        return null;
    }
    @Override
    public void delete(String uuid) throws WebAppException {
        LOGGER.info("DELIting resume with uuid " + uuid);
        for (int i = 0; i < LIMIT; i++) {
            if (resumes[i] == null) {
                try {
                    throw new WebAppException("this resume already in use");
                } catch (Exception e) {
                    LOGGER.severe("this resume already in use");

                }
            }
            if (resumes[i].getUuid().equals(uuid)) {
                resumes[i] = null;
                size--;
                int k = -1;
                for (int j = i + 1; j < LIMIT; j++) {
                    if (resumes[j] == null) {
                        break;
                    }
                    resumes[i] = resumes[j];
                    i++;
                    k = j;
                }
                if (k != -1) {
                    resumes[k] = null;
                }
                return;
            }
        }
    }

    @Override
    public void clear() {
        Arrays.fill(resumes, null);
        LOGGER.info("Deleted all resumes.");
        size = 0;
    }

    @Override
    public Collection<Resume> getAllSorted() {
        return Arrays.stream(resumes).filter(Objects::nonNull).sorted(Comparator.comparing(Resume::getFullName)).collect(Collectors.toList());
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
