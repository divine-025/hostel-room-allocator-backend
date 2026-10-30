package com.exam.hostelroomallocator.service;

import com.exam.hostelroomallocator.domain.DutyArea;
import com.exam.hostelroomallocator.domain.HostelBlock;
import com.exam.hostelroomallocator.domain.Warden;
import com.exam.hostelroomallocator.repository.DutyAreaRepository;
import com.exam.hostelroomallocator.repository.HostelBlockRepository;
import com.exam.hostelroomallocator.repository.WardenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class WardenService {
    private final WardenRepository repository;
    private final DutyAreaRepository dutyAreaRepository;
    private final HostelBlockRepository hostelBlockRepository;

    public WardenService(WardenRepository repository, DutyAreaRepository dutyAreaRepository, HostelBlockRepository hostelBlockRepository ) {
         this.repository = repository;
         this.dutyAreaRepository = dutyAreaRepository;
         this.hostelBlockRepository = hostelBlockRepository;
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

    //C1
    public List<Warden> findByDutyAreaName(String name) {
        return repository.searchByDutyAreaName(name);
    }

    //D3: add one duty area to a warden, keeping the ones already there
    @Transactional 
    public String addDutyArea(UUID wardenId, UUID dutyAreaId) {
        Optional<Warden> wardenFound = repository.findById(wardenId);
        if (wardenFound.isEmpty()) {
            return "The warden with that id does not exist";
        }
        Optional<DutyArea> dutyAreaFound = dutyAreaRepository.findById(dutyAreaId);
        if (dutyAreaFound.isEmpty()) {
            return "That duty area does not exist";
        }
        Warden warden = wardenFound.get();
        DutyArea dutyArea = dutyAreaFound.get();
        if (warden.getDutyAreas() == null) {
            warden.setDutyAreas(new ArrayList<>());
        }
        if (warden.getDutyAreas().contains(dutyArea)) {
            return "The warden already already has that duty area";
        }
        warden.getDutyAreas().add(dutyArea);
        repository.save(warden); //the warden is the owner of the relationship
        return "Duty area added to the warden";
    }

    //D4: move a warden to another hostel block
    public String changeHostelBlock(UUID wardenId, int blockNumber) {
        Optional<Warden> wardenFound = repository.findById(wardenId);
        if (wardenFound.isEmpty()) {
            return "The warden with that id does not exist";
        }
        Optional<HostelBlock> blockFound = hostelBlockRepository.findByBlockNumber(blockNumber);
        if (blockFound.isEmpty()) {
            return "The hostel block with that number does not exist";
        }
        Warden warden = wardenFound.get();
        HostelBlock block = blockFound.get();

        boolean alreadyHis = warden.getHostelBlock() != null && warden.getHostelBlock().getId().equals(block.getId());
        if (!alreadyHis && repository.existsByHostelBlock(block)) {
            return "That hostel block is taken by another warden";
        }
        warden.setHostelBlock(block);
        repository.save(warden);
        return "Hostel block changed successfully";

    }

}

