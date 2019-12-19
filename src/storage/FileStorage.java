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
public class FileStorage extends AbstractStorage<Path> {
    public static final File pathToFiles = new File("file_storage");
    {
        createFile(pathToFiles);
    }

    public FileStorage() {
    }

    @Override
    protected Path getContext(String uuid) {
        return Paths.get(pathToFiles + "/" + uuid + ".txt");
    }

    @Override
    protected boolean exist(Path path) {
        return path.toFile().exists();
    }

    @Override
    public void doSave(Resume resume) {
        File f = new File(pathToFiles.getAbsolutePath() + "/" + resume.getUuid() + ".txt");
        createFile(f);
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pathToFiles.getAbsolutePath() + "/" + resume.getUuid() + ".txt"))){
            os.writeObject(resume);
        } catch (IOException e) {
            throw new WebAppException("can not serialize Resume", e);
        }
    }

    private void createFile(File file) {
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new WebAppException("cn not create file");
                }
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void doUpdate(Path path, Resume resume) {
        delete(resume.getUuid());
        save(resume);
    }

    @Override
    public Resume doLoad(Path path){
       try {
           ObjectInputStream os = new ObjectInputStream(new FileInputStream(path.toFile()));
           return (Resume) os.readObject();
       } catch (ClassNotFoundException | IOException e) {
           e.printStackTrace();
       }
        return null;
    }

    @Override
    public void doDelete(Path path) {
        path.toFile().delete();
    }

    @Override
    public void doClear()  {
        try {
            Stream<Path> allResumes = Files.list(pathToFiles.toPath());
            allResumes.forEach(this::doDelete);
        } catch (IOException e) {
            throw new WebAppException("can not get files");
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
        ArrayList<Resume> list = new ArrayList<>();
        try {
            Stream<Path> allResumesPaths = Files.list(pathToFiles.toPath());
            allResumesPaths.forEach(p -> list.add(doLoad(p)));

        } catch (IOException e) {
            throw new WebAppException("something bad with path", e);
        }
        return list;
    }

    @Override
    public int size() {
        return Objects.requireNonNull(pathToFiles.list()).length;
    }
}
