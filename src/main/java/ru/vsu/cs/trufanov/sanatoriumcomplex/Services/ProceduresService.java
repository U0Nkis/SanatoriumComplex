package ru.vsu.cs.trufanov.sanatoriumcomplex.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Models.Procedures;
import ru.vsu.cs.trufanov.sanatoriumcomplex.Repository.api.ProcedureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProceduresService {
    @Autowired
    private ProcedureRepository procedureRepository;

    public List<Procedures> findAllProcedures() {
        return procedureRepository.findAll().stream().sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).toList();
    }

    public Optional<Procedures> findProceduresById(Integer id) {
        return procedureRepository.findById(id);
    }

    public Procedures saveProcedures(Procedures procedures) {
        return procedureRepository.save(procedures);
    }

    public void deleteProcedure(Integer id) {
        procedureRepository.deleteById(id);
    }
}
