package com.micorservices.inventory_service.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_inventorys")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
