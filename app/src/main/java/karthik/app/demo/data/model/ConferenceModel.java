package karthik.app.demo.data.model;

/**
 * Created by Flochat on 17-01-2018.
 */

public class ConferenceModel {

    private boolean isMe = false;
    private int color;
    private String name = "";
    private int person_position;
    private int height=0;

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPerson_position() {
        return person_position;
    }

    public void setPerson_position(int person_position) {
        this.person_position = person_position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
