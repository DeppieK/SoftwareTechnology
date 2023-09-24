package com.example.demo.orders;

import jakarta.persistence.*;

@Entity
@Table
public class Orders {
    @Id
    @SequenceGenerator(
            name = "orders_sequence",
            sequenceName = "orders_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_sequence"
    )
    private Long id;
    private int totalcost;

    public Orders() {
    }

    public Orders(Long id, int totalcost) {
        this.id = id;
        this.totalcost = totalcost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(int totalcost) {
        this.totalcost = totalcost;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", totalcost=" + totalcost +
                '}';
    }
}