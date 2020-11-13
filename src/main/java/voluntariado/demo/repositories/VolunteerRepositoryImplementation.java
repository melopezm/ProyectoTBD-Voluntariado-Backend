package voluntariado.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import voluntariado.demo.models.Volunteer;

import java.sql.Date;
import java.util.List;

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

    @Override
    public void deleteVolunteerById(Integer id) {
        final String sql = "DELETE FROM voluntario WHERE id = :id";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql).addParameter("id",id).executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Volunteer updateVolunteerById(Integer id, Volunteer volunteer) {
        System.out.println(volunteer.getFnacimiento());
        final String sql = "UPDATE voluntario SET nombre = :nombre,fnacimiento = :fnacimiento,correo_electronico = :ce, celular = :celu WHERE id = :id ";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql,true)
                    .addParameter("nombre",volunteer.getNombre())
                    .addParameter("fnacimiento",volunteer.getFnacimiento())
                    .addParameter("ce",volunteer.getCorreo_electronico())
                    .addParameter("celu",volunteer.getCelular())
                    .addParameter("id",id)
                    .executeUpdate();
            volunteer.setId(id);
            return volunteer;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Volunteer createVolunteer(Volunteer volunteer) {
        int idMax = 0;
        try(Connection conn =sql2o.open()){
            idMax = conn.createQuery("SELECT MAX(id) FROM voluntario").executeScalar(Integer.class)+1;
            conn.createQuery("INSERT INTO voluntario(id,nombre,fnacimiento,correo_electronico,celular) " +
                    "VALUES (:id,:nombre,:fnacimiento,:co,:celular)")
                    .addParameter("id",idMax)
                    .addParameter("nombre",volunteer.getNombre())
                    .addParameter("fnacimiento",volunteer.getFnacimiento())
                    .addParameter("co",volunteer.getCorreo_electronico())
                    .addParameter("celular",volunteer.getCelular()).executeUpdate();
            volunteer.setId(idMax);
            return volunteer;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}