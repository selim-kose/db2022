package se.iths.pojo;

import java.util.ArrayList;
import java.util.List;

public class Artist {

    private final int id;
    private String name;
    private List<Album> albums = new ArrayList<>();


    public Artist(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId(){
        return this.id;
    }

    public void add(Album album){
    albums.add(album);
    }

    public String toString(){
        StringBuilder builder = new StringBuilder(String.valueOf(id));
        builder.append(": ");
        builder.append(name);
        builder.append("\nAlbums:\n");
        for(Album album : albums){
            builder.append("\t");
            builder.append(album);
            builder.append("\n");
        }
        return builder.toString();
    }
}
