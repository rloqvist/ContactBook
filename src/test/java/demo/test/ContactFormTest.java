package demo.test;

import demo.contacts.Contact;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rloqvist
 */
public class ContactFormTest {
    
    public ContactFormTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testNumGetters() throws IntrospectionException{
        
        List<PropertyDescriptor> descriptors = Arrays.asList(
                Introspector
                        .getBeanInfo(Contact.class)
                        .getPropertyDescriptors()
        );
        
        long numGetters = descriptors
                .stream()
                .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                .count();
        System.out.println(descriptors);
        Assert.assertEquals(4, numGetters);
    }

    @Test
    public void testAttributes() throws IntrospectionException{
        
        List<PropertyDescriptor> descriptors = Arrays.asList(
                Introspector
                        .getBeanInfo(Contact.class)
                        .getPropertyDescriptors()
        );
        
        List<String> attributes = descriptors
                .stream()
                .filter(pd -> Objects.nonNull(pd.attributeNames()))
                .map(pd -> pd.getName())
                .filter(name -> !name.equals("class"))
                .sorted()
                .collect(Collectors.toList());
        
        System.out.println(attributes);
        
        List<String> fakeAttributes = new ArrayList<>();
        fakeAttributes.add("cell");
        fakeAttributes.add("contactId");
        fakeAttributes.add("fullname");
        
        Assert.assertEquals(3, attributes.size());
        Assert.assertEquals(fakeAttributes, attributes);
    }
    
}
