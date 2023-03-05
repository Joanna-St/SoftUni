function sortNames(arr) {
    arr.sort((a, b) => a.localeCompare(b));
    for (let i = 0; i < arr.length; i++) {
        const element = arr[i];
        console.log(`${i + 1}.${element}`);
    }
}

sortNames(["John", "Bob", "Christina", "Ema", "Ema"])