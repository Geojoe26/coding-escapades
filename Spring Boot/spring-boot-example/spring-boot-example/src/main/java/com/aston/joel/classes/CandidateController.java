package com.aston.joel.classes;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateRepo candidateRepo;

    public CandidateController(CandidateRepo candidateRepo){
        this.candidateRepo = candidateRepo;
    }

    @GetMapping
    public List<Candidate> getCandidate()
    {
        return candidateRepo.findAll();
    }

    record AddCandidatereq(String name, Integer age, String email){}

    @PostMapping
    public List<Candidate> addCandidate(@RequestBody AddCandidatereq request)
    {
        Candidate can = new Candidate();
        can.setName(request.name());
        can.setEmail(request.email());
        can.setAge(request.age());

        candidateRepo.save(can);

        return candidateRepo.findAll();
    }

    @DeleteMapping("{candidateId}")
    public List<Candidate> deleteCandidate(@PathVariable("candidateId") Integer id)
    {
        candidateRepo.deleteById(id);
        return candidateRepo.findAll();
    }

    @PostMapping("{candidateId}")
    public List<Candidate> updateCandidate(@PathVariable("candidateId") Integer id, @RequestBody CandidateController.AddCandidatereq request)
    {
        if(candidateRepo.existsById(id))
        {
            Candidate canUpd = candidateRepo.getReferenceById(id);
            canUpd.setAge(request.age()!=null? request.age() : canUpd.getAge());
            canUpd.setName(request.name()!=null? request.name() : canUpd.getName());
            canUpd.setEmail(request.email()!=null? request.email() : canUpd.getEmail());

            candidateRepo.save(canUpd);
        }
        else
        {
            System.out.println("ID doesn't exist");
        }

        return candidateRepo.findAll();
    }

    // @PostMapping("/filterbyName/{candidateName}")
    @PostMapping("/filterbyName")
    public List<Candidate> filterCandidatebyName(@RequestBody String name)
    {
        List<Candidate> canList = candidateRepo.findAll();
        List<Candidate> filterName = new ArrayList<>();

        for(Candidate a: canList)
        {
            if(a.getName().equals(name))
            {
                filterName.add(a);
            }
        }

        return filterName;
    }

    @PostMapping("/filterbyAge")
    public List<Candidate> filterCandidatebyAge(@RequestBody Integer age)
    {
        List<Candidate> canList = candidateRepo.findAll();
        List<Candidate> filterAge = new ArrayList<>();
        for(Candidate a: canList)
        {
            if(a.getAge().equals(age))
            {
                filterAge.add(a);
            }
        }

        return filterAge;
    }

    @PostMapping("/getID")
    public Integer getID(@RequestBody String name)
    {
        List<Candidate> canList = candidateRepo.findAll();
        Integer Id = 0;
        for(Candidate a: canList)
        {
            if(a.getName().equals(name))
            {
                Id = a.getId();
            }
        }
        return Id;
    }
}
