function pointsValidation([x1, y1, x2, y2]) {

    let firstDistanceStatement = `{${x1}, ${y1}} to {0, 0} is `
    let secondDistanceStatement = `{${x2}, ${y2}} to {0, 0} is `
    let thirdDistanceStatement = `{${x1}, ${y1}} to {${x2}, ${y2}} is `

    if (distanceIsInt([x1, y1, 0, 0])) {
        firstDistanceStatement = firstDistanceStatement.concat('valid')
    } else {
        firstDistanceStatement = firstDistanceStatement.concat('invalid')
    }

    if (distanceIsInt([x2, y2, 0, 0])) {
        secondDistanceStatement = secondDistanceStatement.concat('valid')
    } else {
        secondDistanceStatement = secondDistanceStatement.concat('invalid')
    }

    if (distanceIsInt([x1, y1, x2, y2])) {
        thirdDistanceStatement = thirdDistanceStatement.concat('valid')
    } else {
        thirdDistanceStatement = thirdDistanceStatement.concat('invalid')
    }

    console.log(firstDistanceStatement);
    console.log(secondDistanceStatement);
    console.log(thirdDistanceStatement);

    function distanceIsInt([x1, y1, x2, y2]) {
        let result = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));

        return result % 1 == 0;
    }
}

pointsValidation([2, 1, 1, 1])