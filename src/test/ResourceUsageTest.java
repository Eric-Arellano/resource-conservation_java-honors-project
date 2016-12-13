package test;

import base.ResourceUsage;
import base.UsageDiscreteQuantity;
import base.UsageTimeDuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class ResourceUsageTest {

	public static class DiscreteQuantity {

		private ResourceUsage quantityUsage;

		@Before
		public void setupMockResources() {
			quantityUsage = new UsageDiscreteQuantity("washing machine",
					25,
					"times per month",
					"gallons",
					"src/test/mockTips.txt",
					4,
					3, 6, 5, 5);
		}

		@Test
		public void promptDiscreteInput() {
			String expectedPrompt = "How many times per month did you use the washing machine? ";
			String returnedPrompt = quantityUsage.promptInput();
			assertEquals(expectedPrompt, returnedPrompt);
		}

		@Test
		public void displayDiscreteUsage() {
			String expectedMessage = "That means you used 25 gallons.";
			quantityUsage.implementInput(1);
			quantityUsage.setUsageFromInput();
			String returnedMessage = quantityUsage.returnUsage();
			assertEquals(expectedMessage, returnedMessage);
		}
	}


	public static class TimeDurationSeconds {

		private ResourceUsage secondUsage;

		@Before
		public void setupMockResources() {
			secondUsage = new UsageTimeDuration("sink",
					1.5,
					UsageTimeDuration.TimeType.SECONDS,
					"gallons",
					"src/test/mockTips.txt",
					0.35,
					0.2, 0.6, 0.5, 0.9);
		}

		@Test
		public void promptTimeInput_Seconds() {
			String expectedPrompt = "How many seconds did you use the sink? ";
			String returnedPrompt = secondUsage.promptInput();
			assertEquals(expectedPrompt, returnedPrompt);
		}

		@Test
		public void displayTimeUsage_Seconds() {
			String expectedMessage = "That means you used 1.5 gallons.";
			secondUsage.implementInput(60);
			secondUsage.setUsageFromInput();
			String returnedMessage = secondUsage.returnUsage();
			assertEquals(expectedMessage, returnedMessage);
		}
	}

	public static class TimeDurationMinutes {

		private ResourceUsage minuteUsage;

		@Before
		public void setupMockResources() {
			minuteUsage = new UsageTimeDuration("shower",
					2.5,
					UsageTimeDuration.TimeType.MINUTES,
					"gallons",
					"src/test/mockTips.txt",
					10.6,
					8.3, 9.2, 12.4, 18.1);
		}

		@Test
		public void promptTimeInput_Minutes() {
			String expectedPrompt = "How many minutes did you use the shower? ";
			String returnedPrompt = minuteUsage.promptInput();
			assertEquals(expectedPrompt, returnedPrompt);
		}

		@Test
		public void displayTimeUsage_Minutes() {
			String expectedMessage = "That means you used 2.5 gallons.";
			minuteUsage.implementInput(1);
			minuteUsage.setUsageFromInput();
			String returnedMessage = minuteUsage.returnUsage();
			assertEquals(expectedMessage, returnedMessage);
		}
	}

}
