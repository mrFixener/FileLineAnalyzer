/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Dimon
 */
public class NewJUnitTest {
    
    public NewJUnitTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    //@Ignore
    @Test
    public void patternAnyExtensionTest(){
        Pattern pattern = Pattern.compile(".*");
        assertTrue(pattern.matcher("someFile.txt").matches());
    }
    
}
