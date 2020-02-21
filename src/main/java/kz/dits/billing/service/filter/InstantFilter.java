package kz.dits.billing.service.filter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.Instant;

/**
 * Filter class for {@link Instant} type attributes.
 *
 * @see RangeFilter
 */
public class InstantFilter extends RangeFilter<Instant> {

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setEquals(Instant equals) {
        super.setEquals(equals);
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setGreaterThan(Instant equals) {
        super.setGreaterThan(equals);
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setGreaterOrEqualThan(Instant equals) {
        super.setGreaterOrEqualThan(equals);
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setLessThan(Instant equals) {
        super.setLessThan(equals);
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public void setLessOrEqualThan(Instant equals) {
        super.setLessOrEqualThan(equals);
    }

}
