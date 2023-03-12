function printMatrix(n) {
    printRows(rowContent);

    function printRows(rowContent) {
        for (let i = 0; i < n; i++) {
            console.log(rowContent());
        }
    }

    function rowContent() {
        return (`${n} `).repeat(n).trim();
    }
}

printMatrix(7)