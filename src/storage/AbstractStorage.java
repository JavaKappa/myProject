package storage;

import ru.webapp.model.Resume;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

abstract public class AbstractStorage implements IStorage{
    protected Logger logger = Logger.getLogger(ArrayStorage.class.getName());

    protected abstract int getIndex(String uuid);

    @Override
    public void save(Resume resume) {
        logger.info("Save resume witRh uuid: " + resume.getUuid());
        if (getIndex(resume.getUuid()) != -1) {
            doException("Resume already exist");
        }
        doSave(resume);
    }

    abstract public void doSave(Resume resume);
    //---------------------------------------------------------------------------------------

    @Override
    public void update(Resume resume) {
        logger.info("trying update " + resume.getUuid());
        if (getIndex(resume.getUuid()) == -1) {
            doException("Resume does not exist");
        }
        logger.info("Resume updating was success!");
    }

    abstract public void doUpdate(Resume resume);
    //---------------------------------------------------------------------------------------

    @Override
    public Resume load(String uuid) {
        logger.info("trying load" + uuid);
        if (getIndex(uuid) == -1) {
            doException("Resume  does not exist");
        }
        return doLoad(uuid);
    }

    abstract public Resume doLoad(String uuid);
    //---------------------------------------------------------------------------------------

    @Override
    public void delete(String uuid) throws WebAppException {
        logger.info("Delete resume with uuid " + uuid);
        if (getIndex(uuid) == -1) {
            doException("Resume does not exist");
        }
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

    public List<Resume> getAll(){
        logger.info("getiing all");
        return doGetAll();
    }

    public abstract List<Resume> doGetAll ();


    void doException(String message) {
        logger.severe(message);
        throw new WebAppException(message);
    }
}