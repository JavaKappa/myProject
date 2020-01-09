package ru.webapp.storage;

import ru.webapp.WebAppException;
import ru.webapp.model.Resume;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class AbstractStorage<C> implements IStorage{
    protected Logger logger = Logger.getLogger(ArrayStorage.class.getName());

    protected abstract C getContext(String uuid);

    protected abstract boolean exist(C ctx);

    @Override
    public void save(Resume resume) {
        logger.info("Save resume witRh uuid: " + resume.getUuid());
        C context = getContext(resume.getUuid());
        if (exist(context)) {
            doException("Resume already exist");
        }
        doSave(resume);
    }

    abstract public void doSave(Resume resume);
    //---------------------------------------------------------------------------------------

    @Override
    public void update(Resume resume) {
        logger.info("trying update " + resume.getUuid());
        C ctx = getContext(resume.getUuid());
        if (!exist(ctx)) {
            doException("Resume does not exist");
        }
        doUpdate(ctx, resume);
        logger.info("Resume updating was success!");
    }

    abstract public void doUpdate(C ctx, Resume resume);
    //---------------------------------------------------------------------------------------

    @Override
    public Resume load(String uuid) {
        logger.info("trying load" + uuid);
        C ctx = getContext(uuid);
        if (!exist(ctx)) {
            doException("Resume  does not exist");
        }
        return doLoad(ctx);
    }

    abstract public Resume doLoad(C ctx);
    //---------------------------------------------------------------------------------------

    @Override
    public void delete(String uuid) throws WebAppException {
        logger.info("Delete resume with uuid " + uuid);
        C ctx = getContext(uuid);
        if (!exist(ctx)) {
            doException("Resume  does not exist");
        }
        doDelete(ctx);
    }

    abstract public void doDelete(C ctx);
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
        throw new WebAppException(message);
    }
}