package se.iths.pojo;

public class Album {

    private final int id;
    private String title;


    public Album(int id,String name){
        this.id = id;
        this.title = name;

    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return String.format("%d %s", id, title);
    }
}
