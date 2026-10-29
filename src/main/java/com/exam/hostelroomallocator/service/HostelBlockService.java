package com.exam.hostelroomallocator.service;

import com.exam.hostelroomallocator.domain.HostelBlock;
import com.exam.hostelroomallocator.repository.HostelBlockRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class HostelBlockService {

    private final HostelBlockRepository repository;

    public HostelBlockService(HostelBlockRepository repository) {
        this.repository = repository;
    }

    public HostelBlock save(HostelBlock hostelBlock) {
        return repository.save(hostelBlock);
    }
    public List<HostelBlock> findAll(){
        return repository.findAll();
    
    }
    public Optional<HostelBlock> findById(UUID id){
        return repository.findById(id);

    }
    public Optional<HostelBlock> update(UUID id, HostelBlock hostelBlock) {
        if (repository.existsById(id)) {
            hostelBlock.setId(id);
            return Optional.of(repository.save(hostelBlock));
        
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

    //C2
    public List<HostelBlock> findFreeBlocks() {
        return repository.findFreeBlocks();
    }
}
