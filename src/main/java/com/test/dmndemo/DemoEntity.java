package com.test.dmndemo;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Map;

@Table(name = "demo")
@Entity
@Getter
@Setter
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
        ,
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class DemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String key;

    private String country;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Map<String, String> content;
}
