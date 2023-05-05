package off;

import jakarta.persistence.EntityManager;
import model.JPAUtils;

public class App 
{
    public static void main( String[] args )
    {
	
        EntityManager em = JPAUtils.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close(); 
    }
}
