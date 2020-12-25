package com.test_webflux.Spring_with_webflux.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document
public class Studio {

    @Id
    private String id;
    private String name;
    private String foundation_origin;
    private int founded_in;

    public Studio() {
        super();
    }
    public Studio(String id, String name, String foundation_origin, int founded_in) {
        this.id = id;
        this.name = name;
        this.foundation_origin = foundation_origin;
        this.founded_in = founded_in;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studio)) return false;
        Studio studio = (Studio) o;
        return Objects.equals(id, studio.id) &&
                Objects.equals(name, studio.name) &&
                Objects.equals(foundation_origin, studio.foundation_origin) &&
                Objects.equals(founded_in, studio.founded_in);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, foundation_origin, founded_in);
    }
    @Override
    public String toString() {
        return "Studio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundation_origin='" + foundation_origin + '\'' +
                ", founded_in=" + founded_in +
                '}';
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFoundation_origin() {
        return foundation_origin;
    }
    public void setFoundation_origin(String foundation_origin) {
        this.foundation_origin = foundation_origin;
    }
    public int getFounded_in() {
        return founded_in;
    }
    public void setFounded_in(int founded_in) {
        this.founded_in = founded_in;
    }
}