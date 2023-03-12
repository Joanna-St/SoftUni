function passwordValidator(password) {
    let errorMessages = [];

    let lengthCheckResult = lengthCheck(password);
    let charCheckResult = passwordIterator(password);

    if (lengthCheckResult != null) {
        errorMessages.push(lengthCheckResult)
    }

    if (charCheckResult.length > 0) {
        errorMessages.push(...charCheckResult)
    }

    if (errorMessages.length > 0) {
        errorMessages.forEach(message => console.log(message))
    } else {
        console.log('Password is valid');
    }

    function lengthCheck(password) {
        if (password.length < 6 || password.length > 10) {
            return 'Password must be between 6 and 10 characters'
        }

        return null;
    };

    function passwordIterator(password) {
        const isDigit = charCode => charCode >= 48 && charCode <= 57;
        const isCapitalLetter = charCode => charCode >= 65 && charCode <= 90;
        const isSmallLetter = charCode => charCode >= 97 && charCode <= 122;

        function isInvalidChar(charCode) {
            return !isDigit(charCode) && !isCapitalLetter(charCode) && !isSmallLetter(charCode);
        };

        let returnMessages = [];
        let validChars = true;
        let digitsCount = 0;

        for (const char of password) {
            let charCode = char.charCodeAt(0);

            if (isInvalidChar(charCode)) {
                validChars = false;
            }

            if (isDigit(charCode)) {
                digitsCount++;
            }
        }

        if (!validChars) {
            returnMessages.push('Password must consist only of letters and digits');
        }

        if (digitsCount < 2) {
            returnMessages.push('Password must have at least 2 digits')
        }

        return returnMessages;
    };
};



passwordValidator('Pa$s$s')