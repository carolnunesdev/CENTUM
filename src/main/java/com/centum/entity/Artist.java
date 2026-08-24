package com.centum.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(unique = true, length = 170)
    private String slug;

    protected Artist() {
    }

    public Artist(String name, String slug) { // por que id não é passado como parametro? ele é gerado automaticamente
                                              // pelo banco de dados, certo?
        this.name = name;
        this.slug = slug; // o que seria slug? é o nome do artista sem espaços, certo?, pra que serveria?
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}
