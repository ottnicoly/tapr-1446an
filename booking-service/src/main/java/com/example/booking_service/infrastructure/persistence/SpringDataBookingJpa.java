package com.example.booking_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataBookingJpa extends JpaRepository<BookingEntity, UUID> {

    /**
     * Busca todas as reservas de uma sala específica
     */
    List<BookingEntity> findByRoomId(String roomId);

    /**
     * Busca todas as reservas de um usuário específico
     */
    List<BookingEntity> findByUserId(UUID userId);

    /**
     * Busca todas as reservas de uma sala em um determinado período
     * Útil para verificar conflitos de horário
     */
    @Query("SELECT b FROM BookingEntity b WHERE b.roomId = :roomId " +
           "AND ((b.startTime < :endTime AND b.endTime > :startTime))")
    List<BookingEntity> findConflictingBookings(
            @Param("roomId") String roomId,
            @Param("startTime") java.time.LocalDateTime startTime,
            @Param("endTime") java.time.LocalDateTime endTime
    );
}
