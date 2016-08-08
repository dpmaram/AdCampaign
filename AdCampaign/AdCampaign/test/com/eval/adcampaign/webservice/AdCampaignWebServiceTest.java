package com.eval.adcampaign.webservice;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.eval.adcampaign.service.AdCampaignService;
import com.eval.adcampaign.to.AdCampaign;

import junit.framework.TestCase;

public class AdCampaignWebServiceTest extends TestCase{

	@Test
	public void testGetAdCampaignService() {
        AdCampaignService adCompaignService = mock(AdCampaignService.class);
        AdCampaignWebService adCompaignWebService = new AdCampaignWebService(adCompaignService);
        assertEquals("Expected adCompaignService", adCompaignService, adCompaignWebService.getAdCampaignService());
	}

	@Test
	public void testSetAdCampaignService() {
        AdCampaignService adCompaignService = mock(AdCampaignService.class);
        AdCampaignWebService adCompaignWebService = new AdCampaignWebService(adCompaignService);
        
        assertEquals("Expected adCompaignService", adCompaignService, adCompaignWebService.getAdCampaignService());        
	}

	@Test
	public void testCreate() {
        AdCampaignService adCompaignService = mock(AdCampaignService.class);
        AdCampaignWebService adCompaignWebService = new AdCampaignWebService(adCompaignService);

        Response response = adCompaignWebService.create("{ \"partner_id\": \"101\", \"duration\": \"120\", \"ad_content\": \"Second Advertisement\" }");
        assertEquals("Expected a 200 response code.", 200, response.getStatus());
        assertEquals("SUCCESSFULL", response.getEntity().toString());
	}

	@Test
	public void testFindActive() {
        AdCampaignService adCompaignService = mock(AdCampaignService.class);
        AdCampaignWebService adCompaignWebService = new AdCampaignWebService(adCompaignService);
        
        Response response = adCompaignWebService.findActive("Patner_1");
        assertEquals("Expected a 200 response code.", 200, response.getStatus());
	}

	@Test
	public void testFind() {
        AdCampaignService adCompaignService = mock(AdCampaignService.class);
        AdCampaignWebService adCompaignWebService = new AdCampaignWebService(adCompaignService);
        
        final int expectedNumberOfAds = 1;
        List<AdCampaign> adsList = createSomeAds(expectedNumberOfAds);
        when(adCompaignService.findAllAdCampaign("Partner_1")).thenReturn(adsList);
        Response response = adCompaignWebService.find("Patner_1");
        assertEquals("Expected a 200 response code.", 200, response.getStatus());
	}

	@Test
	public void testFindAll() {
        AdCampaignService adCompaignService = mock(AdCampaignService.class);
        AdCampaignWebService adCompaignWebService = new AdCampaignWebService(adCompaignService);
        
        final int expectedNumberOfAds = 3;
        List<AdCampaign> adsList = createSomeAds(expectedNumberOfAds);
        when(adCompaignService.getAllAdCampaigns()).thenReturn(adsList);
        Response response = adCompaignWebService.findAll();
        assertEquals("Expected a 200 response code.", 200, response.getStatus());
	}

	@Test
	public void testDelete() {
        AdCampaignService adCompaignService = mock(AdCampaignService.class);
        AdCampaignWebService adCompaignWebService = new AdCampaignWebService(adCompaignService);
        
        Response response = adCompaignWebService.deleteAd("Patner_1");
        assertEquals("Expected a 200 response code.", 200, response.getStatus());
	}

	@Test
	public void testUpdate() {
        AdCampaignService adCompaignService = mock(AdCampaignService.class);
        AdCampaignWebService adCompaignWebService = new AdCampaignWebService(adCompaignService);
        
        Response response = adCompaignWebService.updateAd("{ \"partner_id\": \"101\", \"duration\": \"120\", \"ad_content\": \"Second Advertisement\" }");
        assertEquals("Expected a 200 response code.", 200, response.getStatus());
	}
	
    protected List<AdCampaign> createSomeAds(final int numberOfAds) {
        final List<AdCampaign> adsList = new ArrayList<AdCampaign>(numberOfAds);
        for (int i = 0; i < numberOfAds; i++) {
        	adsList.add(new AdCampaign("Partner_"+i, (i * 10) ,"", new Date()));
        }
        return adsList;
    }	

}
