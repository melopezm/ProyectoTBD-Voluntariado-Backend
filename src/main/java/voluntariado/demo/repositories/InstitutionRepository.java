package voluntariado.demo.repositories;

import voluntariado.demo.models.Institution;
import java.util.List;

public interface InstitutionRepository
{
    public int countInstitution();
    public List<Institution> getAllInstitution();
}