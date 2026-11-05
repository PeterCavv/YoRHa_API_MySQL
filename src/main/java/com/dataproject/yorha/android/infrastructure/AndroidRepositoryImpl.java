package com.dataproject.yorha.android.infrastructure;

import com.dataproject.yorha.android.domain.Android;
import com.dataproject.yorha.android.domain.AndroidRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AndroidRepositoryImpl implements AndroidRepository {
    private final AndroidJpaRepository jpaRepository;

    public AndroidRepositoryImpl(AndroidJpaRepository jpaRepository){
        this.jpaRepository = jpaRepository;
    }

    public Android save(){
        return
    }

    private AndroidEntity toEntity(Android android) {
        return new AndroidEntity(
                android.getDescription(),
                android.getModel(),
                android.getAppearance(),
                android.getTypeId().value()
        );
    }

    private Android toDomain(AndroidEntity entity) {}
}
