function findPerfectNum(n) {
    let isPositive = n > 0;
    let equalToDevisors = sumDivisors(getDivisors) / 2 == n;

    if (isPositive && equalToDevisors){
        console.log('We have a perfect number!');
    } else {
        console.log("It's not so perfect.");
    }


    function getDivisors() {
        let divisors = [];

        for (let i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors.push(i);
            }
        }
        return divisors;
    }

    function sumDivisors(getDivisors){
        let sum = 0;

        for (const divisor of getDivisors()) {
            sum += divisor;
        }

        return sum;
    }
}

findPerfectNum(1236498)