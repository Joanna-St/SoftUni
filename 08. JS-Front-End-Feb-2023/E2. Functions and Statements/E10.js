function factorialDivision(a, b) {

    console.log((calcFactorial(a) / calcFactorial(b)).toFixed(2));

    function calcFactorial(x) {
        let factorialTotal = 1;
        
        multiply();

        function multiply() {
            factorialTotal *= x;
            x -= 1;
            if (x > 0){
                multiply();
            }
        }

        return(factorialTotal);
    }
}

factorialDivision(6,2)