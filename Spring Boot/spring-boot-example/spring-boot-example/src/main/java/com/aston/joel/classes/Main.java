package com.aston.joel.classes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RestController
//@RequestMapping("/candidates")
public class Main {

//    private final CandidateRepo candidateRepo;
//
//    public Main(CandidateRepo candidateRepo) {
//        this.candidateRepo = candidateRepo;
//    }

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

//    @GetMapping
//    public List<Candidate> getCandidate()
//    {
//        return candidateRepo.findAll();
//    }
//
//    record AddCandidatereq(String name, Integer age, String email){}
//    @PostMapping
//    public List<Candidate> addCandidate(@RequestBody AddCandidatereq request)
//    {
//        Candidate can = new Candidate();
//        can.setName(request.name());
//        can.setEmail(request.email());
//        can.setAge(request.age());
//
//        candidateRepo.save(can);
//
//        return candidateRepo.findAll();
//    }
//
//    @DeleteMapping("{candidateId}")
//    public List<Candidate> deleteCandidate(@PathVariable("candidateId") Integer id)
//    {
//        candidateRepo.deleteById(id);
//        return candidateRepo.findAll();
//    }
//
//    @PostMapping("{candidateId}")
//    public List<Candidate> updateCandidate(@PathVariable("candidateId") Integer id, @RequestBody AddCandidatereq request)
//    {
//        if(candidateRepo.existsById(id))
//        {
//            Candidate canUpd = candidateRepo.getReferenceById(id);
//            canUpd.setAge(request.age()!=null? request.age() : canUpd.getAge());
//            canUpd.setName(request.name()!=null? request.name() : canUpd.getName());
//            canUpd.setEmail(request.email()!=null? request.email() : canUpd.getEmail());
//
//            candidateRepo.save(canUpd);
//        }
//        else
//        {
//            System.out.println("ID doesn't exist");
//        }
//
//        return candidateRepo.findAll();
//    }
//
//   // @PostMapping("/filterbyName/{candidateName}")
//   @PostMapping("/filterbyName")
//   public List<Candidate> filterCandidatebyName(@RequestBody String name)
//    {
//        List<Candidate> canList = candidateRepo.findAll();
//        List<Candidate> filterName = new ArrayList<>();
//
//        for(Candidate a: canList)
//        {
//            if(a.getName().equals(name))
//            {
//                filterName.add(a);
//            }
//        }
//
//        return filterName;
//    }
//
//    @PostMapping("/filterbyAge")
//    public List<Candidate> filterCandidatebyAge(@RequestBody Integer age)
//    {
//        List<Candidate> canList = candidateRepo.findAll();
//        List<Candidate> filterAge = new ArrayList<>();
//        for(Candidate a: canList)
//        {
//            if(a.getAge().equals(age))
//            {
//                filterAge.add(a);
//            }
//        }
//
//        return filterAge;
//    }
//
//    @PostMapping("/getID")
//    public Integer getID(@RequestBody String name)
//    {
//        List<Candidate> canList = candidateRepo.findAll();
//        Integer Id = 0;
//        for(Candidate a: canList)
//        {
//            if(a.getName().equals(name))
//            {
//                Id = a.getId();
//            }
//        }
//        return Id;
//    }
//    @GetMapping( "/")
//    public WelcomeMessage welcome()
//    {
//        WelcomeMessage w1 = new WelcomeMessage("Hola",
//                List.of("Pizza","Pasta"),
//                new Person("Pablo", 34, 45000));
//
//        return w1;
//    }
//
//    @GetMapping( "/nextPage")
//    public WelcomeMessage giveNum()
//    {
//        WelcomeMessage w2 = new WelcomeMessage("Hello",
//                List.of("Dosa","Chinese Noodles"),
//                new Person("Joel", 29, 45000));
//
//        return w2;
//    }
//
//    record Person(String name, int age, double salary){}
//    record WelcomeMessage(String welcome, List<String> food, Person person){}
}
