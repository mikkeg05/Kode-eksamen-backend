package facades;

import dtos.WashingAssistantDTO;
import entities.WashingAssistant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class  WashingAssistantFacade {

    private static WashingAssistantFacade instance;
    private static EntityManagerFactory emf;

    private WashingAssistantFacade() {}


    public static WashingAssistantFacade getWashingAssistantFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new WashingAssistantFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



    public List<WashingAssistantDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<WashingAssistant> query = em.createQuery("SELECT w FROM WashingAssistant w", WashingAssistant.class);
        List<WashingAssistant> washE = query.getResultList();
        return WashingAssistantDTO.getDTOs(washE);
    }

    public WashingAssistantDTO createWashingAssistant(WashingAssistantDTO washDTO){
        EntityManager em = emf.createEntityManager();
        WashingAssistant washingAssistant = new WashingAssistant(washDTO.getName(), washDTO.getPrimarylanguage(), washDTO.getYearsofexp(), washDTO.getPriceprhour());

        try{
            em.getTransaction().begin();
            em.persist(washingAssistant);
            em.getTransaction().commit();
            return new WashingAssistantDTO(washingAssistant);
        } finally{
            em.close();
        }
    }

}