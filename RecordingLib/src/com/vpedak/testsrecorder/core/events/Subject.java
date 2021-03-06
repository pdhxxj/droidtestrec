package com.vpedak.testsrecorder.core.events;

import com.vpedak.testsrecorder.core.Data;
import com.vpedak.testsrecorder.core.TestGenerator;

public abstract class Subject {
    public void accept(StringBuilder sb, TestGenerator generator){
        generator.generateSubject(sb, this);
    }

    @Override
    public String toString() {
        return Subject.toString(this);
    }

    public static String toString(Subject subject) {
        if (subject instanceof View) {
            View view = (View) subject;
            return "view=" + view.getId();
        } else if (subject instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) subject;
            return "menuItem=" + menuItem.getId() + "#" + menuItem.getTitle();
        } else if (subject instanceof OptionsMenu) {
            return "optionsMenu";
        } else if (subject instanceof Data) {
            Data data = (Data) subject;
            return "data="+data.getClassName()+(data.getValue()==null?"":"#"+data.getValue());
        } else {
            throw new RuntimeException("Unknown subject - " + subject.getClass());
        }
    }

    public static Subject fromString(String str) {
        if (str.startsWith("view")) {
            String id = str.substring(str.indexOf("=") + 1);
            return new View(id);
        } else if (str.startsWith("menuItem")) {
            String tmp = str.substring(str.indexOf("=") + 1);
            int pos = tmp.indexOf("#");
            String id = tmp.substring(0, pos);
            String title = tmp.substring(pos+1, tmp.length());
            return new MenuItem(title, id);
        } else if (str.startsWith("optionsMenu")) {
            return new OptionsMenu();
        } else if (str.startsWith("data")) {
            String tmp = str.substring(str.indexOf("=") + 1);
            int pos = tmp.indexOf("#");
            if (pos != -1) {
                String className = tmp.substring(0, pos);
                String value = tmp.substring(pos + 1, tmp.length());
                return new Data(className, value);
            } else {
                return new Data(tmp);
            }
        } else {
            throw new RuntimeException("Unknown subject - " + str);
        }
    }
}
