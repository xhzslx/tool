package com.example.jpm.librarydemo.tools.base;

import java.util.ArrayList;
import java.util.List;

public class BaseData {


    public List<String> getyearlist() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(2010 + i + "");
        }

        return list;
    }

    public List<String> getquartlist() {
        List<String> list = new ArrayList<>();
        list.add("第1");
        list.add("第2");
        list.add("第3");
        list.add("第4");
        return list;
    }

    public List<String> getweeklist() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            list.add((1 + i) + "");
        }
        return list;
    }
}
