/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Annonce;
import bean.AnnonceItem;
import bean.Quartier;
import bean.Ville;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lenovo
 */
public class AnnonceFacadeTest {
    
    public AnnonceFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class AnnonceFacade.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Annonce annonce = null;
        List<AnnonceItem> annonceItems = null;
        AnnonceFacade instance = new AnnonceFacade();
        instance.save(annonce, annonceItems);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of FindAnnonce method, of class AnnonceFacade.
     */
    @Test
    public void testFindAnnonce() {
        System.out.println("FindAnnonce");
        String titre = "";
        Ville ville = null;
        Quartier quartier = null;
        AnnonceFacade instance = new AnnonceFacade();
        List<Annonce> expResult = null;
        List<Annonce> result = instance.FindAnnonce(titre, ville, quartier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
