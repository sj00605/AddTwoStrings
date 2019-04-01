public class AddTwoStrings {
    private static final String DECIMAL_DELIMETER = "\\.";

    public String addTwoStrings(String a, String b){
        String[] s1 = a.split(DECIMAL_DELIMETER);
        String[] s2 = b.split(DECIMAL_DELIMETER);

        if (s1.length == 1 && s2.length == 1) {
            return add(s1[0], s2[0], false);
        }

        String rightOfDecimal;
        String leftOfDecimal;
        if (s1.length == 1 && s2.length == 2) {
            rightOfDecimal = add("0", s2[1], true);
            leftOfDecimal = add(s1[0], s2[0], false);
            return combineLeftAndRightOfDecimal(rightOfDecimal, s2[1].length(), leftOfDecimal);
        } else if (s1.length == 2 && s2.length == 1) {
            rightOfDecimal = add("0", s1[1], true);
            leftOfDecimal = add(s1[0], s2[0], false);
            return combineLeftAndRightOfDecimal(rightOfDecimal, s1[1].length(), leftOfDecimal);
        } else {
            rightOfDecimal = add(s1[1], s2[1], true);
            leftOfDecimal = add(s1[0], s2[0], false);
            return combineLeftAndRightOfDecimal(rightOfDecimal, Math.max(s1[1].length(),s2[1].length()), leftOfDecimal);
        }
    }

    private String add(String a, String b, boolean rightDecimal) {
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();

        String answer;
        if (charA.length < charB.length ){
            answer = add(charA, charB, rightDecimal);
        } else {
            answer = add(charB, charA, rightDecimal);
        }
        return answer;
    }

    private String add(char[] shorter, char[] longer, boolean rightDecimal) {
        StringBuilder sb = new StringBuilder();
        int amountOfPadding = longer.length - shorter.length;
        char[] shorterWithPadding;

        if (rightDecimal) {
            shorterWithPadding = addPaddingRight(shorter, amountOfPadding);
        } else {
            shorterWithPadding = addPaddingLeft(shorter, amountOfPadding);
        }

        int carry = 0;
        for (int i=shorterWithPadding.length-1; i>=0; i--) {
            int a = Character.getNumericValue(shorterWithPadding[i]);
            int b = Character.getNumericValue(longer[i]);
            int addition = a + b + carry;
            if (addition <= 9) {
                carry = 0;
            } else {
                addition = addition%10;
                carry = 1;
            }
            sb.append(String.valueOf(addition));
        }

        if (carry == 1) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }

    private char[] addPaddingLeft(char[] array, int paddingToAdd) {
        char[] arrayWithPadding = new char[array.length + paddingToAdd];

        for (int i=0; i<paddingToAdd; i++) {
            arrayWithPadding[i] = '0';
        }

        for (int i=paddingToAdd; i<arrayWithPadding.length; i++) {
            arrayWithPadding[i] = array[i-paddingToAdd];
        }
        return arrayWithPadding;
    }

    private char[] addPaddingRight(char[] array, int paddingToAdd) {
        char[] arrayWithPadding = new char[array.length + paddingToAdd];
        for (int i = 0; i<array.length; i++) {
            arrayWithPadding[i] = array[i];
        }
        for (int i=array.length; i<arrayWithPadding.length; i++) {
            arrayWithPadding[i] = '0';
        }
        return arrayWithPadding;
    }

    private String combineLeftAndRightOfDecimal(String rightDecimal, int lengthOfStartingBefore, String leftDecimal) {
        StringBuilder combineAnswer = new StringBuilder();
        if (rightDecimal.length() > lengthOfStartingBefore) {
            rightDecimal = rightDecimal.substring(1);
            leftDecimal = add(leftDecimal, "1", false);
        }

        combineAnswer.append(leftDecimal);
        combineAnswer.append(".");
        combineAnswer.append(rightDecimal);

        return combineAnswer.toString();
    }
}
