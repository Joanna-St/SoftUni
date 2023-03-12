function charsInRange(...chars) {
    console.log(getSymbols(sortChars()).join(' '));

    function sortChars() {
        return chars.sort();
    }
    
    function getSymbols(sorted) {
        let firstIndex = sorted[0].charCodeAt(0) + 1;
        let lastIndex = sorted[1].charCodeAt(0);
    
        let resultArr = [];
    
        for (let i = firstIndex; i < lastIndex; i++) {
            resultArr.push(String.fromCharCode(i));
        }
        return resultArr;
    }
}



charsInRange('C',
'#')
