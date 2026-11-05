package com.dataproject.yorha.android.infrastructure;

import com.dataproject.yorha.android.domain.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "androids")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AndroidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    private String description;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String appearance;

    @Column(name = "type_id", nullable = false)
    private Long typeId;

    @Column(name = "operator_id")
    private Long operatorId;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public AndroidEntity(
            String description,
            String model,
            String appearance,
            Long typeId
    ) {
        this.description = description;
        this.model = model;
        this.appearance = appearance;
        this.typeId = typeId;
    }
}
