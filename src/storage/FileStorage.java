package storage;

import ru.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Капу пк
 * 18.12.2019
 */
public class FileStorage extends AbstractStorage<File> {
    public File pathToFiles = new File("file_storage");
    {
        try {
            pathToFiles.createNewFile();
        } catch (IOException e) {
            throw new WebAppException("Can not crate file", e);
        }
    }

    public FileStorage() {
    }

    @Override
    protected File getContext(String uuid) {
        return new File(pathToFiles + "/" + uuid + ".txt");
    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }

    @Override
    public void doSave(Resume resume) {
        File f = new File(pathToFiles.getAbsolutePath() + "/" + resume.getUuid() + ".txt");
        try {
            if (!f.exists())
            f.createNewFile();
        } catch (IOException e) {
            throw new WebAppException("Couldn't create file", e);
        }
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pathToFiles.getAbsolutePath() + "/" + resume.getUuid() + ".txt"))){
            os.writeObject(resume);
        } catch (IOException e) {
            throw new WebAppException("can not serialize Resume", e);
        }
    }


    @Override
    public void doUpdate(File file, Resume resume) {
        delete(resume.getUuid());
        save(resume);
    }

    @Override
    public Resume doLoad(File file){
       try {
           ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
           return (Resume) os.readObject();
       } catch (ClassNotFoundException | IOException e) {
           e.printStackTrace();
       }
        return null;
    }

    @Override
    public void doDelete(File file) {
        file.delete();
    }

    @Override
    public void doClear()  {
        File[] files = pathToFiles.listFiles();
        if (files != null) {
            for(File file: files){
                file.delete();
            }
        }
    }

    @Override
    public Collection<Resume> doGetAllSorted() {
        List<Resume> allResumes = getAll();
        allResumes.sort(Comparator.naturalOrder());
        return allResumes;
    }

    @Override
    public List<Resume> doGetAll() {
        List<Resume> allResumes = new ArrayList<>();
        File[] files = pathToFiles.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                allResumes.add(doLoad(files[i]));
            }
        }
        return allResumes;
    }

    @Override
    public int size() {
        File[] files = pathToFiles.listFiles();
        if (files != null) {
            return files.length;
        }
        return 0;
    }
}
