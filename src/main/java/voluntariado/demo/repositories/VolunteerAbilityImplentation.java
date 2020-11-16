package voluntariado.demo.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import voluntariado.demo.models.VolunteerAbility;

import java.util.List;

@Repository
public class VolunteerAbilityImplentation implements VolunteerAbilityRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<VolunteerAbility> getAllVolunteerAbility(){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM volunteerAbility").executeAndFetch(VolunteerAbility.class);
        }
        catch (Exception e)
        {
            System.out.println( e.getMessage() );
            return null;
        }
    }








}

