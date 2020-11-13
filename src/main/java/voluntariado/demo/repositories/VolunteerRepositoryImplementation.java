package voluntariado.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.converters.IntegerConverter;
import voluntariado.demo.models.Volunteer;

import java.util.List;
import java.util.Optional;

@Repository
public class VolunteerRepositoryImplementation implements VolunteerRepository {

    @Autowired
    private Sql2o sql2o;


    @Override
    public List<Volunteer> getAllVolunteer() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM voluntario").executeAndFetch(Volunteer.class);
        }
        catch (Exception e)
        {
            System.out.println( e.getMessage() );
            return null;
        }
    }

    @Override
    public Volunteer getVolunteerById(Integer id) {
        final String sql="SELECT * FROM voluntario where id = :id";
        try(Connection con = sql2o.open()){
            Volunteer volunteer = con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Volunteer.class);
            return volunteer;
        }
        catch (Exception e)
        {
            System.out.println( e.getMessage() );
            return null;
        }
    }

}
