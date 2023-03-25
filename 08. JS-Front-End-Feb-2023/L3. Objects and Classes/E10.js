function songs(arr) {
    class Song{
        constructor(typeList, name, time) {
            this.typeList = typeList;
            this.name = name;
            this.time = time;
        }
    }

    let n = arr.shift();
    let command = arr.pop();
    let songsArr = {'all': []};

    for (const songInfo of arr) {
        let typeList = songInfo.split('_')[0]
        let name = songInfo.split('_')[1]
        let time = songInfo.split('_')[2]

        let song = new Song(typeList, name, time)

        if (!songsArr.hasOwnProperty(typeList)) {
            songsArr[typeList] = [song]
        } else {
            songsArr[typeList].push(song);
        }

        songsArr['all'].push(song)
    }

    songsArr[command].forEach(song => {
        console.log(song.name);
    });
}

songs([2,
    'like_Replay_3:15',
    'ban_Photoshop_3:48',
    'all'])