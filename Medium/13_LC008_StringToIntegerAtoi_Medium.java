class Solution {
    public int myAtoi(String s) {
        int index = 0;

        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }

        int sign = 1;

        if (index < s.length() && s.charAt(index) == '-') {
            sign = -1;
            index++;
        } else if (index < s.length() && s.charAt(index) == '+') {
            index++;
        }

        long number = 0;

        while (index < s.length()
                && s.charAt(index) >= '0'
                && s.charAt(index) <= '9') {
            int digit = s.charAt(index) - '0';
            number = number * 10 + digit;

            long signedNumber = number * sign;

            if (signedNumber > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            if (signedNumber < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            index++;
        }

        return (int) (number * sign);
    }
}
