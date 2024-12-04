package it.epicode.gestione_eventi.test;

import com.github.javafaker.Faker;
import it.epicode.gestione_eventi.dao.EventoDAO;
import it.epicode.gestione_eventi.dao.LocationDAO;
import it.epicode.gestione_eventi.dao.PartecipazioneDAO;
import it.epicode.gestione_eventi.dao.PersonaDAO;
import it.epicode.gestione_eventi.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainDAO {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");

        EntityManager em = emf.createEntityManager();

        Faker faker = new Faker(new Locale("it"));

        //Persona
        PersonaDAO personaDAO = new PersonaDAO(em);
        Persona persona = new Persona();
        persona.setNome("Andrea");
        persona.setCognome("La Ventura");
        persona.setEmail("andreafocoso@gmail.com");
        persona.setDataDiNascita(LocalDate.of(2003, 01, 15));
        persona.setSesso(SessoEnum.M);
//        persona.setListaPartecipazioni();

        personaDAO.insertPersona(persona);

        //Location
        LocationDAO locationDAO = new LocationDAO(em);
        Location location = new Location();

        location.setNome("Palasport");
        location.setCitta("Milano");

        locationDAO.insertLocation(location);

        //Evento
        EventoDAO eventoDAO = new EventoDAO(em);
        Evento evento = new Evento();
        evento.setTitolo("Epicode Party");
        evento.setDataEvento(LocalDate.of(2024, 12, 11));
        evento.setDescrizione("Super mega party epicode,sarà uno sballo!");
        evento.setTipoEvento(EventoEnum.PUBBLICO);
        evento.setNumeroMassimoPartecipanti(1000);
        evento.setLocation(location);

        eventoDAO.insertEvento(evento);

        //Partecipazione
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);
        Partecipazione partecipazione = new Partecipazione();

        partecipazione.setEvento(evento);
        partecipazione.setPersona(persona);
        partecipazione.setStato(StatoPartecipazioneEnum.CONFERMATA);

        partecipazioneDAO.insertPartecipazione(partecipazione);

//      evento.getListaPartecipazioni().add(partecipazione);
        evento.setListaPartecipazioni(Arrays.asList(partecipazione));

        em.close();
    }
}
