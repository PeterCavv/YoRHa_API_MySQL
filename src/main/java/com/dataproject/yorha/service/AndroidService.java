package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.Android.CreateAndroidDTO;
import com.dataproject.yorha.DTO.Android.GetAllAndroidDTO;
import com.dataproject.yorha.DTO.Android.GetOneAndroidDTO;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.model.Android;
import com.dataproject.yorha.model.Status;
import com.dataproject.yorha.model.Type;
import com.dataproject.yorha.repository.AndroidRepository;
import jakarta.transaction.Transactional;
import mapper.AndroidMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AndroidService {
    private final TypeRepository typeRepository;
    private final AndroidRepository androidRepository;
    private final AndroidMapper androidMapper;

    public AndroidService(
            TypeRepository typeRepository,
            AndroidRepository androidRepository,
            AndroidMapper androidMapper
    ) {
        this.typeRepository = typeRepository;
        this.androidRepository = androidRepository;
        this.androidMapper = androidMapper;
    }

    public List<GetAllAndroidDTO> findAll() {
        return androidRepository.findAll()
                .stream().map(androidMapper::toGetAllDto).toList();
    }

    public List<GetAllAndroidDTO> findAllWithoutTrashed() {
        return androidRepository.findByStatus(Status.ACTIVE)
                .stream().map(androidMapper::toGetAllDto).toList();
    }

    public Optional<GetOneAndroidDTO> findById(Long id) {
        return androidRepository.findById(id).map(androidMapper::toGetOneDto);
    }

    public Android create(CreateAndroidDTO dto) {
        Type type = typeRepository.findById(dto.getType_id()).orElseThrow(
                () -> new ObjectNotFoundException("Type not found with ID: " + dto.getType_id())
        );

        Android android = androidMapper.toEntity(dto, type);

        return androidRepository.save(android);
    }

    public Android delete(Long id) {
        Android android = androidRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Android not found with ID: " + id)
        );

        android.delete();

        return androidRepository.save(android);
    }
}
