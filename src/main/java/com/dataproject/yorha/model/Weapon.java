package com.dataproject.yorha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weapons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Weapon {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weapon_type_id", nullable = false)
    private WeaponType weaponType;
}
