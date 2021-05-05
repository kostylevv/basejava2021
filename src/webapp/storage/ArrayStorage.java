package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based webapp.storage for Resumes
 */
public class ArrayStorage {
    private static final int CAPACITY = 10_000;
    Resume[] storage = new Resume[CAPACITY];

    public int getSize() {
        return size;
    }

    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (r == null || r.getUuid() == null) {
            System.out.println("Can't save null");
            return;
        }
        if (size + 1 == CAPACITY) {
            System.out.println("Storage is full");
            return;
        }
        if (!hasResume(r.getUuid())) {
            storage[size] = r;
            size++;
            System.out.println("Stored resume with UUID " + r.getUuid());
        } else {
            System.out.println("Storage already have resume with UUID " + r.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);
        if (index != -1) return storage[index];
        return null;
    }

    public void delete(String uuid) {
        if (uuid == null) {
            System.out.println("Can't delete null");
            return;
        }
        if (hasResume(uuid)) {
            int index = getResumeIndex(uuid);
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Storage doesn't have Resume with UUID " + uuid);
        }
    }

    public boolean hasResume(Resume resume) {
        if (resume != null && resume.getUuid() != null && getResumeIndex(resume.getUuid()) > -1) return true;
        return false;
    }

    public boolean hasResume(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return hasResume(resume);
    }

    public void update(Resume updated) {
        if (updated == null || updated.getUuid() == null) {
            System.out.println("Can't update with null");
        }
        if (hasResume(updated)) {
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
}
