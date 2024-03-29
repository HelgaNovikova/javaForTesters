package ru.stqa.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "group_list")
public class GroupData {

    @Expose
    @Column(name = "group_name")
    private String name;

    @Column(name = "group_footer")
    @Type(type = "text")
    @Expose
    private String footer;

    @Id
    @Column(name = "group_id")
    private int id = Integer.MAX_VALUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return id == groupData.id && Objects.equals(name, groupData.name)
                && Objects.equals(footer, groupData.footer) && Objects.equals(header, groupData.header);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, footer, id, header);
    }

    @Expose
    @Column(name = "group_header")
    @Type(type = "text")
    private String header;

    public Contacts getContacts() {
        return new Contacts(contacts);
    }

    @ManyToMany (mappedBy = "groups")
    private Set<ContactData> contacts  = new HashSet<>();

    public GroupData withId(int id) {
        this.id = id;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}
