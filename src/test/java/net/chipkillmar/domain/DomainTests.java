package net.chipkillmar.domain;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static net.chipkillmar.domain.State.TEXAS;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class DomainTests {
    private YAMLFactory yamlFactory = new YAMLFactory();

    private ObjectMapper objectMapper = new ObjectMapper(yamlFactory);

    @Test
    public void testDeserializeCustomer() throws IOException {
        String yaml = Resources.toString(Resources.getResource("customer.yml"), UTF_8);
        Customer customer = objectMapper.readValue(yaml, Customer.class);
        assertThat(customer.getFirstName()).isEqualTo("Bruce");
        assertThat(customer.getLastName()).isEqualTo("Wayne");
        assertThat(customer.getEmailAddress()).isEqualTo("bwayne@example.com");
        Address address = customer.getAddress();
        assertThat(address.getStreet()).isEqualTo("604 Brazos St.");
        assertThat(address.getCity()).isEqualTo("Austin");
        assertThat(address.getState()).isEqualTo(TEXAS);
        assertThat(address.getZipCode()).isEqualTo(78701);
    }

    @Test
    public void testDeserializeCustomers() throws IOException {
        String yaml = Resources.toString(Resources.getResource("customers.yml"), UTF_8);
        // This doesn't work!
        // List<Customer> customers = objectMapper.readValue(yaml, new TypeReference<List<Customer>>() {});
        MappingIterator<Customer> iterator = objectMapper.readValues(yamlFactory.createParser(yaml), Customer.class);
        Customer customer = iterator.next();
        assertThat(customer.getFirstName()).isEqualTo("Bruce");
        customer = iterator.next();
        assertThat(customer.getFirstName()).isEqualTo("Clark");
        customer = iterator.next();
        assertThat(customer.getFirstName()).isEqualTo("Diana");
        assertThat(iterator.hasNext()).isFalse();
    }
}
