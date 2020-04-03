package org.petriankin.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.TreeSet;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"dateOfBirth", "tickets"}, callSuper = true)
public class User extends DomainObject {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private NavigableSet<Ticket> tickets = new TreeSet<>();
}
