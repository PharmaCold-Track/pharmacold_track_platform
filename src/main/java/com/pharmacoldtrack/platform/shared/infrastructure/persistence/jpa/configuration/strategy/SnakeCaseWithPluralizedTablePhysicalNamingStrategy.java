package com.pharmacoldtrack.platform.shared.infrastructure.persistence.jpa.configuration.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static com.pharmacoldtrack.platform.shared.domain.utils.StringUtils.toSnakeCase;
import static com.pharmacoldtrack.platform.shared.domain.utils.StringUtils.pluralize;

public class SnakeCaseWithPluralizedTablePhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        if (name == null) {
            return null;
        }
        final String newName = toSnakeCase(pluralize(name.getText()));
        return Identifier.toIdentifier(newName);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        if (name == null) {
            return null;
        }
        return Identifier.toIdentifier(toSnakeCase(name.getText()));
    }
}