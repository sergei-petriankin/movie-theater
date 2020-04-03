package org.petriankin.domain;

import lombok.*;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
public class Auditorium {

    private String name;
    private long numberOfSeats;
    private Set<Long> vipSeats;

    /**
     * Counts how many vip seats are there in supplied <code>seats</code>
     *
     * @param seats Seats to process
     * @return number of vip seats in request
     */
    public long countVipSeats(Collection<Long> seats) {
        return seats.stream()
                .filter(seat -> vipSeats.contains(seat))
                .count();
    }

    public Set<Long> getAllSeats() {
        return LongStream.range(1, numberOfSeats + 1)
                .boxed()
                .collect(Collectors.toSet());
    }
}
