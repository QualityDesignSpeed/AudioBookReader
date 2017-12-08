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
        this.path = path;
        this.dir = dir;
        String[] sppath = path.split("/");
        this.name = sppath[sppath.length-1];
        String[] sppathtype = path.split(".");
        if(!dir) {
            for (int i = 0; i < types.length; i++)//&&!audio
            {
                String a = types[0];
               /* if (types[0].equalsIgnoreCase(sppathtype[1])) ;
                {
                    audio = true;
                }*/
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
