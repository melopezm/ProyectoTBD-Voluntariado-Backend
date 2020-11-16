package voluntariado.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import voluntariado.demo.models.Emergency;
import voluntariado.demo.models.TaskAbility;

import java.util.List;

@Repository
public class EmergencyImplementation implements EmergencyRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Emergency> getAllEmergency() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM Emergency").executeAndFetch(Emergency.class);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergency createEmergency(Emergency emergency) {
        return null;
    }

    @Override
    public void deleteEmergencyById(Integer id) {

    }

    @Override
    public Emergency getEmergencyById(Integer id) {
        return null;
    }

    @Override
    public Emergency updateEmergencyById(Integer id, Emergency emergency) {
        return null;
    }
}
