package ru.webapp;

import ru.webapp.model.Resume;

/**
 * Капу пк
 * 08.12.2019
 */
public class WebAppException extends RuntimeException {
    Resume resume = null;
    String uuid = null;

    public WebAppException(String message) {
        super(message);
    }

    public WebAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebAppException(String message, Resume resume) {
        super(message);
        this.resume = resume;
    }

    public WebAppException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public Resume getResume(){
        return resume;
    }

    public String getUuid() {
        return uuid;
    }
}
