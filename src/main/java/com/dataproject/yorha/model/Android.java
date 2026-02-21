package com.dataproject.yorha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "androids")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Android {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int number;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String description;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String appearance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private Android operator;

    @OneToMany(mappedBy = "operator")
    private List<Android> assignedAndroids = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @PrePersist
    public void onCreate() {
        this.status = Status.ACTIVE;
        this.number = this.generateTypeNumber();
    }

    public Android(String description, String model, String appearance, Type type) {
        this.description = description;
        this.model = model;
        this.appearance = appearance;
        this.type = type;
    }

    public String getName() {
        if (this.model.equals("YoRHa")) {
            return "YoRHa Nº " + this.number
                    + " Type " + this.type.getCode();
        }
        return this.model;
    }

    public String getShortName() {
        if (this.model.equals("YoRHa")) {
            return String.valueOf(this.number) + this.type.getCode();
        }
        return this.model;
    }

    public void delete() {
        this.status = Status.INACTIVE;
    }

    private int generateTypeNumber() {
        return this.type.getAndroids().size() + 1;
    }
}
