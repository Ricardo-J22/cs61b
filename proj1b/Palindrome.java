public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> head  = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            head.add(word.charAt(i));
        }
        return head;
    }
    public boolean isPalindrome(String word){
        if (word.length() == 0 || word.length() == 1){
            return true;
        }
        for (int i = 0; i <= word.length()/ 2; i++) {
            if(!( word.charAt(i) == word.charAt(word.length() - i - 1 ))){
                return false;
            }
        }
        
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() == 0 || word.length() == 1){
            return true;
        }
        for (int i = 0; i < word.length()/2; i++) {
            if(!cc.equalChars(word.charAt(i), word.charAt( word.length() - 1 - i))){
                return false;
            }
        }
        return true;
    }
}