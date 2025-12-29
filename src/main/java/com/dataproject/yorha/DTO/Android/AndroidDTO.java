package com.dataproject.yorha.DTO.Android;

import com.dataproject.yorha.model.Android;
import lombok.Getter;

@Getter
public class AndroidDTO {
    private final Long id;
    private final String name;
    private final String shortName;
    private final String status;
    private final String type;
    private final String description;

    public AndroidDTO(Android android) {
        this.id = android.getId();
        this.name = android.getName();
        this.shortName = android.getShortName();
        this.status = android.getStatus().getName();
        this.type = android.getType().getName();
        this.description = android.getDescription();
    }

}
