package voluntariado.demo.repositories;

import org.sql2o.converters.IntegerConverter;
import voluntariado.demo.models.Volunteer;

import java.util.List;
import java.util.Optional;

public interface VolunteerRepository {
    public List<Volunteer> getAllVolunteer();
    public Volunteer getVolunteerById(Integer id);
}
