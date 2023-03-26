function oddOccurrences(inputString) {
    let wordsToCheck = inputString.split(' ');

    let trackedCounts = new Map();

    for (let word of wordsToCheck) {
        word = word.toLowerCase();
        if (!trackedCounts.has(word)) {
            trackedCounts.set(word, 1);
        } else {
            let newCount = trackedCounts.get(word) + 1;
            trackedCounts.set(word, newCount)
        }
    }

    let oddNum = [];

    trackedCounts.forEach((value, key) => {
        if (value % 2 != 0) {
            oddNum.push(key)
        }
    })

    console.log(oddNum.join(' '));
}

oddOccurrences('Java C# Php PHP Java PhP 3 C# 3 1 5 C#')