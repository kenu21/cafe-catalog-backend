package com.cafes.cafes.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cafe_photos")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cafe_id", nullable = false)
    private CafeEntity cafe;

    @Column(nullable = false, length = 255)
    private String photoLink;

    @Column(nullable = false)
    private Boolean isMain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CafeEntity getCafe() {
        return cafe;
    }

    public void setCafe(CafeEntity cafe) {
        this.cafe = cafe;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }
}
