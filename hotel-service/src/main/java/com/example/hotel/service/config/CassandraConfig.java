package com.example.hotel.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import java.util.Arrays;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String KEYSPACE;

    @Value("${spring.data.cassandra.contact-points}")
    private String CONTACT_POINT;

    @Value("${spring.data.cassandra.port}")
    private int PORT;

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    /**
     * This will create keyspace if not already exists
     */
    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE).ifNotExists();
        return Arrays.asList(specification);
    }

    /*
    This will drop the keyspace
     */
//    @Override
//    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
//        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
//    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    protected String getContactPoints() {
        return CONTACT_POINT;
    }

    @Override
    protected int getPort() {
        return PORT;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.example.hotel.service.entities"};
    }
}
