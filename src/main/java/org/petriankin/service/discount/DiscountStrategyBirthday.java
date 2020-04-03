package org.petriankin.service.discount;

import org.petriankin.domain.Event;
import org.petriankin.domain.User;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Component
public class DiscountStrategyBirthday implements DiscountStrategy {
    @Override
    public int getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        boolean birthdayDiscount = false;
        if (user != null) {
            birthdayDiscount = (user.getDateOfBirth().getMonthValue() == airDateTime.getMonthValue())
                    && (Math.abs(user.getDateOfBirth().getDayOfMonth() - airDateTime.getDayOfMonth()) <= 5);
        }
        return birthdayDiscount
                ? 10
                : 0;
    }
}
