package com.cm.domain.model;

import com.cm.domain.AbstractAuditableEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CM_T_COINS")
public class Coin extends AbstractAuditableEntity {

    public enum Columns {
        ID("id"),
        DESCRIPTION("description"),
        COMPOSITION("composition"),
        COUNTRY("country"),
        YEAR("year"),
        CIRCULATION("circulation"),
        GRADE("grade"),
        PRICE("price"),
        CREATEDAT("createdat"),
        ROWSTATUS("rowstatus");

        private final String value;

        private Columns(String value) {
            this.value = value;
        }
    }

    @Column(name = "COMPOSITION")
    @Enumerated(EnumType.ORDINAL)
    private CompositionType composition;

    @Column(name = "GRADE")
    @Enumerated(EnumType.ORDINAL)
    private GradeType grade;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "YEAR")
    private String year;

    @Column(name = "CIRCULATION")
    private long circulation;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Transient
    private boolean isGradeRestricted;

    @Transient
    private BigDecimal rawPrice;

    public Coin() {
    }

    public void setComposition(CompositionType composition) {
        this.composition = composition;
    }

    public void setGrade(GradeType grade) {
        this.grade = grade;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCirculation(long circulation) {
        this.circulation = circulation;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CompositionType getComposition() {
        return composition;
    }

    public GradeType getGrade() {
        return grade;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public String getYear() {
        return year;
    }

    public long getCirculation() {
        return circulation;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setGradeRestricted(boolean isGradeRestricted) {
        this.isGradeRestricted = isGradeRestricted;
    }

    public void setRawPrice(BigDecimal rawPrice) {
        this.rawPrice = rawPrice;
    }

    public boolean isGradeRestricted() {
        return isGradeRestricted;
    }

    public BigDecimal getRawPrice() {
        return rawPrice;
    }

    public static enum CompositionType {
        SILVER(0L),
        GOLD(1L),
        COPPER(2L),
        OTHER(3L);

        private final long value;

        private CompositionType(final long value) {
            this.value = value;
        }

        public long getValue() {
            return this.value;
        }
    }

    public static enum GradeType {
        GOOD(0L),
        VERY_GOOD(1L),
        FINE(2L),
        VERY_FINE(3L),
        EXTRA_FINE(4L);

        private final long value;

        private GradeType(final long value) {
            this.value = value;
        }

        public long getValue() {
            return this.value;
        }
    }

}
