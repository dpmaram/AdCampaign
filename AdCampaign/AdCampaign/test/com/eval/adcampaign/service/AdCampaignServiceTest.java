package com.eval.adcampaign.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.eval.adcampaign.dao.AdCampaignDAO;
import com.eval.adcampaign.to.AdCampaign;
import com.eval.adcampaign.webservice.AdCampaignWebService;

public class AdCampaignServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllAdCampaigns() {
        AdCampaignDAO adCompaignDAO = mock(AdCampaignDAO.class);
        AdCampaignService adCompaignService = new AdCampaignService(adCompaignDAO);
        
        final int expectedNumberOfAds = 3;
        List<AdCampaign> adsList = createSomeAds(expectedNumberOfAds);
        when(adCompaignDAO.getAllAdCampaings()).thenReturn(adsList);
        
        assertEquals("Return size should be 3", 3, adCompaignService.getAllAdCampaigns().size());
	}

	@Test
	public void testFindAllAdCampaign() {
        AdCampaignDAO adCompaignDAO = mock(AdCampaignDAO.class);
        AdCampaignService adCompaignService = new AdCampaignService(adCompaignDAO);
        
        final int expectedNumberOfAds = 1;
        List<AdCampaign> adsList = createSomeAds(expectedNumberOfAds);
        when(adCompaignDAO.findByPartnerId("Partner_1")).thenReturn(adsList);
        
        assertEquals("Return size should be 1", 1, adCompaignService.findAllAdCampaign("Partner_1").size());
	}

	@Test
	public void testFindActiveAdCampaign() {
        AdCampaignDAO adCompaignDAO = mock(AdCampaignDAO.class);
        AdCampaignService adCompaignService = new AdCampaignService(adCompaignDAO);
        
        final int expectedNumberOfAds = 3;
        List<AdCampaign> adsList = createSomeAds(expectedNumberOfAds);
        when(adCompaignDAO.getAllAdCampaings()).thenReturn(adsList);
        
        assertEquals("Return size should be 1", "Partner_1", ((AdCampaign)adCompaignService.findActiveAdCampaign("Partner_1")).getPartnerId());
	}

	@Test
	public void testAddAdCampaign_Success() {
        AdCampaignDAO adCompaignDAO = mock(AdCampaignDAO.class);
        AdCampaignService adCompaignService = new AdCampaignService(adCompaignDAO);
        AdCampaign adCampaign = new AdCampaign("Partner_4", 10, "Ad1", new Date());
        
        final int expectedNumberOfAds = 3;
        List<AdCampaign> adsList = createSomeAds(expectedNumberOfAds);
        when(adCompaignDAO.getAllAdCampaings()).thenReturn(adsList);
        when(adCompaignDAO.create(adCampaign)).thenReturn(0);
        
        assertEquals("Return Value should be 0", 0, adCompaignService.addAdCampaign(adCampaign));
	}
	
	@Test
	public void testAddAdCampaign_AlreadyExists() {
        AdCampaignDAO adCompaignDAO = mock(AdCampaignDAO.class);
        AdCampaignService adCompaignService = new AdCampaignService(adCompaignDAO);
        AdCampaign adCampaign = new AdCampaign("Partner_1", 10, "Ad1", new Date());
        
        final int expectedNumberOfAds = 3;
        List<AdCampaign> adsList = createSomeAds(expectedNumberOfAds);
        when(adCompaignDAO.getAllAdCampaings()).thenReturn(adsList);
        
        assertEquals("Return Value should be 1", 1, adCompaignService.addAdCampaign(adCampaign));
	}	

	@Test
	public void testUpdateAdCampaign() {
        AdCampaignDAO adCompaignDAO = mock(AdCampaignDAO.class);
        AdCampaignService adCompaignService = new AdCampaignService(adCompaignDAO);
        AdCampaign adCampaign = new AdCampaign("Partner_1", 10, "Ad1", new Date());
        
        when(adCompaignDAO.update(adCampaign)).thenReturn(0);        
        assertEquals("Return Value should be 0", 0, adCompaignService.updateAdCampaign(adCampaign));

	}

	@Test
	public void testDeleteAdCampaign() {
        AdCampaignDAO adCompaignDAO = mock(AdCampaignDAO.class);
        AdCampaignService adCompaignService = new AdCampaignService(adCompaignDAO);
        
        when(adCompaignDAO.delete("Partner_1")).thenReturn(0);        
        assertEquals("Return Value should be 0", 0, adCompaignService.deleteAdCampaign("Partner_1"));
	}
	
    protected List<AdCampaign> createSomeAds(final int numberOfAds) {
        final List<AdCampaign> adsList = new ArrayList<AdCampaign>(numberOfAds);
        for (int i = 0; i < numberOfAds; i++) {
        	adsList.add(new AdCampaign("Partner_"+i, 1000 ,"", new Date()));
        }
        return adsList;
    }	

}
