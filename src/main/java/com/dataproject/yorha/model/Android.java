package com.dataproject.yorha.model.android;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToOne
    private Type type_id;

    public String getName() {
        if (this.model.equals("YoRHa")) {
            return "YoRHa Nº " + this.number
                    + " Type " + this.type_id.name.charAt(0);
        }

        return this.model;
    }

    public String gerShortName() {
        if (this.model.equals("YoRHa")) {
            
        }
    }
}
