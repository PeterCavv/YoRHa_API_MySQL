package com.dataproject.yorha.DTO.Android;

import com.dataproject.yorha.model.Android;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetAllAndroidDTO implements Serializable {
    private final Long id;
    private final String name;
    private final String status;
    private final String type;

    public GetAllAndroidDTO(Android android) {
        this.id = android.getId();
        this.name = android.getName();
        this.status = android.getStatus().getName();
        this.type = android.getType().getName();
    }

}
