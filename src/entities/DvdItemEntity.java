package entities;

import javax.persistence.*;

/**
 * Created by andrea on 20/02/17.
 */
@Entity
@Table(name = "DVDItem", schema = "DVDCollection", catalog = "")
public class DvdItemEntity {
    private int id;
    private String title;
    private String genre;
    private int year;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 30)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "genre", nullable = false, length = 30)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DvdItemEntity that = (DvdItemEntity) o;

        if (id != that.id) return false;
        if (year != that.year) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }
}
