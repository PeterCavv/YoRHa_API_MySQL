package com.dataproject.yorha.DTO.Android;

import com.dataproject.yorha.model.Android;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetOneAndroidDTO implements Serializable {
    private Long id;
    private String name;
    private String shortName;
    private String status;
    private String type;
    private String description;

    public GetOneAndroidDTO(Android android) {
        this.id = android.getId();
        this.name = android.getName();
        this.shortName = android.getShortName();
        this.status = android.getStatus().getName();
        this.type = android.getType().getName();
        this.description = android.getDescription();
    }

}
