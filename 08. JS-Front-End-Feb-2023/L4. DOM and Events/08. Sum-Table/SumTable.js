function sumTable() {
    let arr = Array.from(document.querySelectorAll('tbody td:nth-child(even)'))
    let sum = 0;

    for (const item of arr) {
        sum += Number(item.textContent);
    }

    document.getElementById('sum').textContent = sum
}