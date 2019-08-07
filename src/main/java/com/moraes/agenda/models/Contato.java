package com.moraes.agenda.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@NotNull
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded =  true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @Column(length = 100)
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Email
    private String email;

    @Null
    private String photo;

    @Column(name ="birth_date")
    private LocalDate birthDate;

    @ElementCollection(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @CollectionTable(name="contatos_phones", joinColumns = @JoinColumn(name = "contatos_id"))
    private List<String> phones;

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", birthDate=" + birthDate +
                ", phones=" + phones +
                '}';
    }
}
