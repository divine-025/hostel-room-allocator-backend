package com.exam.hostelroomallocator.service;

import com.exam.hostelroomallocator.domain.DutyArea;
import com.exam.hostelroomallocator.repository.DutyAreaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class DutyAreaService {

    private final DutyAreaRepository repository;

    public DutyAreaService(DutyAreaRepository repository) {
        this.repository = repository;

    }

    public DutyArea save(DutyArea dutyArea) {
        return repository.save(dutyArea);

    }

    public List<DutyArea> findAll() {
        return repository.findAll();
    }

    public Optional<DutyArea> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<DutyArea> update(UUID id, DutyArea dutyArea) {
        if (repository.existsById(id)) {
            dutyArea.setId(id);
            return Optional.of(repository.save(dutyArea));

        }
        return Optional.empty();

    }
    public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
