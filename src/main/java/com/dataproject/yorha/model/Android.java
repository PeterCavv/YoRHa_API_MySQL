package com.dataproject.yorha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity(name = "androids")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Android {
    @Id
    @GeneratedValue
    private Long id;
    private int number;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;
    private String model;
    private String appearance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;

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
}
