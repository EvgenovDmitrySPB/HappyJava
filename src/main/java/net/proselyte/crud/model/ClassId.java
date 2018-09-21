package net.proselyte.crud.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ClassId {
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 5)
    protected Long id;

}
