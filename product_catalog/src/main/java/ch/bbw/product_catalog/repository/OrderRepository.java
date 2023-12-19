package ch.bbw.product_catalog.repository;

import ch.bbw.product_catalog.model.OrderPlacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderPlacement, Long> {
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrderPlacement o JOIN o.products p WHERE p.productId = :productId")
    boolean existsByProductId(@Param("productId") Long productId);
}
