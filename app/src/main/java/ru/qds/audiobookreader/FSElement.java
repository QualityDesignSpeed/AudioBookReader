package ru.qds.audiobookreader;

/**
 * Created by eduard on 05.12.17.
 */

public class FSElement
{
    private String name;
    private String path;
    private Boolean dir;
    FSElement(String path, Boolean dir)
    {
        this.path = path;
        this.dir=dir;
        String[] sppath = path.split("/");
        this.name = sppath[sppath.length-1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getDir() {
        return dir;
    }

    public void setDir(Boolean dir) {
        this.dir = dir;
    }
}
