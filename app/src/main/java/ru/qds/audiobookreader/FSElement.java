package ru.qds.audiobookreader;

/**
 * Created by eduard on 05.12.17.
 */

public class FSElement
{
    private String name;
    private String path;
    private Boolean dir;
    private Boolean audio;
    private String[] types={"mp3"};
    FSElement(String path, Boolean dir)
    {
        this.audio = false;
        this.path = path;
        this.dir = dir;
        String[] sppath = path.split("/");
        this.name = sppath[sppath.length-1];
        if(!dir) {
            for (int i = 0; i < types.length&&!audio; i++)//
            {
               if (name.endsWith(types[0]))
                {
                    audio = true;
                }
            }
        }
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

    public Boolean isDir() {
        return dir;
    }

    public void setDir(Boolean dir) {
        this.dir = dir;
    }

    public Boolean isAudio() {
        return audio;
    }

    public void setAudio(Boolean audio) {
        this.audio = audio;
    }
}
