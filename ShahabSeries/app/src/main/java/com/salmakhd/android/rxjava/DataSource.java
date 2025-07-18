package com.salmakhd.android.rxjava;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static List<Task> createTaskList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("...", true, 3));
        tasks.add(new Task("...", false, 1));
        tasks.add(new Task("...",false, 4));
        return tasks;
    }
}
