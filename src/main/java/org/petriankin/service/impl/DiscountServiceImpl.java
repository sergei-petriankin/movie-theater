package org.petriankin.service.impl;

import org.petriankin.domain.Event;
import org.petriankin.domain.User;
import org.petriankin.service.DiscountService;
import org.petriankin.service.discount.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final List<DiscountStrategy> discountStrategyList;

    @Autowired
    public DiscountServiceImpl(List<DiscountStrategy> discountStrategyList) {
        this.discountStrategyList = discountStrategyList;
    }

    /**
     * Getting discount based on some rules for user that buys some number of
     * tickets for the specific date time of the event
     *
     * @param user            User that buys tickets. Can be <code>null</code>
     * @param event           Event that tickets are bought for
     * @param airDateTime     The date and time event will be aired
     * @param numberOfTickets Number of tickets that user buys
     * @return discount value from 0 to 100
     */
    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        byte totalDiscount = 0;
        for (DiscountStrategy discountStrategy : discountStrategyList) {
            totalDiscount = (byte) Math.max(discountStrategy.getDiscount(user, event, airDateTime, numberOfTickets), totalDiscount);
        }
        return totalDiscount;
    }

}
