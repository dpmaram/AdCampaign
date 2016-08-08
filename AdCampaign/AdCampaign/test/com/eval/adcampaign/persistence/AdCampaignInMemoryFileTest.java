package com.eval.adcampaign.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.eval.adcampaign.to.AdCampaign;

public class AdCampaignInMemoryFileTest {
	private String dataFileName = "c:\\temp\\testAdCampaigns.dat";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File file = new File("c:\\temp\\testAdCampaigns.dat");
		if (file.exists()) file.delete();		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		File file = new File(dataFileName);
		if (file.exists()) file.delete();
	}


	@Test
	public void testFindAllAdCampaign_Empty() {
        AdCampaignInMemoryFile inMemory = new AdCampaignInMemoryFile();
        inMemory.setDataFileName(dataFileName);        
        assertEquals("Expected No Records Empty file", 0, inMemory.getAllAdCampaigns().size());
	}

	@Test
	public void testCreate() {
        AdCampaignInMemoryFile inMemory = new AdCampaignInMemoryFile();
        inMemory.setDataFileName(dataFileName);        
        AdCampaign adCampaign = new AdCampaign("Partner_1", 10, "Ad1", new Date());
        assertEquals("Expected No Records Empty file", 0, inMemory.create(adCampaign));
	}
	/*
	@Test
	public void testGetAllAdCampaigns_AfterAdding() {
        AdCampaignInMemoryFile inMemory = new AdCampaignInMemoryFile();
        inMemory.setDataFileName(dataFileName);        
        AdCampaign adCampaign = new AdCampaign("Partner_1", 10, "Ad1", new Date());
        assertEquals("Expected No Records Empty file", 0, inMemory.create(adCampaign));
        assertEquals("Expected 1 record in a file", 1, inMemory.getAllAdCampaigns().size());
	}
	*/

	@Test
	public void testUpdateAdCampaign() {
        AdCampaignInMemoryFile inMemory = new AdCampaignInMemoryFile();
        inMemory.setDataFileName(dataFileName);        
        AdCampaign adCampaign = new AdCampaign("Partner_1", 10, "Update", new Date());
        assertEquals("Update test failed", 0, inMemory.updateAdCampaign(adCampaign));
	}

	@Test
	public void testDeleteAdCampaign() {
        AdCampaignInMemoryFile inMemory = new AdCampaignInMemoryFile();
        inMemory.setDataFileName(dataFileName);        
        assertEquals("Delete Test failed", 0, inMemory.deleteAdCampaign("Partner_1"));
	}
	
    protected List<AdCampaign> createSomeAds(final int numberOfAds) {
        final List<AdCampaign> adsList = new ArrayList<AdCampaign>(numberOfAds);
        for (int i = 0; i < numberOfAds; i++) {
        	adsList.add(new AdCampaign("Partner_"+i, (i * 10) ,"", new Date()));
        }
        return adsList;
    }	

}
