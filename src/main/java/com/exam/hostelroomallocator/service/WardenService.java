package com.exam.hostelroomallocator.service;

import com.exam.hostelroomallocator.domain.Warden;
import com.exam.hostelroomallocator.repository.WardenRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class WardenService {
    private final WardenRepository repository;

    public WardenService(WardenRepository repository) {
        this.repository = repository;
    }

    public Warden save(Warden warden) {
        return repository.save(warden);
    }

    public List<Warden> findAll() {
        return repository.findAll();
    }
    public Optional<Warden> findById(UUID id) {
        return repository.findById(id);
    }
    public Optional<Warden> update(UUID id, Warden warden) {
        if (repository.existsById(id)) {
            warden.setId(id);
            return Optional.of(repository.save(warden));
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

