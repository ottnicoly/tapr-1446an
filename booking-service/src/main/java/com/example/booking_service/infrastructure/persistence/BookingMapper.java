package com.example.booking_service.infrastructure.persistence;

import com.example.booking_service.domain.Booking;
import com.example.booking_service.domain.vo.BookingId;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    /**
     * Converte de Booking (domain) para BookingEntity (JPA)
     */
    public BookingEntity toEntity(Booking booking) {
        if (booking == null) {
            return null;
        }

        return new BookingEntity(
                booking.getId().value(),
                booking.getUserId(),
                booking.getRoomId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getRoomType(),
                booking.getNumberOfPeople()
        );
    }

    /**
     * Converte de BookingEntity (JPA) para Booking (domain)
     */
    public Booking toDomain(BookingEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Booking(
                new BookingId(entity.getId()),
                entity.getUserId(),
                entity.getRoomId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getRoomType(),
                entity.getNumberOfPeople()
        );
    }
}
