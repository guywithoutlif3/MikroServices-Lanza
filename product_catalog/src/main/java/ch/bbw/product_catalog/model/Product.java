package ch.bbw.product_catalog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "img_link", nullable = false)
    private String imgLink;
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "products")
    private Set<OrderPlacement> orders = new HashSet<>();
}
