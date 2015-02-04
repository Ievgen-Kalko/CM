package com.cm.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Ievgen on 2/4/2015.
 */
@Entity
@Table(name = "CM_T_COINS", schema = "CM", catalog = "")
public class Coin {
    private long id;
    private String description;
    private String year;
    private long circulation;
    private BigDecimal price;
    private long createdat;
    private BigInteger rowstatus;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "defaultGenerator")
    @SequenceGenerator(name = "defaultGenerator", sequenceName = "CM_T_SEQUENCE")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "YEAR")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "CIRCULATION")
    public long getCirculation() {
        return circulation;
    }

    public void setCirculation(long circulation) {
        this.circulation = circulation;
    }

    @Basic
    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "CREATEDAT")
    public long getCreatedat() {
        return createdat;
    }

    public void setCreatedat(long createdat) {
        this.createdat = createdat;
    }

    @Basic
    @Column(name = "ROWSTATUS")
    public BigInteger getRowstatus() {
        return rowstatus;
    }

    public void setRowstatus(BigInteger rowstatus) {
        this.rowstatus = rowstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coin cmTCoins = (Coin) o;

        if (circulation != cmTCoins.circulation) return false;
        if (createdat != cmTCoins.createdat) return false;
        if (id != cmTCoins.id) return false;
        if (description != null ? !description.equals(cmTCoins.description) : cmTCoins.description != null)
            return false;
        if (price != null ? !price.equals(cmTCoins.price) : cmTCoins.price != null) return false;
        if (rowstatus != null ? !rowstatus.equals(cmTCoins.rowstatus) : cmTCoins.rowstatus != null) return false;
        if (year != null ? !year.equals(cmTCoins.year) : cmTCoins.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (int) (circulation ^ (circulation >>> 32));
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) (createdat ^ (createdat >>> 32));
        result = 31 * result + (rowstatus != null ? rowstatus.hashCode() : 0);
        return result;
    }
}
