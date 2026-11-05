package com.dataproject.yorha.android.domain;

import com.dataproject.yorha.shared.domain.AggregateRoot;
import lombok.Getter;

@Getter
public class Android extends AggregateRoot<AndroidId> {
    private final int number;
    private Status status;
    private final String description;
    private final String model;
    private final String appearance;
    private final TypeId typeId;
    private final AndroidId operatorId;

    private Android(
        AndroidId id,
        TypeId typeId,
        AndroidId operatorId,
        Status status,
        int number,
        String description,
        String model,
        String appearance
    ) {
        super(id);

        validate(appearance, model, typeId);

        this.number = number;
        this.typeId = typeId;
        this.operatorId = operatorId;
        this.description = description;
        this.model = model;
        this.appearance = appearance;
        this.status = status;
    }

    public static Android create(
            TypeId typeId,
            int number,
            String description,
            String model,
            String appearance
    ) {
        return new Android(
                null,
                typeId,
                null,
                Status.ACTIVE,
                number,
                description,
                model,
                appearance
        );
    }

    public static Android restore(
            AndroidId androidId,
            TypeId typeId,
            AndroidId operatorId,
            Status status,
            int number,
            String description,
            String model,
            String appearance
    ) {
        return new Android(
                androidId,
                typeId,
                operatorId,
                status,
                number,
                description,
                model,
                appearance
        );
    }

    public void delete() {
        this.status = Status.INACTIVE;
    }

    public String getName(char typeCode) {
        if (this.model.equals("YoRHa")) {
            return "YoRHa Nº " + this.number + " Type " + typeCode;
        }

        return this.model;
    }

    public String getShortName(char typeCode) {
        if (this.model.equals("YoRHa")){
            return this.number + String.valueOf(typeCode);
        }

        return this.model;
    }

    private static void validate(
            String appearance,
            String model,
            TypeId typeId
    ) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("model can't be null");
        }

        if (appearance == null || appearance.isBlank()) {
            throw new IllegalArgumentException("appearance can't be null");
        }

        if (typeId == null) {
            throw new IllegalArgumentException("typeId can't be null");
        }
    }
}
