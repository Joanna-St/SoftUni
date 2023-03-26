function wordTracker(arr) {
    let wordsToTrack = arr.shift().split(' ');
    let wordsToCheck = arr;

    let trackedCounts = {};

    for (const trackedWord of wordsToTrack) {
        trackedCounts[trackedWord] = 0;

        for (const checkWord of wordsToCheck) {
            if (checkWord == trackedWord){
                trackedCounts[trackedWord]++;
            }
        }
    }

    Object.entries(trackedCounts).sort((entry1, entry2) => entry2[1] - entry1[1]).forEach(entry => console.log(entry[0] +' - ' + entry[1]));
}

wordTracker([
    'is the', 
    'first', 'sentence', 'Here', 'is', 'another', 'the', 'And', 'finally', 'the', 'the', 'sentence'])