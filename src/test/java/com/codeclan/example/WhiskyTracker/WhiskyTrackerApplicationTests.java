package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindWhiskyByYear(){
		assertEquals("The Glendronach Revival", whiskyRepository.findByYear(2018).get(0).getName());
	}

//	Whisky whisky1 = new Whisky("The Glendronach Revival", 15, 2018, distillery1);
//        whiskyRepository.save(whisky1);

	@Test
	public void canFindDistilleryByRegion(){
		assertEquals(3, distilleryRepository.getByRegion("Highland").size());
	}

	@Test
	public void canFindWhiskyByDistilleryAndAge(){
		Distillery distillery55 = new Distillery("Highland Park", "Island");
		Whisky whisky55 = new Whisky("Viking Honour", 12, 2017, distillery55);

		assertEquals("Viking Honour", whiskyRepository.findByDistilleryNameAndAge("Highland Park", 12).get(0).getName());
	}

	@Test
	public void canFindWhiskyByAge(){
		assertEquals(6, whiskyRepository.findByAge(12).size());
	}

	@Test
	public void canFindWhiskyByDistillery(){
		List<Whisky> foundWhisky = whiskyRepository.findByDistilleryName("Glendronach");
		assertEquals(2, foundWhisky.size());
	}
}
