package nl.ing.honours;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "Transaction")
@Entity
public class Transaction implements Serializable {

    @Id
    @Column(insertable = false, updatable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date date;

    private Double amount;

    @SerializedName("external-iban")
    @JsonProperty("external-iban")
    private String iban;

    private String type;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private TransactionCategory category;

    public Transaction() {

    }

    public Transaction(Long id, String sessionId, Date Date, Double amount, String externalIban, String type,
                       TransactionCategory category) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }

}


