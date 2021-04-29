package webapp.storage;

import webapp.model.Resume;

/**
 * Array based webapp.storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    public int getSize() {
        return size;
    }

    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if (r != null) {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);
        if (index != -1) return storage[index];
        return null;
    }

    public void delete(String uuid) {
        int index = getResumeIndex(uuid);
        if (index > -1) {
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in webapp.storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }
}
