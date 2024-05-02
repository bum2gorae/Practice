class Print {
    public String repeatAlphabet(String word, int repeatNum) {
        int repeatWordLength = word.length()*repeatNum; //전체 출력 길이를 변수 선언
        char[] repeatWord = new char[repeatWordLength]; //전체 출력 길이 만큼의 char 배열 생성
        for (int i = 0; i < repeatWordLength; i++) { //단어의 첫 알파벳부터 반복만큼 char배열에 추가
            int order = i/repeatNum; //반복하기 위한 인덱싱 번호 조정
            char wordAlphabet = word.charAt(order);
            repeatWord[i] = wordAlphabet;
        }
        String repeatWordStr = new String(repeatWord);
        return repeatWordStr;

    }
}

public class scratch_1 {
    public static void main(String[] args) {
        int testNum = 3;
        Print test = new Print();
        String result = test.repeatAlphabet("hello",testNum);
        System.out.println(result);
    }
}
