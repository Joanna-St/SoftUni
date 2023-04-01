function colorize() {
    let arr = Array.from(document.querySelectorAll('tbody > tr:nth-child(even)'))

    for (const item of arr) {
        item.style.backgroundColor = 'Teal'
    }
}