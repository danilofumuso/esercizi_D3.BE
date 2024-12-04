package it.epicode.gestione_eventi.test;

import com.github.javafaker.Faker;
import it.epicode.gestione_eventi.dao.EventoDAO;
import it.epicode.gestione_eventi.entity.Evento;
import it.epicode.gestione_eventi.entity.EventoEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Locale;

public class MainDAO {

    public static void main(String[] args) {

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("unit-jpa");

        EntityManager em= emf.createEntityManager();

        Faker faker= new Faker(new Locale("it"));

        EventoDAO eventoDAO= new EventoDAO(em);
        Evento evento= new Evento();
        evento.setTitolo("Epicode Party");
        evento.setDataEvento(LocalDate.of(2024,12,11));
        evento.setDescrizione("Super mega party epicode,sarà uno sballo!");
        evento.setTipoEvento(EventoEnum.PUBBLICO);
        evento.setNumeroMassimoPartecipanti(1000);

        eventoDAO.insertEvento(evento);

        em.close();
    }
}
