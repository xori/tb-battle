package TBBL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Evan Verworn
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({})
public class HighLevelBattle {

  @BeforeClass
  public static void setUpClass() throws Exception {
    
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
    
  }

  @Before
  public void setUp() throws Exception {
    
  }

  @After
  public void tearDown() throws Exception {
    
  }
  
  @Test
  public void HighLevelBattle() throws Exception {
      Team one = new Team();
      Team two = new Team();

      Character charzard = Character.load("Charzard"); 
      Character sparrow = Character.load("Sparrow");
      one.addTeamMate(charzard);
      two.addTeamMate(sparrow);

      Battle board = new Battle(one, two/*, three, four*/);

      BoardState state = board.nextTurn(); // processes current moves then wait until it gets two more moves?
      MoveSet options = charzard.getMoves(); // Returns move list
      MoveSet sMoves = sparrow.getMoves();

      charzard.submit(options.use("Flamethrower"));
      sparrow.submit(sMoves.use("Gust"));

      state = board.nextTurn();
      charzard.health; // 90
      sparrow.health; // 90

      // There. Now offical tests.
      assert.true(charzard.health < 100)g
      assert.true(sparrow.health < 100)
  }

  @Test
  public void CharacterAttributes() throws Exception {
      Character charzard = Character.load("Charzard");
      assert.propertiesExist(charzard, "health", "speed", "defence", ...);
      /*
          Charzard : {
              getMoves() : MoveSet
              health : int
              speed : int
              defence : int
              attack : int
              spAttack : int
              spDefence : int
              modifiers : List<Modifier>
          }
      */
  }

  @Test 
  /**
   * Character should implement a modifier interface. Allowing it to change stats of either
   *   itself or the board. It should also have control over removing itself from a character's
   *   modifier list by possibly returning false. 
   **/
  public void CharacterModifiers() throw Exception {
      Character charzard = Character.load("Charzard");
      charzard.modifiers.Add(new Modifier() { // Poison Example
          @Override
          public boolean OnAfterAttack(Character myself, Team friendly, List<Team> enemy, Board field) {
              myself.health -= Math.Random() * 10 + 1;
              return Math.Random() > 0.5;
          }    
      });
      Battle battle = new Battle(new Team(charzard), new MockTeam()));
      Board board = battle.nextTurn();
      charzard.submit(charzard.getMoves().submit("Scratch"));
      board = battle.nextTurn();
      assert.true(charzard.health < 100 && charzard.health > 89);
  }
  
}