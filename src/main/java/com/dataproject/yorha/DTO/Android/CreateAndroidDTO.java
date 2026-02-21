package com.dataproject.yorha.DTO.Android;

import com.dataproject.yorha.validation.Type.TypeExists;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateAndroidDTO implements Serializable {
    @Size(max = 1000, message = "{android.description.size")
    private String description;

    @NotBlank(message = "{android.model.required}")
    private String model;

    @NotBlank(message = "{android.appearance.required}")
    private String appearance;

    @TypeExists
    private Long type_id;
}
