package org.petriankin.service.discount;

import org.petriankin.domain.Event;
import org.petriankin.domain.User;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Component
public class DiscountStrategyTenthTicket implements DiscountStrategy {
    @Override
    public int getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        int discount;
        if (user != null) {
            discount = ((user.getTickets().size() + numberOfTickets) % 10) > 0
                    ? 5
                    : 0;
        } else {
            discount = (numberOfTickets % 10) > 0
                    ? 5
                    : 0;
        }
        return discount;
    }
}
