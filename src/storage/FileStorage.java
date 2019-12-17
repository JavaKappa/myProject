package storage;

import ru.webapp.model.Resume;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * Капу пк
 * 18.12.2019
 */
public class FileStorage extends AbstractStorage<File> {
    public static final String path = "fileStorage";
    @Override
    protected File getContext(String uuid) {
        return null;
    }

    @Override
    protected boolean exist(File path) {
        return false;
    }

    @Override
    public void doSave(Resume resume) {

    }

    @Override
    public void doUpdate(File path, Resume resume) {

    }

    @Override
    public Resume doLoad(File file) {
        return null;
    }

    @Override
    public void doDelete(File file) {

    }

    @Override
    public void doClear() {

    }

    @Override
    public Collection<Resume> doGetAllSorted() {
        return null;
    }

    @Override
    public List<Resume> doGetAll() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
