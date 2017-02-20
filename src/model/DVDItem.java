package model;

import java.util.Comparator;

/**
 * Created by andrea on 07/02/17.
 */
public class DVDItem implements Comparable<DVDItem>{

    public static class DVDYearComparator implements Comparator<DVDItem>{

        @Override
        public int compare(DVDItem o1, DVDItem o2) {
            return o1.getDvdYear() - o2.getDvdYear();
        }
    }

    public static class DVDGenreComparator implements Comparator<DVDItem>{

        @Override
        public int compare(DVDItem o1, DVDItem o2) {
            return o1.getDvdGenre().compareToIgnoreCase(o2.getDvdGenre());
        }
    }

    private String dvdTitle, dvdGenre;
    private int dvdId, dvdYear;

    public DVDItem(int dvdId, String dvdTitle, int dvdYear, String dvdGenre) {
        this.dvdId = dvdId;
        this.dvdTitle = dvdTitle;
        this.dvdYear = dvdYear;
        this.dvdGenre = dvdGenre;
}

    public int getDvdId() {
        return dvdId;
    }
    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public String getDvdTitle() {
        return dvdTitle;
    }
    public void setDvdTitle(String dvdTitle) {
        this.dvdTitle = dvdTitle;
    }

    public int getDvdYear() {
        return dvdYear;
    }
    public void setDvdYear(int dvdYear) {
        this.dvdYear = dvdYear;
    }

    public String getDvdGenre() {
        return dvdGenre;
    }
    public void setDvdGenre(String dvdGenre) {
        this.dvdGenre = dvdGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DVDItem dvdItem = (DVDItem) o;

        if (dvdYear != dvdItem.dvdYear) return false;
        if (dvdTitle != null ? !dvdTitle.equals(dvdItem.dvdTitle) : dvdItem.dvdTitle != null) return false;
        return dvdGenre != null ? dvdGenre.equals(dvdItem.dvdGenre) : dvdItem.dvdGenre == null;
    }

    @Override
    public int hashCode() {
        int result = dvdTitle != null ? dvdTitle.hashCode() : 0;
        result = 31 * result + (dvdGenre != null ? dvdGenre.hashCode() : 0);
        result = 31 * result + dvdYear;
        return result;
    }

    @Override
    public int compareTo(DVDItem o) {
        return dvdTitle.compareToIgnoreCase(o.getDvdTitle());
    }
}
