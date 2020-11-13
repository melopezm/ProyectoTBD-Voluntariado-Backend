package voluntariado.demo.services;

import org.springframework.web.bind.annotation.*;
import voluntariado.demo.models.Volunteer;
import voluntariado.demo.repositories.VolunteerRepository;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @GetMapping("/volunteers")
    public List<Volunteer> getAllVolunteer(){

        return volunteerRepository.getAllVolunteer();
    }

    @GetMapping("/volunteers/{id}")
    public Volunteer getVolunteerById(@PathVariable("id") Integer id){
        return volunteerRepository.getVolunteerById(id);
    }

}
