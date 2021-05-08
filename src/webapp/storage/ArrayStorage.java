package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based webapp.storage for Resumes
 */
public class ArrayStorage {
    private static final int CAPACITY = 10_000;
    Resume[] storage = new Resume[CAPACITY];
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (resume == null || resume.getUuid() == null) {
            System.out.println("Can't save null");
            return;
        }
        if (size + 1 >= CAPACITY) {
            System.out.println("Storage is full");
            return;
        }
        if (getResumeIndex(resume) == -1) {
            storage[size] = resume;
            size++;
            System.out.println("Stored resume with UUID " + resume.getUuid());
        } else {
            System.out.println("Storage already have resume with UUID " + resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);
        if (index != -1) return storage[index];
        System.out.println("Storage doesn't have Resume with UUID " + uuid);
        return null;
    }

    public void delete(String uuid) {
        if (uuid == null) {
            System.out.println("Can't delete null");
            return;
        }
        if (getResumeIndex(uuid) > -1) {
            int index = getResumeIndex(uuid);
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Storage doesn't have Resume with UUID " + uuid);
        }
    }

    public void update(Resume updated) {
        if (updated == null || updated.getUuid() == null) {
            System.out.println("Can't update with null");
        }
        if (getResumeIndex(updated) > -1) {
            storage[getResumeIndex(updated.getUuid())] = updated;
        } else {
            System.out.println("Doesn't have resume with UUID " + updated.getUuid());
        }
    }
    /**
     * @return array, contains only Resumes in webapp.storage (without null)
     */
    public Resume[] getAll() { return Arrays.copyOf(storage,size); }

    private int getResumeIndex(String uuid) {
        if (uuid != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) return i;
            }
        }
        return -1;
    }

    private int getResumeIndex(Resume resume) {
        return getResumeIndex(resume.getUuid());
    }
}
