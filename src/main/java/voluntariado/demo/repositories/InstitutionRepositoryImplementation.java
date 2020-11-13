package voluntariado.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import voluntariado.demo.models.Institution;

import java.util.List;

@Repository
public class InstitutionRepositoryImplementation implements InstitutionRepository
{
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countInstitution()
    {
        try ( Connection conn = sql2o.open() )
        {
            return conn.createQuery( "SELECT COUNT(*) FROM institucion" ).executeScalar( Integer.class );
        }

        catch ( Exception e )
        {
            System.out.println( e.getMessage() );
            return -1;
        }
    }

    @Override
    public List<Institution> getAllInstitution()
    {
        try ( Connection conn = sql2o.open() )
        {
            return conn.createQuery( "SELECT * FROM institucion" ).executeAndFetch( Institution.class );
        }

        catch (Exception e)
        {
            System.out.println( e.getMessage() );
            return null;
        }
    }
}