import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } 
    //Uncomment this class once you've created your Palindrome class. 

    @Test
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("terret"));
        assertTrue(palindrome.isPalindrome("tebbet"));
        assertTrue(palindrome.isPalindrome("rotor"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testIsPalindrome() {
        CharacterComparator x = new  OffByOne();
        assertTrue(palindrome.isPalindrome("ab", x));
        assertTrue(palindrome.isPalindrome("flake",x));
        assertTrue(palindrome.isPalindrome("a",x));
        assertTrue(palindrome.isPalindrome("",x));
    }
}
