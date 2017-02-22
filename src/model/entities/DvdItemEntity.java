package model.entities;

import javax.persistence.*;
import java.util.Comparator;

/**
 * Created by andrea on 20/02/17.
 */
@Entity
@Table(name = "DVDItem", schema = "DVDCollection")
public class DvdItemEntity implements Comparable<DvdItemEntity>{

    private int id;
    private String title;
    private String genre;
    private int year;

    public DvdItemEntity() {
    }

    public DvdItemEntity(String title, String genre, int year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public DvdItemEntity(int id, String title, String genre, int year) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

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

        if (year != that.year) return false;
        if (!title.equals(that.title)) return false;
        return genre.equals(that.genre);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + year;
        return result;
    }

    @Override
    public int compareTo(DvdItemEntity o) {
        return title.compareToIgnoreCase(o.getTitle());
    }

    public static class DVDYearComparator implements Comparator<DvdItemEntity> {

        @Override
        public int compare(DvdItemEntity o1, DvdItemEntity o2) {
            return o1.getYear() - o2.getYear();
        }
    }

    public static class DVDGenreComparator implements Comparator<DvdItemEntity>{

        @Override
        public int compare(DvdItemEntity o1, DvdItemEntity o2) {
            return o1.getGenre().compareToIgnoreCase(o2.getGenre());
        }
    }
}
