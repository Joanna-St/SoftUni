function movies(arr) {
    let moviesArr = {
        addIfExists(name,property,value) {
            if (moviesArr.hasOwnProperty(name)){
                moviesArr[name][property] = value;
            }
        }
    };
    // class Movie {
    //     constructor(name){
    //         this. name = name;
    //         this.director = undefined;
    //         this.date = undefined;
    //     }
    // }

    for (const item of arr) {
        let itemArrBySpace = item.split(' ');
        let movieInfo = [];
        let name = '';

        if (itemArrBySpace[0] == 'addMovie') {
            name = item.split('addMovie ')[1];
            let movie = {name}
            moviesArr[name] = movie;
        } else {
            if (itemArrBySpace.some((element) => element == 'directedBy')) {
                movieInfo = item.split(' directedBy ')
                name = movieInfo[0];
                let director = movieInfo[1];

                moviesArr.addIfExists(name, 'director', director)
            } else {
                movieInfo = item.split(' onDate ')
                name = movieInfo[0];
                let date = movieInfo[1];

                moviesArr.addIfExists(name, 'date', date)
            }
        }
    }

    for (const movie in moviesArr) {
        // let valuesArr = Object.values(moviesArr[movie])

        // if (!valuesArr.some((element) => element == undefined)) {
        //     console.log(JSON.stringify(moviesArr[movie]));
        // }
        
        let propertiesArr = Object.keys(moviesArr[movie])

        if (propertiesArr.length == 3) {
            console.log(JSON.stringify(moviesArr[movie]));
        }
    }
}

movies([
    'addMovie The Avengers',
    'addMovie Superman',
    'The Avengers directedBy Anthony Russo',
    'The Avengers onDate 30.07.2010',
    'Captain America onDate 30.07.2010',
    'Captain America directedBy Joe Russo'
    ])