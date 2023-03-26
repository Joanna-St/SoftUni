function towns(arr){
    let townsArr = [];

    for (const item of arr) {
        let townInfo = item.split(' | ')

        let town = {
            town: townInfo[0],
            latitude: Number(townInfo[1]).toFixed(2),
            longitude:  Number(townInfo[2]).toFixed(2)
        }

        townsArr.push(town)
    }

    townsArr.forEach(town => {console.log(town); });
}

towns(['Sofia | 42.696552 | 23.32601',
'Beijing | 39.913818 | 116.363625']
)
