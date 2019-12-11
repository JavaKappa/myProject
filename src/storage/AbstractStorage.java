package storage;

import ru.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

abstract public class AbstractStorage implements IStorage{
    protected Logger logger = Logger.getLogger(ArrayStorage.class.getName());

    @Override
    public void save(Resume resume) {
        logger.info("Save resume witRh uuid: " + resume.getUuid());
        doSave(resume);
    }

    abstract public void doSave(Resume resume);
    //---------------------------------------------------------------------------------------

    @Override
    public void update(Resume resume) {
        logger.info("trying update " + resume.getUuid());
        doUpdate(resume);
        logger.info("Resume updating was success!");
    }

    abstract public void doUpdate(Resume resume);
    //---------------------------------------------------------------------------------------

    @Override
    public Resume load(String uuid) {
        logger.info("trying load" + uuid);
        return doLoad(uuid);
    }

    abstract public Resume doLoad(String uuid);
    //---------------------------------------------------------------------------------------

    @Override
    public void delete(String uuid) throws WebAppException {
        logger.info("Delete resume with uuid " + uuid);
        doDelete(uuid);
    }

    abstract public void doDelete(String uuid);
    //---------------------------------------------------------------------------------------

    @Override
    public void clear() {
        logger.info("Deleted all resumes.");
        doClear();
    }

    public abstract void doClear();
    //---------------------------------------------------------------------------------------

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("run method getAllSorted");
        return doGetAllSorted();
    }

    public abstract Collection<Resume> doGetAllSorted();


    void doException(String message) {
        logger.severe(message);
        throw new WebAppException(message);
    }
}