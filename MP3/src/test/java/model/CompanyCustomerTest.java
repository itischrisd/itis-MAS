package model;

import model.company.Company;
import model.company.CorporateCustomer;
import model.company.Customer;
import model.company.ICustomer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CompanyCustomerTest {

    @Test
    void canCreateCorporateCustomer() {
        CorporateCustomer corporateCustomer = new CorporateCustomer("companyName", "Koszykowa 86", "1234567890", "test@mail.com", null);
        assertNotNull(corporateCustomer);
        assertInstanceOf(CorporateCustomer.class, corporateCustomer);
        assertInstanceOf(ICustomer.class, corporateCustomer);
        assertInstanceOf(Company.class, corporateCustomer);
    }

    @Test
    void canCreateCustomer() {
        Customer customer = new Customer("Koszykowa 86", "test@mail.com");
        assertNotNull(customer);
        assertInstanceOf(Customer.class, customer);
        assertInstanceOf(ICustomer.class, customer);
    }
}
