package com.khudim.main;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Arrays;



@Entity
public class Webm {
    private long id;
    private String name;
    private Date date;

    private byte[] image;
    private int rating;
    private long voteCount;

    private String path;

    private int height;

    private int width;

    @Column(name = "path", nullable = false, insertable = true, updatable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    @Column(name = "height", nullable = false, insertable = false, updatable = false)
    public int getWidth() {
        return width;
    }
    @Column(name = "width", nullable = false, insertable = false, updatable = false)
    public void setWidth(int width) {
        this.width = width;
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date", nullable = false, insertable = true, updatable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    @Basic
    @Column(name = "image", nullable = false, insertable = true, updatable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "rating", nullable = false, insertable = true, updatable = true)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "vote_count", nullable = false, insertable = true, updatable = true)
    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Webm webm = (Webm) o;

        if (id != webm.id) return false;
        if (rating != webm.rating) return false;
        if (voteCount != webm.voteCount) return false;
        if (name != null ? !name.equals(webm.name) : webm.name != null) return false;
        if (date != null ? !date.equals(webm.date) : webm.date != null) return false;

        if (!Arrays.equals(image, webm.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);

        result = 31 * result + (image != null ? Arrays.hashCode(image) : 0);
        result = 31 * result + rating;
        result = 31 * result + (int) (voteCount ^ (voteCount >>> 32));
        return result;
    }
}
