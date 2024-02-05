package com.lufecrx.academico;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Estudante {
    @Id
    @GeneratedValue (strategy = javax.persistence.GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String email;
    private String matricula;

    Estudante (String nome, String email, String matricula) {
        this.id = null;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
    }

    Estudante () {

    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Estudante{" + this.nome + ", " + this.email + ", " + this.matricula + '}';
    }
}
