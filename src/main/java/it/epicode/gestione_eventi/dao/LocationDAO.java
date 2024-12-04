package it.epicode.gestione_eventi.dao;

import it.epicode.gestione_eventi.entity.Location;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LocationDAO {

    private EntityManager em;

    public LocationDAO(EntityManager em) {
        this.em = em;
    }

    public void insertLocation(Location location) {
        em.getTransaction().begin();
        em.persist(location);
        em.getTransaction().commit();
    }

    public Location findLocationById(Long id) {
        return em.find(Location.class, id);
    }

    public void updateLocation(Location location) {
        em.getTransaction().begin();
        em.merge(location);
        em.getTransaction().commit();
    }

    public void updateLocation(Long id, Location location) {
        Location lReale = findLocationById(id);
        lReale.setNome(location.getNome());
        lReale.setCitta(location.getCitta());

        em.getTransaction().begin();
        em.merge(lReale);
        em.getTransaction().commit();
    }

    public void deleteLocation(Long id) {
        Location lReale = findLocationById(id);
        em.getTransaction().begin();
        em.remove(lReale);
        em.getTransaction().commit();
    }

    public void deleteLocation(Location location) {
        em.getTransaction().begin();
        em.remove(location);
        em.getTransaction().commit();
    }

    public void insertAll(List<Location> lista) {
        em.getTransaction().begin();
        for (Location e : lista) {
            em.persist(e);
        }
        em.getTransaction().commit();


    }
}
