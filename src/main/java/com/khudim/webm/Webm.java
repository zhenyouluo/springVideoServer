package com.khudim.webm;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

/**
 * Created by Beaver.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Webm.findAll",
                query = "SELECT webm FROM Webm webm"),
        @NamedQuery(name = "Webm.findByName",
                query = "SELECT webm FROM Webm webm WHERE webm.name = :name "),
        @NamedQuery(name = "Webm.findById",
                query = "SELECT webm FROM Webm webm WHERE webm.id = :id "),
        @NamedQuery(name = "Webm.findByPath",
                query = "SELECT webm FROM Webm webm WHERE webm.path = :path ")
})
@Table(name = "webm")
public class Webm {
    private long id;
    private String name;
    private long date;
    private byte[] image;
    private int rating;
    private long voteCount;
    private int width;
    private int height;
    private String path;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Column(name = "name", length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "date", nullable = false)
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }


    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    @Column(name = "rating")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Column(name = "vote_count")
    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }


    @Column(name = "width")
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    @Column(name = "height")
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @Column(name = "path", unique = true)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Webm that = (Webm) o;

        if (id != that.id) return false;
        if (rating != that.rating) return false;
        if (voteCount != that.voteCount) return false;
        if (width != that.width) return false;
        if (height != that.height) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        if (!Arrays.equals(image, that.image)) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        result = 31 * result + rating;
        result = 31 * result + (int) (voteCount ^ (voteCount >>> 32));
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
