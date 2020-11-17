package voluntariado.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import voluntariado.demo.models.Emergency;
import java.util.List;

@Repository
public class EmergencyImplementation implements EmergencyRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Emergency> getAllEmergency() {
        try(Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Emergency").executeAndFetch(Emergency.class);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergency createEmergency(Emergency emergency) {
        int idMax = 0;
        try(Connection conn =sql2o.open()) {
            idMax = conn.createQuery("SELECT MAX(id) FROM emergency").executeScalar(Integer.class)+1;
            conn.createQuery("INSERT INTO emergency(id,nombre,descrip,finicio,ffin,id_institucion) " + "VALUES (:id,:nombre,:descrip,:finicio,:ffin,:id_institucion)")
                    .addParameter("id",idMax)
                    .addParameter("nombre",emergency.getNombre())
                    .addParameter("descrip",emergency.getDescrip())
                    .addParameter("finicio",emergency.getFinicio())
                    .addParameter("ffin",emergency.getFfin())
                    .addParameter("id_institucion",emergency.getId_institucion())
                    .executeUpdate();
            emergency.setId(idMax);
            return emergency;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteEmergencyById(Integer id) {
        final String sql = "DELETE FROM emergency WHERE id = :id";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql).addParameter("id",id).executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Emergency getEmergencyById(Integer id) {
        final String sql="SELECT * FROM emergency where id = :id";
        try(Connection conn = sql2o.open()){
            Emergency emergency = conn.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Emergency.class);
            return emergency;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergency updateEmergencyById(Integer id, Emergency emergency) {
        final String sql = "UPDATE emergency SET nombre = :nombre,descrip = :descrip,finicio = :finicio,ffin = :ffin,id_institucion = :id_institucion WHERE id = :id ";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql,true)
                    .addParameter("nombre",emergency.getNombre())
                    .addParameter("descrip",emergency.getDescrip())
                    .addParameter("finicio",emergency.getFinicio())
                    .addParameter("ffin",emergency.getFfin())
                    .addParameter("id_institucion",emergency.getId_institucion())
                    .addParameter("id",id)
                    .executeUpdate();
            emergency.setId(id);
            return emergency;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
