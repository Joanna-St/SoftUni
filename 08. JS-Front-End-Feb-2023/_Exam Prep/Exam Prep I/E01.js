function pianoPieces([n, ...rest]) {
    let songList = {};
    
    class Song {
        constructor(songInfo) {
            let input = songInfo.split('|');
            this.songName = input[0];
            this.composer = input[1];
            this.key = input[2]
        }
    }

    for (let i = 0; i < n; i++) {
        let newSong = new Song(rest.shift());
        songList[newSong.songName] = newSong;
    }

    let commandHandler = {
        add(songInfo) {
            let checkSong = new Song(songInfo);

            if (songList.hasOwnProperty(checkSong.songName)) {
                console.log(`${checkSong.songName} is already in the collection!`);
            } else {
                songList[checkSong.songName] = checkSong;
                console.log(`${checkSong.songName} by ${checkSong.composer} in ${checkSong.key} added to the collection!`);
            }
        },
        remove(songName) {
            if (songList.hasOwnProperty(songName)) {
                delete songList[songName];
                console.log(`Successfully removed ${songName}!`);
            } else {
                console.log(`Invalid operation! ${songName} does not exist in the collection.`);
            }
        },
        changekey(songInfo){
            let songName = songInfo.split('|')[0];
            let newKey = songInfo.split('|')[1];

            if (songList.hasOwnProperty(songName)) {
                songList[songName].key = newKey
                console.log(`Changed the key of ${songName} to ${newKey}!`);
            } else {
                console.log(`Invalid operation! ${songName} does not exist in the collection.`);
            }
        },
        stop(){
            for (const song of Object.values(songList)) {
                console.log(`${song.songName} -> Composer: ${song.composer}, Key: ${song.key}`);
            }
        }
    }

    

    for (const string of rest) {
        let commandArr = string.split('|');
        let command = commandArr.shift().toLowerCase();
        let commandInput = commandArr.join('|');

        commandHandler[command](commandInput);
    }
}

pianoPieces([
    '3',
    'Fur Elise|Beethoven|A Minor',
    'Moonlight Sonata|Beethoven|C# Minor',
    'Clair de Lune|Debussy|C# Minor',
    'Add|Sonata No.2|Chopin|B Minor',
    'Add|Hungarian Rhapsody No.2|Liszt|C# Minor',
    'Add|Fur Elise|Beethoven|C# Minor',
    'Remove|Clair de Lune',
    'ChangeKey|Moonlight Sonata|C# Major',
    'Stop'
])