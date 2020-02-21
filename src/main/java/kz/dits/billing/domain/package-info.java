/**
 * JPA domain objects.
 */
@TypeDef(
        name = "pg-uuid",
        defaultForType = UUID.class,
        typeClass = PostgresUUIDType.class
)
package kz.dits.billing.domain;

import org.hibernate.annotations.TypeDef;
import org.hibernate.type.PostgresUUIDType;

import java.util.UUID;
