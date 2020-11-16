package voluntariado.demo.repositories;

import voluntariado.demo.models.VolunteerAbility;

import java.util.List;

public interface VolunteerAbilityRepository {
    public List<VolunteerAbility> getAllVolunteerAbility();
    public VolunteerAbility getVolunteerAbilityById(Integer id);
}
