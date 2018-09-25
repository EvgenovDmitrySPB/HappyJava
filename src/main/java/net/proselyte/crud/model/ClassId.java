package net.proselyte.crud.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@MappedSuperclass
public class ClassId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, length = 5 )
    protected Long id;

}
