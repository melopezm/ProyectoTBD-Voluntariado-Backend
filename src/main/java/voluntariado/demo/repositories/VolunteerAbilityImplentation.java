package voluntariado.demo.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import voluntariado.demo.models.TaskAbility;
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

    @Override
    public VolunteerAbility getVolunteerAbilityById(Integer id) {
        final String sql="SELECT * FROM volunteerAbility where id = :id";
        try(Connection con = sql2o.open()){
            VolunteerAbility volunteerAbility = con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(VolunteerAbility.class);
            return volunteerAbility;
        }
        catch (Exception e)
        {
            System.out.println( e.getMessage() );
            return null;
        }
    }

    @Override
    public void deleteVolunteerAbilityById(Integer id) {
        final String sql = "DELETE FROM volunteerAbility WHERE id = :id";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql).addParameter("id",id).executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public VolunteerAbility updateVolunteerAbilityById(Integer id, VolunteerAbility volunteerAbility) {
        final String sql = "UPDATE volunteerAbility SET id_voluntario = :id_voluntario,id_habilidad = :id_habilidad WHERE id = :id ";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql,true)
                    .addParameter("id_voluntario",volunteerAbility.getId_voluntario())
                    .addParameter("id_habilidad",volunteerAbility.getId_habilidad())
                    .addParameter("id",id).executeUpdate();
            volunteerAbility.setId(id);
            return volunteerAbility;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }











}

