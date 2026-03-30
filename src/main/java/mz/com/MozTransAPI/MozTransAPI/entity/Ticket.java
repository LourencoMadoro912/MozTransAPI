package mz.com.MozTransAPI.MozTransAPI.entity;

import jakarta.persistence.*;

public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String assento;

    @ManyToOne
    @JoinColumn(name="trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name="costumer_id")
    private Costumer costumer;
}
