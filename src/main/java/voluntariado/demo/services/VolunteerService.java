package voluntariado.demo.services;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import voluntariado.demo.repositories.VolunteerRepository;

@CrossOrigin
@RestController
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @GetMapping("/volunteer/count")
    public String countVolunteer(){
        int total = volunteerRepository.countVolunteer();
        return String.format("La cantidad de voluntarios es: %s",total);
    }
}
