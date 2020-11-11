package voluntariado.demo.services;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import voluntariado.demo.repositories.InstitutionRepository;
import voluntariado.demo.models.Institution;

import java.util.List;

@CrossOrigin
@RestController
public class InstitutionService
{
    private final InstitutionRepository insRepo;
    InstitutionService ( InstitutionRepository insRepo )
    {
        this.insRepo = insRepo;
    }

    @GetMapping( "/institution/count" )
    public String countInstitution()
    {
        int count = insRepo.countInstitution();
        return String.format( "Hay %s instituciones", count );
    }

    @GetMapping( "/institution/all" )
    public List<Institution> getAllInstitution()
    {
        return insRepo.getAllInstitution();
    }
}
