public class OffByN implements CharacterComparator {
    int number;

    OffByN(int number){
        this.number = number;
    }
    @Override
    public boolean equalChars(char x, char y) {
        // TODO Auto-generated method stub
        if (x - y == number || x - y == -number){
            return true;
        }
        return false;
    }
    
}