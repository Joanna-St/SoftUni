function numberMod(n) {

    while (calcAvg(n) < 5) {
        n = (n * 10) + 9
    }

    console.log(n);
    
    function calcAvg(n){
        let sum = 0;
        let countDigits = 0;

        while (n > 0){
            sum += n % 10
            countDigits++;
            n = Math.floor(n / 10)
        }

        return sum / countDigits;
    }
}

numberMod(5835)