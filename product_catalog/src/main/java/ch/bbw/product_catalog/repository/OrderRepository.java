package ch.bbw.product_catalog.repository;

import ch.bbw.product_catalog.model.OrderPlacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderPlacement, Long> {}
