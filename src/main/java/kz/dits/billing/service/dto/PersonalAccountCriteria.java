package kz.dits.billing.service.dto;

import kz.dits.billing.service.filter.BigDecimalFilter;
import kz.dits.billing.service.filter.Filter;
import kz.dits.billing.service.filter.InstantFilter;
import kz.dits.billing.service.filter.LongFilter;
import kz.dits.billing.service.filter.StringFilter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * Criteria class for the PersonalAccount entity. This class is used in PersonalAccountResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /personal-accounts?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@Data
@NoArgsConstructor
public class PersonalAccountCriteria implements Serializable {
    private static final long serialVersionUID = 1L;

    private StringFilter accountNumber;
    private StringFilter contractNumber;
    private StringFilter ownerXin;
    private LongFilter ownerId;
    private StringFilter ownerCompanyName;
    private StringFilter ownerLastName;
    private StringFilter ownerFirstName;
    private StringFilter ownerMiddleName;
    private BigDecimalFilter balance;
    private InstantFilter registrationDate;
    private InstantFilter expirationDate;
}
