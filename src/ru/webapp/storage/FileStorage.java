package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.io.*;
import java.util.*;

/**
 * Капу пк
 * 22.12.2019
 */
abstract public class FileStorage extends AbstractStorage<File> {
    public File pathToFiles = new File("C:\\Users\\Kapy\\IdeaProjects\\baseJavaProject\\file_storage");


    public FileStorage(String path) {
        pathToFiles = new File(path);
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
        if (!pathToFiles.exists()) {
            pathToFiles.mkdirs();
        }
        String pathname = pathToFiles.getAbsolutePath() + "\\" + resume.getUuid() + ".txt";
        File f = new File(pathname);
        try {
            if (!f.exists())
                f.createNewFile();
        } catch (IOException e) {
            throw new WebAppException("Couldn't create file", e);
        }
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pathname))) {
            write(os, resume);
        } catch (IOException e) {
            throw new WebAppException("can not serialize Resume", e);
        }
    }

    protected abstract void write(ObjectOutputStream os, Resume resume) throws IOException;

    @Override
    public void doUpdate(File file, Resume resume) {
        doSave(resume);
    }

    @Override
    public Resume doLoad(File file) {
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(file))) {
            return read(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract Resume read(ObjectInputStream os) throws IOException;

    @Override
    public void doDelete(File file) {
        if (!file.delete()) throw new WebAppException("can't delete file");
    }

    @Override
    public void doClear() {
        File[] files = pathToFiles.listFiles();
        if (files != null) {
            for (File file : files) {
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

