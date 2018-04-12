package nl.ing.honours.category;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import nl.ing.honours.exceptions.InvalidInputException;
import nl.ing.honours.session.Session;
import nl.ing.honours.transaction.Transaction;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Category")
@JsonPropertyOrder({"id", "name"})
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonDeserialize(using = Category.Deserializer.class)
public class Category implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Transaction> transactions;

    @ManyToOne
    @JsonIgnore
    private Session session;

    @JsonCreator
    public Category(@JsonProperty("id") Long id) {
        this.id = id;
    }

    @JsonCreator
    public Category(@JsonProperty("name") String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public static class Deserializer extends StdDeserializer<Category> {
        public Deserializer() {
            this(null);
        }

        Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Category deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);
            if (node.has("name") && node.has("id")) {
                throw new InvalidInputException();
            } else if (node.has("name")) {
                String name = node.get("name").asText();
                return new Category(name);
            } else if (node.has("id")){
                Long id = node.get("id").asLong();
                return new Category(id);
            } else {
                throw new InvalidInputException();
            }
        }
    }
}