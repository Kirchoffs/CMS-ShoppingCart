package com.syh.cmsshoppingcart.models.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "pages")
@Data
public class Page {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 2, max = 45, message = "Title mush be at least 2 characters long")
    @Column(name = "title", length = 45)
    private String title;

    @NotNull
    @Column(name = "slug", length = 45)
    private String slug;

    @NotNull
    @Size(min = 5, message = "Content mush be at least 5 characters long")
    @Column(name= "content", length = 65535, columnDefinition = "text")
    private String content;

    @NotNull
    private int sorting;
}
