package storage;

import ru.webapp.model.Resume;

import java.io.*;
import java.util.*;


/**
 * Капу пк
 * 18.12.2019
 */
public class FileStorage extends AbstractStorage<File> {
    public static final File pathToFiles = new File("file_storage");
    {
        if (!pathToFiles.exists()) {
            pathToFiles.mkdir();
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
        String pathname = pathToFiles.getAbsolutePath() + "\\" + resume.getUuid() + ".txt";
        File f = new File(pathname);
        try {
            if (!f.exists())
            f.createNewFile();
        } catch (IOException e) {
            throw new WebAppException("Couldn't create file", e);
        }
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pathname))){
            os.writeObject(resume);
        } catch (IOException e) {
            throw new WebAppException("can not serialize Resume", e);
        }
    }


    @Override
    public void doUpdate(File file, Resume resume) {
        doSave(resume);
    }

    @Override
    public Resume doLoad(File file){
       try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(file))){
           return (Resume) os.readObject();
       } catch (ClassNotFoundException | IOException e) {
           e.printStackTrace();
       }
        return null;
    }

    @Override
    public void doDelete(File file) {
        if (!file.delete()) throw new WebAppException("can't delete file");
    }

    @Override
    public void doClear()  {
        File[] files = pathToFiles.listFiles();
        if (files != null) {
            for(File file: files){
                doDelete(file);
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
        String[] list = pathToFiles.list();
        if (list == null) throw new WebAppException("Couldn't read directory " + pathToFiles.getAbsolutePath());
        return list.length;
    }
}
