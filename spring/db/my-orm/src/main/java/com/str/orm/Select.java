package com.str.orm;

import java.util.Arrays;

/**
 * SELECT ... FROM ...
 *
 * Default to "*".
 * */
public final class Select extends CriteriaQuery {

    Select(Criteria criteria, String... selectFields) {
        super(criteria);
        if (selectFields.length > 0) {
            this.criteria.select = Arrays.asList(selectFields);
        }
    }

    /**
     * Add from clause.
     *
     * @param entityClass The entity class.
     * @return The criteria object.
     * */
    public <T> From<T> from(Class<T> entityClass) {
        return new From<T>(this.criteria, this.criteria.db.getMapper(entityClass));
    }
}
