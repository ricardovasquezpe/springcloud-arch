package com.revendedor.ticketservice.repository;

import com.revendedor.ticketservice.repository.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "SELECT t.* FROM tickets t WHERE t.code =:code", nativeQuery = true)
    Optional<Ticket> findTicketByCode(@Param("code") String code);
}
