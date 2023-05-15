package PruebasEspecificaciones;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * These are the tests we made in the exploration phase (step 2)
 */
public class StringUtilsExplorationTest {

  @Test
  void simpleCase() {
    assertThat(
        StringUtils.substringsBetween("abcd", "a", "d")
    ).isEqualTo(new String[]{"bc"});
  }

  @Test
  void manyStrings() {
    assertThat(
        StringUtils.substringsBetween("abcdabcdab", "a", "d")
    ).isEqualTo(new String[]{"bc", "bc"});
  }

  @Test
  void openAndCloseTagsThatAreLongerThan1Char() {
    assertThat(
        StringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")
    ).isEqualTo(new String[]{"bc", "bf"});
  }
}
