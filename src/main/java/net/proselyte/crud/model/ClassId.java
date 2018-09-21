package net.proselyte.crud.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ClassId {
    @Id
    Long id;
}
