/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import bean.Quartier;
import bean.Ville;
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
public class CompteFacadeTest {
    
    public CompteFacadeTest() {
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
     * Test of CreateCompte method, of class CompteFacade.
     */
    @Test
    public void testCreateCompte() {
        System.out.println("CreateCompte");
        String nom = "amine";
        String prenom = "mohamed";
        int tel = 04545454664;
        String email = "this dlfjd";
        
        CompteFacade instance =new CompteFacade();
        instance.createCompte(nom, prenom, tel, email, null, null);
      
    }

   
}
