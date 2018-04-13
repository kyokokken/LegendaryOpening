import com.kookoon.legendaryopening.PacksTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

  PacksTracker tracker;
  double delta = 0.0001;

  @Before
  public void init() {
    tracker = new PacksTracker();
  }

  @Test
  public void simpleAssertTest() {
    Assert.assertTrue(tracker != null);
  }

  @Test
  public void gonnaBeLegendary() {
    tracker.setOpenedPacks(9);
    tracker.setOpenedLegendaries(0);
    float chance = tracker.getNextPackLegendaryChance();
    Assert.assertEquals(1.0f / 1, chance, delta);
  }

  @Test
  public void gonnaBeFiftyFiftyLegendary() {
    tracker.setOpenedPacks(8);
    tracker.setOpenedLegendaries(0);
    float chance = tracker.getNextPackLegendaryChance();
    Assert.assertEquals(1.0f / 2, chance, delta);
  }

  @Test
  public void sevenPacksOpened() {
    tracker.setOpenedPacks(7);
    tracker.setOpenedLegendaries(0);
    float chance = tracker.getNextPackLegendaryChance();
    Assert.assertEquals(1.0 / 3, chance, delta);
  }

  @Test
  public void firstPackOpening() {
    tracker.setOpenedPacks(0);
    tracker.setOpenedLegendaries(0);
    float chance = tracker.getNextPackLegendaryChance();
    Assert.assertEquals(1.0f / 10, chance, delta);
  }

  @Test
  public void constrainOnFirstLegendary() {
    tracker.setOpenedPacks(0);
    int openedLegendaries = tracker.getOpenedLegendaries();
    Assert.assertTrue("Opened legendaries is not equal to zero: [" + openedLegendaries + "]", openedLegendaries == 0);
    tracker.setOpenedPacks(10);
    tracker.setOpenedLegendaries(1);
    openedLegendaries = tracker.getOpenedLegendaries();
    Assert.assertTrue("Opened legendaries is not greater than zero: [" + openedLegendaries + "]", openedLegendaries > 0);
  }

  @Test
  public void openThirteenthPack() {
    tracker.setOpenedPacks(29); // I have opened 10 packs,
    tracker.setOpenedLegendaries(1); // I am assured that I have at least 1 legendary.
    float chance = tracker.getNextPackLegendaryChance();
    Assert.assertEquals(1.0f, chance, delta);
  }

  @Test
  public void openTwentyNineteethPack() {
    tracker.setOpenedPacks(28);
    tracker.setOpenedLegendaries(1);
    float chance = tracker.getNextPackLegendaryChance();
    Assert.assertEquals(1.0f / 2, chance, delta);
  }

  @Test
  public void open64thPack() {
    tracker.setOpenedPacks(63);
    tracker.setOpenedLegendaries(3);
    float chance = tracker.getNextPackLegendaryChance();
    Assert.assertEquals(1.0f / (70 - 63), chance, delta);
  }

  @Test
  public void alreadyOpenedSomePacks() {
    tracker.setOpenedPacks(9);
    tracker.setOpenedLegendaries(0);
    tracker.setOpenedFirstTenPacks(true);
    float chance = tracker.getNextPackLegendaryChance();
    Assert.assertEquals(1.0f / (20 - 9), chance, delta);
  }

  @Test
  public void alreadyOpenedFirstTenPacksAndOpening64thPack() {
    tracker.setOpenedPacks(63);
    tracker.setOpenedLegendaries(3);
    tracker.setOpenedFirstTenPacks(true);
    float chance = tracker.getNextPackLegendaryChance();
    Assert.assertEquals(1.0f / (80 - 63), chance, delta);
  }
}
