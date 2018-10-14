package info.maalvarez.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="ProductExample")
@Table(name="\"PRODUCTEXAMPLE\"")
public class ProductExampleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @Column(length = 50, name="\"name\"")
    @NotNull
    private String name;

    @Digits(integer = 5,  fraction = 2)
    @Column(precision = 7, scale = 2, name="\"price\"")
    private BigDecimal price;

    @Column(name="\"stock\"")
    @Digits(integer = 4, fraction = 0)
    private Integer stock;

    @Column(name="\"launchDate\"")
    @Temporal(TemporalType.DATE)
    private Date launchDate;

    @Column(name="\"discontinued\"")
    private Boolean discontinued;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getLaunchDate() {
        return this.launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Boolean getDiscontinued() {
        return this.discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

}
