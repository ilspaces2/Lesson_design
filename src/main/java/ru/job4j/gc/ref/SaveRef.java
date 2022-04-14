package ru.job4j.gc.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SaveRef {
    private String name;
    private int id;

    public SaveRef(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("Delete: %s%n", name);
    }

    public static void saveSoftReference() throws InterruptedException {
        List<SoftReference<SaveRef>> softUsers = new ArrayList<>();
        for (int i = 0; i < 27000; i++) {
            softUsers.add(new SoftReference<>(new SaveRef("Name" + i, i)));
        }

        List<SaveRef> saveUsers = new ArrayList<>();
        saveUsers.add(softUsers.get(34).get());
        saveUsers.add(softUsers.get(1034).get());
        saveUsers.add(softUsers.get(2034).get());
        saveUsers.add(softUsers.get(15034).get());

        System.gc();
        TimeUnit.SECONDS.sleep(3);

        int countSave = 0;
        for (SoftReference<SaveRef> users : softUsers) {
            SaveRef user = users.get();
            if (user != null) {
                countSave++;
                System.out.println("Saved " + user.id);
            }
        }
        System.out.println("Saved " + countSave);
    }

    public static void saveWeakReference() throws InterruptedException {
        ReferenceQueue<SaveRef> queue = new ReferenceQueue<>();
        List<WeakReference<SaveRef>> weakUsers = new ArrayList<>();

        weakUsers.add(new WeakReference<>(new SaveRef("Name" + 1, 1)));
        weakUsers.add(new WeakReference<>(new SaveRef("Name" + 2, 2), queue));
        SaveRef saveRef = weakUsers.get(0).get();

        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Saved: " + weakUsers.get(0).get().name);
        System.out.println("Deleted weakRef " + queue.poll());
        System.out.println("Deleted object " + weakUsers.get(1).get());
    }

    public static void main(String[] args) throws InterruptedException {
        saveSoftReference();
        saveWeakReference();
    }
}
