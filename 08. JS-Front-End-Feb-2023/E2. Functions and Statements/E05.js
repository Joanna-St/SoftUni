function palindromeIntegers(arr){
    arr.forEach(num => {
        console.log(isPalindrome(num));
    });

    function isPalindrome(num) {
        num = num.toString();
        for (let i = 0; i < num.length / 2; i++) {
            if (num.charAt(i) != num.charAt(num.length - 1 - i)){
                return false;
            }
        }

        return true;
    }
}

palindromeIntegers([32,2,232,1011])